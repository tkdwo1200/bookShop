package com.spring.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.biz.ShopService;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.MemberVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;
import com.spring.util.DateUtil;


@Controller
public class AdminController {
	@Resource(name = "shopService")
	ShopService shopService;
	
	//구매관리
	@RequestMapping(value = "/manageBuy.ad")
	public String manageBuy(GoodsVO goodsVO, MemberVO memberVO, SearchVO searchVO, Model model) {
		//페이지 정보설정
		goodsVO.setMainMenu("admin");
		//categoryList 조회 및 데이터 전달
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//toDate에 들어갈 날짜
		String toDate = searchVO.getToDate();
		//fromDate에 들어갈 날짜
		String fromDate = searchVO.getFromDate();
		
		if(searchVO.getFromDate() == null || searchVO.getFromDate().equals("")) {
			
//			Calendar cal = Calendar.getInstance();
//			//시간 데이터의 포맷을 정해주는 객체
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
////			System.out.println(dateFormat.format(cal.getTime()));
//			//현재 날짜
//			String toDate = dateFormat.format(cal.getTime());
			//!위 세줄과같음! class 따로 만들어서 사용
			//todate에 현재 날짜 세팅
			toDate = DateUtil.getNowDate();
			
			//한달전 날짜
//			cal.add(Calendar.MONTH, -1);
//			String fromDate = dateFormat.format(cal.getTime());
			//fromdate에 한달 전 날짜 세팅
			fromDate = DateUtil.getBeforeDate();
			
			//buyVO에 넣어줌
			searchVO.setFromDate(fromDate);
			searchVO.setToDate(toDate);
			
		}
		
		model.addAttribute("toDate", toDate);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("buyList", shopService.selectBuyList(searchVO));
		
		return "admin/manageBuy"; //WEB-INF/view/list.do.jsp
	}
	
	//상품등록페이지 이동
	@RequestMapping(value = "/insertItem.ad")
	public String insertItem(MemberVO memberVO, Model model) {
		//categoryList 조회 및 데이터 전달
		model.addAttribute("categoryList", shopService.selectCategoryList());
		return "admin/insertItem"; //WEB-INF/view/list.do.jsp
	}
	//상품관리
	@RequestMapping(value = "/manageItem.ad")
	public String manageItem(MemberVO memberVO, GoodsVO goodsVO, Model model) {
		//페이지 정보설정
		goodsVO.setMainMenu("admin");
		
		model.addAttribute("goodsList", shopService.selectGoodsListForManage(goodsVO));
		
		return "admin/manageItem"; //WEB-INF/view/list.do.jsp
	}
	//회원관리
	@RequestMapping(value = "/manageMember.ad")
	public String manageMember(MemberVO memberVO) {
		return "admin/manageMember"; //WEB-INF/view/list.do.jsp
	}
	//카테고리리스트
	@RequestMapping(value = "/manageCategory.ad")
	public String manageCategory(MemberVO memberVO, GoodsVO goodsVO, Model model) {
		//페이지 위에 선택 되고 안되고 차이 확인필요
		goodsVO.setMainMenu("admin");
		//categoryList 조회 및 데이터 전달
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		return "admin/manageCategory"; //WEB-INF/view/list.do.jsp
	}
	
	//상품상태변경
	@ResponseBody
	@RequestMapping(value = "/updateGoodsStatus.ad")
	public int updateGoodsStatus(GoodsVO goodsVO) {
		int result = shopService.updateGoodsStatus(goodsVO);
		
		return result; //WEB-INF/view/list.do.jsp
	}
	//구매 정보 중 is_confirm 값 업데이트
	//@responseBody 넣어줘야 아 이게 ajax이구나 라고 생각하기때문에 무조건 넣어줘야함
	@ResponseBody
	@RequestMapping(value = "/updateIsConfirm.ad")
	public int updateIsConfirm(int orderNum) {
		int result = shopService.updateIsConfirm(orderNum); //WEB-INF/view/list.do.jsp
		return result;
	}
	
	//상품등록
	@RequestMapping(value = "/insertGoods.ad")
	public String insertGoods(GoodsVO goodsVO,ImageVO imageVO, Model model, HttpSession session, MultipartHttpServletRequest multi) throws Exception {
		//파일첨부 후 첨부된 파일명의 List를 전달
		List<String> attachedFileNames = attachFile(multi);
		//goodsId값 지정
		int goodsId = shopService.getMaxId();
		goodsVO.setGoodsId(goodsId);
		//상품정보 넣은거 memberId값 지정
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		goodsVO.setGoodsWriter(memberId);
		//모든 파라메터를 전달하기 위한 list생성(위에 넣음)
		//반복 횟수를 판단하기 위한 첨부파일의 갯수를 파악
		//shopping_image 테이블에 insert를 하기위한 파라메터 설정
		List<ImageVO> imageList = new ArrayList<>();
		for (int i = 0; i < attachedFileNames.size(); i++) {
			ImageVO vo = new ImageVO();
			String imageId = "IMG" + getNowDateTime() + (i + 1);
			vo.setImageId(imageId);
			vo.setGoodsId(goodsId);
			vo.setFileName(attachedFileNames.get(i));
			vo.setMemberId(memberId);
			imageList.add(vo);
		}
		imageVO.setImageList(imageList);
		shopService.insertGoods(goodsVO,imageVO);
		
		//페이지 정보를 보내줌
		model.addAttribute("mainMenu", "admin");
		model.addAttribute("subMenu", "insertItem");
		
		return "redirect:insertItem.ad"; //WEB-INF/view/list.do.jsp
	}
	
