package com.spring.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.ShopService;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartListVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.MemberVO;

@Controller
public class ShopController {
	@Resource(name = "shopService")
	ShopService shopService;
	
	@RequestMapping(value = "/shopList.sh")
	public String shopList(GoodsVO goodsVO, Model model) {
		//도서상품 목록 조회
		model.addAttribute("goodsList", shopService.selectGoodsList(goodsVO));
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		return "shop/shopList"; //WEB-INF/view/list.do.jsp
	}
	@RequestMapping(value = "/goodsDetail.sh")
	public String goodsDetail(GoodsVO goodsVO, Model model, MemberVO memberVO, ImageVO imageVO) {
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//도서상품 상세보기
		model.addAttribute("goodsDetail", shopService.goodsDetail(goodsVO));
		//페이지 정보 설정
		goodsVO.setMainMenu("shopping");
		
		//도서상품 이미지 상세 조회
		model.addAttribute("goodsImageList", shopService.goodsImageList(imageVO));
		
		return "shop/goodsDetail"; //WEB-INF/view/list.do.jsp
	}
	//구매정보페이지
	@RequestMapping(value = "/buyPage.sh")
	public String buyPage(GoodsVO goodsVO, int cnt, Model model) {
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//도서상세정보조회
		model.addAttribute("goodsDetail", shopService.goodsDetail(goodsVO));
		
		//수량
		model.addAttribute("cnt", cnt);
		
		return "shop/buyPage"; //template.jsp
	}
	//상품 구매
	@RequestMapping(value = "/insertBuy.sh")
	public String insertBuy(BuyVO buyVO, HttpSession session) {
		MemberVO memberVO = (MemberVO)session.getAttribute("loginInfo");
		buyVO.setMemberId(memberVO.getMemberId());
		buyVO.setMemberName(memberVO.getMemberName());
		buyVO.setMemberTel1(memberVO.getTel1());
		buyVO.setMemberTel2(memberVO.getTel2());
		buyVO.setMemberAddr(memberVO.getMemberAddr());
		
		shopService.insertBuy(buyVO);
		
		return "redirect:shopList.sh"; //template.jsp
	}
	
	//상품 장바구니 담기
	@ResponseBody
	@RequestMapping(value = "/insertCart.sh")
	public String insertCart(CartVO cartVO, BuyVO buyVO, HttpSession session, GoodsVO goodsVO, Model model) {
		
		//페이지 정보 설정
		goodsVO.setMainMenu("shopping");
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//도서상품 상세보기
		model.addAttribute("goodsDetail", shopService.goodsDetail(goodsVO));
		
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		
		cartVO.setMemberId(memberId);
		
		
		shopService.insertCart(cartVO);
		
		return "shop/cartList"; //template.jsp
	}
	
	@RequestMapping(value = "/selectCartList.sh")
	public String selectCartList(CartListVO cartListVO, CartVO cartVO, HttpSession session, GoodsVO goodsVO, Model model, MemberVO memberVO, ImageVO imageVO) {
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		//도서상품 상세보기
		model.addAttribute("goodsDetail", shopService.goodsDetail(goodsVO));
		//페이지 정보 설정
		goodsVO.setMainMenu("shopping");
		
		
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		
		cartListVO.setMemberId(memberId);
		
		model.addAttribute("cartList", shopService.selectCartList(cartListVO));
		
		return "shop/cartList"; //WEB-INF/view/list.do.jsp
	}
	//장바구니 비우기
	@RequestMapping(value = "/deleteCart.sh")
	public String deleteCart(String[] cartIds, CartVO cartVO) {
		List<String> cartIdList = Arrays.asList(cartIds);
		cartVO.setCartIdList(cartIdList);
		
		shopService.deleteCart(cartVO);
		
		return "redirect:selectCartList.sh"; //WEB-INF/view/list.do.jsp
	}
	//장바구니 상품 수량변경
	@ResponseBody
	@RequestMapping(value = "/updateCartCnt.sh")
	public void updateCartCnt(CartVO cartVO, CartListVO cartListVO) {
		
		shopService.updateCartCnt(cartListVO);
		
	}
	   //장바구니에서 구매하기 버튼 클릭 시
	   @RequestMapping(value = "/cartBuyPage.sh")
	   public String cartBuyPage(Model model, GoodsVO goodsVO, String[] cartIdArray, CartVO cartVO) {
	      //카테고리 목록 조회
	      model.addAttribute("categoryList", shopService.selectCategoryList());
	      
	      //페이지 정보 설정
	      goodsVO.setMainMenu("shopping");
	      
	      //배열을 list로 바꿔줌
	      List<String> list = Arrays.asList(cartIdArray);
	      
	      cartVO.setCartIdList(list);
	      model.addAttribute("buyList", shopService.selectCartBuyList(cartVO));
	      
	      return "shop/cartBuyPage"; //tiles_shop.xml에서 definition 호출 
	   }
	//구매페이지에서 여러사움 구매시. (int형은 hidden으로 못받아와서 배열로 주면 받아온다.ex) int[] orderPrice )
	@RequestMapping(value = "/insertBuyList.sh")
	public String insertBuyList(int[] goodsId, String[] goodsName, int[] orderGoodsCnt, int[] orderPrice, BuyVO buyVO, HttpSession session,CartVO cartVO, String[] cartId) {
		MemberVO memberVO = (MemberVO)session.getAttribute("loginInfo");
		
		List<BuyVO> buyList = new ArrayList<>();
		
		for(int i = 0; i < goodsId.length; i++) {
			BuyVO vo = new BuyVO();
			vo.setGoodsId(goodsId[i]);
			vo.setGoodsName(goodsName[i]);
			vo.setOrderGoodsCnt(orderGoodsCnt[i]);
			vo.setOrderPrice(orderPrice[i]);
			vo.setMemberId(memberVO.getMemberId());
			vo.setMemberName(memberVO.getMemberName());
			vo.setMemberTel1(memberVO.getTel1());
			vo.setMemberTel2(memberVO.getTel2());
			vo.setMemberAddr(memberVO.getMemberAddr());
			buyList.add(vo);
		}
		
		buyVO.setBuyList(buyList);
		System.out.println(cartId[0]);
		List<String> cartIdList = Arrays.asList(cartId);
		cartVO.setCartIdList(cartIdList);
		
		shopService.insertBuyList(buyVO,cartVO);
		
		return "redirect:shopList.sh";
	}
	
	
}





