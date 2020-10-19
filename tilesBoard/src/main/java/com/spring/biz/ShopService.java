package com.spring.biz;

import java.util.List;

import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartListVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.CategoryVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;

public interface ShopService {
	//interface 에는 public으로 오는거라서 생략하는거다.
	
	//상품 카테고리 리스트 조회
	List<CategoryVO> selectCategoryList();
	//상품등록
	void insertGoods(GoodsVO goodsVO, ImageVO imageVO);
	//상품 첨부파일 이미지
	int insertImage(ImageVO imageVO);
	//상품ID
	int getMaxId();
	//상품정보 리스트
	List<GoodsVO> selectGoodsList(GoodsVO goodsVO);
	//상품상세정보
	GoodsVO goodsDetail(GoodsVO goodsVO);
	//이미지 상세보기(여러장)
	List<GoodsVO> goodsImageList(ImageVO imageVO);
	
	//상품관리조회(admin)
	List<GoodsVO> selectGoodsListForManage(GoodsVO goodsVO);
	//상품 상태변경(admin)
	int updateGoodsStatus(GoodsVO goodsVO);
	
	
	//상품구매 목록조회(admin)
	List<BuyVO> selectBuyList(SearchVO searchVO);
	
	int updateIsConfirm(int orderNum);
	
	//상품구매
	int insertBuy(BuyVO buyVO);
	
	//상품 장바구니
	int insertCart(CartVO cartVO);
	
	//상품 장바구니 조회
	List<CartListVO> selectCartList(CartListVO cartListVO);
	
	void deleteCart(CartVO cartVO);
	
	void updateCartCnt(CartListVO cartListVO);
	
	//상품 구매
	List<CartListVO> selectCartBuyList(CartVO cartVO);
	
	void insertBuyList(BuyVO buyVO, CartVO cartVO);
	
	void updateCartIsDelete(CartVO cartVO);
	
	List<SalesVO> selectSales(SalesVO salesVO);
	
	List<SalesVO> selectYear(SalesVO salesVO);
	
	List<SalesVO> selectSalesListPerMonth(String yearMonth);
}




