	//매출관리 페이지
		@RequestMapping(value = "/salesManage.ad")
		public String salesManage(Model model, SalesVO salesVO, GoodsVO goodsVO, MemberVO memberVO) {
			//페이지 위에 선택 되고 안되고 차이 확인필요
			goodsVO.setMainMenu("admin");
			//categoryList 조회 및 데이터 전달
			model.addAttribute("categoryList", shopService.selectCategoryList());
			//처음값 못받아와서 2020 넣어줌.ㅠㅠ 뭐지
			salesVO.setSalesYear("2020");
			
			//실제 매출이 발생한 월별 매출액 리스트
			List<SalesVO> salesList = shopService.selectSales(salesVO);
			//1~12월까지 정리된 매출액 리스트
			List<SalesVO> resultList = new ArrayList<>();

			//1~12월까지 전부 0원으로 매출액을 세팅. list 12개 배열 만듬
			for(int i = 0; i < 12; i++) {
				SalesVO vo = new SalesVO();
				String buyMonth = String.format("%02d", i + 1);
				vo.setBuyMonth(buyMonth); 
				vo.setSalesPerMonth(0);
				resultList.add(vo);
			}
			for(int i = 0 ; i < salesList.size() ; i++) {
		         for(int j = 0 ; j < resultList.size() ; j++) {
		            if(salesList.get(i).getBuyMonth().equals(resultList.get(j).getBuyMonth())) {
		               resultList.get(j).setSalesPerMonth(salesList.get(i).getSalesPerMonth());
		            }
		         }
		      }
			for(SalesVO e : resultList) {
				System.out.println(e);
				
			}
			model.addAttribute("resultList", resultList);
			
			
			
//			//필요 주석 
//			for(int i = 0; i < 12; i++) {
//				for(int j = 0; j < salesList.size(); j++) {
//					if(!salesList.get(j).getBuyMonth().equals(i + 1 + "")) {
//						SalesVO vo = new SalesVO();
//						vo.setBuyMonth(i + 1 + "");//""넣은 이유는 String으로 변하게 하기위해 
//						vo.setSalesPerMonth(0);
//					}
//					else {
//						SalesVO vo = new SalesVO();
//						vo.setBuyMonth(salesList.get(j).getBuyMonth());
//						vo.setSalesPerMonth(salesList.get(j).getSalesPerMonth());
//					}
//				}
//			}
			
			return "admin/salesManage";
		}
		//월 클릭시 해당월에 대한 일별 매출액 조회
		@ResponseBody
		@RequestMapping(value = "/selectSalesListPerMonth.ad")
		public List<SalesVO> selectSalesListPerMonth(String yearMonth, SalesVO salesVO) {
			//실제 매출이 발생한 일자와 해당 일자의 매출액 합계 List
			List<SalesVO> salesList = shopService.selectSalesListPerMonth(yearMonth);
			//해당 월의 1일부터 마지막 일자까지의 데이터가 담긴 변수
			List<SalesVO> resultList = new ArrayList<>();
			//선택한 월의 마지막 일자 구하기
			Calendar cal = Calendar.getInstance();
			int year = Integer.parseInt(yearMonth.substring(0, 4)); //202009->2020
			int month = Integer.parseInt(yearMonth.substring(4, 6)); //202009->09
			cal.set(year, month - 1, 1);
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			//1일~마지막일까지 데이터 세팅
			int index = 0;
			for (int i = 0; i < lastDay; i++) {
				SalesVO vo = new SalesVO();
				String day = String.format("%02d", i + 1);
				vo.setBuyDay(day);//01
				
				if(salesList.size() != 0) {
					//null들어가는줄알았는데 아님; 그래서 크기가 0이면이랑 아니면으로 나눠서 
					if(day.equals(salesList.get(index).getBuyDay())) {
						// salesList(의값)와 day가 0~lastday까지 가는데 값이 같으면, buyDay의 값을 sales에 넣는다. 그걸 vo에 넣어줌 그후 index+1 해서 for문돌림
						int sales = salesList.get(index).getSalesPerDay();
						vo.setSalesPerDay(sales);
						//index가 salesList.size를 넘으면 값이 없는데 if문 실행되니.. 오류나서 임의로 +1해줌
						if(index + 1 < salesList.size()) {
							index++;
						}
					}
					else {
						//같지 않으면 0 넣음..
						vo.setSalesPerDay(0);
					}
				}
				else {
					//오류냐서 값에 0 넣음.. 
					vo.setSalesPerDay(0);
				}
				//resultList에 vo값을 넣어줌
				resultList.add(vo);
			}
			return resultList; //WEB-INF/view/list.do.jsp
		}
		
