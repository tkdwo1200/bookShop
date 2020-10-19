package com.spring.biz;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartListVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.CategoryVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;

@Service("shopService") 
public class ShopServiceImpl implements ShopService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> selectCategoryList() {
		return sqlSession.selectList("selectCategoryList");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertGoods(GoodsVO goodsVO,ImageVO imageVO) {
		sqlSession.insert("insertGoods", goodsVO);
		insertImage(imageVO);
		
	}

	@Override
	public int insertImage(ImageVO imageVO) {
		return sqlSession.insert("insertImage", imageVO);
	}

	@Override
	public int getMaxId() {
		return sqlSession.selectOne("getMaxId");
	}

	@Override
	public List<GoodsVO> selectGoodsList(GoodsVO goodsVO) {
		return sqlSession.selectList("selectGoodsList", goodsVO);
	}

	@Override
	public GoodsVO goodsDetail(GoodsVO goodsVO) {
		return sqlSession.selectOne("goodsDetail", goodsVO);
	}

	@Override
	public List<GoodsVO> goodsImageList(ImageVO imageVO) {
		return sqlSession.selectList("goodsImageList", imageVO);
	}

	@Override
	public List<GoodsVO> selectGoodsListForManage(GoodsVO goodsVO) {
		return sqlSession.selectList("selectGoodsListForManage", goodsVO);
	}

	@Override
	public int updateGoodsStatus(GoodsVO goodsVO) {
		return sqlSession.update("updateGoodsStatus", goodsVO);
	}

	@Override
	public int insertBuy(BuyVO buyVO) {
		return sqlSession.insert("insertBuy", buyVO);
	}

	@Override
	public List<BuyVO> selectBuyList(SearchVO searchVO) {
		return sqlSession.selectList("goodsMapper.selectBuyList", searchVO);
	}

	@Override
	public int updateIsConfirm(int orderNum) {
		return sqlSession.update("updateIsConfirm", orderNum);
	}

	@Override
	public int insertCart(CartVO cartVO) {
		return sqlSession.insert("insertCart", cartVO);
	}

	@Override
	public List<CartListVO> selectCartList(CartListVO cartListVO) {
		return sqlSession.selectList("selectCartList", cartListVO);
	}

	@Override
	public void deleteCart(CartVO cartVO) {
		sqlSession.delete("deleteCart", cartVO);
		
	}

	@Override
	public void updateCartCnt(CartListVO cartListVO) {
		sqlSession.update("updateCartCnt", cartListVO);
		
	}

	@Override
	public List<CartListVO> selectCartBuyList(CartVO cartVO) {
		return sqlSession.selectList("selectCartBuyList", cartVO);
	}
    //구매페이지에서 여러상품 구매
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuyList(BuyVO buyVO, CartVO cartVO) {
    	sqlSession.insert("insertBuyList", buyVO);
    	updateCartIsDelete(cartVO);
   }
	//장바구니 is_delete업데이트
	@Override
	public void updateCartIsDelete(CartVO cartVO) {
		sqlSession.update("updateCartIsDelete", cartVO);
		
	}

	@Override
	public List<SalesVO> selectSales(SalesVO salesVO) {
		return sqlSession.selectList("selectSales", salesVO);
	}

	@Override
	public List<SalesVO> selectYear(SalesVO salesVO) {
		return sqlSession.selectList("selectYear", salesVO);
	}

	@Override
	public List<SalesVO> selectSalesListPerMonth(String yearMonth) {
		return sqlSession.selectList("selectSalesListPerMonth", yearMonth);
	}
	

}












