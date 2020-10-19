package com.spring.view;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.MemberService;
import com.spring.biz.vo.BuyListVO;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.MemberVO;

@Controller
public class MemberController {
	@Resource(name = "memberService")
	MemberService memberService;
//	보통 string
	
	@RequestMapping(value = "/insertMember.me")
	public String insertMember(MemberVO memberVO) {
		memberService.insertMember(memberVO);
		return "redirect:shopList.sh"; //WEB-INF/view/list.do.jsp
	}
	
	//로그인
	@RequestMapping(value = "/login.me")
	public String login(MemberVO memberVO, HttpSession session) {
		MemberVO vo = memberService.login(memberVO);
		
		if(vo != null) {
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:shopList.sh"; //WEB-INF/view/list.do.jsp
	}
	
	@RequestMapping(value = "/logout.me")
	public String logout(MemberVO memberVO, HttpSession session) {
		session.removeAttribute("loginInfo");
		
		return "redirect:shopList.sh"; //WEB-INF/view/list.do.jsp
	}
	//내 정보 조회
	@RequestMapping(value = "/selectMember.me")
	public String selectMember(MemberVO memberVO) {
		
		return "member/memberDetail"; //WEB-INF/view/list.do.jsp
	}
	//내 정보 변경
	@RequestMapping(value = "/updateMember.me")
	public String updateMember(MemberVO memberVO) {
		
		return "member/updateMember"; //WEB-INF/view/list.do.jsp
	}
	//내 정보 탈퇴
	@RequestMapping(value = "/deleteMember.me")
	public String deleteMember(MemberVO memberVO) {
	      //페이지 정보 설정
	      memberVO.setMainMenu("member");
	      memberVO.setSubMenu("deleteMember");
		
		return "member/deleteMember"; //WEB-INF/view/list.do.jsp
	}
	   //실제 회원탈퇴
	   @RequestMapping(value = "/updateYNdeleteMember.me")
	   public String updateYNdeleteMember(MemberVO memberVO, HttpSession session, Model model) {
	      //페이지 정보 설정
	      memberVO.setMainMenu("member");
	      memberVO.setSubMenu("deleteMember");
	      
	      memberService.updateYNdeleteMember(memberVO);
	      //세션에 로그인된 정보를 지움
	      session.removeAttribute("loginInfo");
	      return "redirect:deleteMember.me";
	   }
	 //구매목록 조회
		@RequestMapping(value = "/selectBuyList.me")
		public String selectBuyList(MemberVO memberVO, HttpSession session, Model model) {
			//모든 데이터를 담고 있는 객체 생성
			//key가 String 타입이고, 해당 키에는 날짜가 들어옴.
			//value로 <BuyVO>가 들어오는데, 이는 구매목록이다. (원래는 오름차순인데 내림차순으로 변경하는 방법 reverseOrder)
			Map<String, List<BuyListVO>> buyList = new TreeMap<>(Collections.reverseOrder());
			//구매일자별 총 구매금액 정보를 담을 리스트
			List<Integer> orderPricePerDateList = new ArrayList<>();	
			
			//구매일자를 중복제거 후 가져옴.
			MemberVO vo = ((MemberVO)session.getAttribute("loginInfo"));
			List<String> buyDateList = memberService.selectBuyDateList(vo.getMemberId());
			
			//구매일자 수만큼 반복해서 해당 구매일의 구매목록을 조회
			for(int i = 0; i < buyDateList.size(); i++) {
				BuyVO buyVO = new BuyVO();
				buyVO.setMemberId(vo.getMemberId());
				buyVO.setBuyDate(buyDateList.get(i));
				
				List<BuyListVO> buyListPerDate = memberService.selectBuyList(buyVO);
				
				buyList.put(buyDateList.get(i), buyListPerDate);
				
				//구매날짜별 총 구매금액을 저장한 변수
				int totalPricePerDate = 0;
				
				for(BuyListVO buyListVO : buyListPerDate) {
					totalPricePerDate += buyListVO.getOrderPrice();
					
				}
				orderPricePerDateList.add(totalPricePerDate);
				
			}
			
			//출력하는 거 주석처리 함(나중에 공부해도 됨)
//			Set<String> buyListSet = buyList.keySet(); //key들만 목록을 쫙 뽑겠다. (map에 있는 모든 키값을 가져온다.)			
//			
//			for(String key : buyListSet) {
//				System.out.println("구매일 = " + key);
//				List<BuyListVO> list = buyList.get(key);
//				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//				for(BuyListVO buyInfo : list) {
//					System.out.println(buyInfo);
//					
//				}
//				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//				System.out.println();
//			}
			
			model.addAttribute("buyList", buyList);
			model.addAttribute("orderPricePerDateList", orderPricePerDateList);
			
			
			return "member/selectBuyList"; //WEB-INF/view/list.do.jsp
		}
	
}