		//상품상태변경
		@ResponseBody
		@RequestMapping(value = "/selectYear.ad")
		public List<SalesVO> selectYear(SalesVO salesVO) {
			//실제 매출이 발생한 월별 매출액 리스트
			List<SalesVO> salesList = shopService.selectSales(salesVO);
			//1~12월까지 정리된 매출액 리스트
			List<SalesVO> resultList = new ArrayList<>();

			//1~12월까지 전부 0원으로 매출액을 세팅. list 12개 배열 만듬
			for(int i = 0; i < 12; i++) {
				SalesVO vo = new SalesVO();
				String buyMonth = String.format("%02d", i + 1);
				vo.setBuyMonth(buyMonth); 
				vo.setSalesPerMonth(0);
				resultList.add(vo);
			}
			for(int i = 0 ; i < salesList.size() ; i++) {
		         for(int j = 0 ; j < resultList.size() ; j++) {
		            if(salesList.get(i).getBuyMonth().equals(resultList.get(j).getBuyMonth())) {
		               resultList.get(j).setSalesPerMonth(salesList.get(i).getSalesPerMonth());
		            }
		         }
		      }

			return resultList; //WEB-INF/view/list.do.jsp
		}
	
	//파일첨부 메소드
		public List<String> attachFile(MultipartHttpServletRequest multi) throws Exception {
			//원본 첨부파일이름이 담길 변수
			String originFileName = "";
			//현재 시간 데이터를 담을 변수
			String nowDate = getNowDateTime();
			//return시킬 첨부파일명이 담긴 리스트 생성
			List<String> fileList = new ArrayList<>();
			
			//첨부파일에 대한 encoding 설정
			multi.setCharacterEncoding("UTF-8");
			//multipartRequest에서 첨부파일에 대한 데이터를 받아온다.
			Iterator<String> enu = multi.getFileNames();
			//첨부파일데이터가 존재하면 첨부파일 개수만큼 반복한다.
			while(enu.hasNext()) {
				//첫번째 파일 이름이뭔지 알려줘(첫번째부터 차례대로 꺼내며 반복)
				String fileName = enu.next();
				
				//단일 첨부파일 일때
				if(fileName.equals("file1")) {
					//실제 파일명(서버에 업로드 되는 파일명)
					String realName = "";
					//실제로 빼와서 (하나씩 빼옴)
					MultipartFile mf = multi.getFile(fileName);
					//실제이름을 찾는다. 그리고 넣어줌
					originFileName = mf.getOriginalFilename();
					//실제 파일명에 현대날짜_원본파일 형태의 이름으로 지정
					realName = nowDate + "_" + originFileName;
//					File file = new File("D:\\uploadFile\\bookStore\\" + fileName);
					File file = new File("D:\\uploadFile\\bookStore\\");
					//첨부파일의 용량이 0이 아니면
					if(mf.getSize() != 0) {
						if(!file.exists()) {
							file.mkdirs();
						}
						//해당하는 경로에 첨부파일을 업로드한다.
						mf.transferTo(new File("D:\\uploadFile\\bookStore\\" + realName));
						//server.xml 에 넣어줘야한다.
						//<Context docBase="D:/uploadFile/bookStore/" path="/upload" reloadable="true"/>
						fileList.add(realName);
					}
				}
				//다중 첨부파일 일때
				else if(fileName.equals("file2")) {
					List<MultipartFile> mfList = multi.getFiles(fileName);
					String realName = "";
					
					for(MultipartFile mf : mfList) {
						originFileName = mf.getOriginalFilename();
						realName = nowDate + "_" + originFileName;
						File file = new File("D:\\uploadFile\\bookStore\\");
						//첨부파일의 용량이 0이 아니면
						if(mf.getSize() != 0) {
							if(!file.exists()) {
								file.mkdirs();
							}
							//해당하는 경로에 첨부파일을 업로드한다.
							mf.transferTo(new File("D:\\uploadFile\\bookStore\\" + realName));
							fileList.add(realName);
						}
					}
				}
			}
			return fileList;
		}
		//현재 날짜 및 시분초 가져오는 메소드
		public String getNowDateTime() {
			Calendar cal = Calendar.getInstance();
			
			String year = cal.get(Calendar.YEAR) + "";
			String month = cal.get(Calendar.MONTH) + 1 + "";
			String day = cal.get(Calendar.DAY_OF_MONTH) + "";
			String hour = cal.get(Calendar.HOUR) + "";
			String minute = cal.get(Calendar.MINUTE) + "";
			String second = cal.get(Calendar.SECOND) + "";
			
			return year + month + day + hour + minute + second;
		}
		
		
}







