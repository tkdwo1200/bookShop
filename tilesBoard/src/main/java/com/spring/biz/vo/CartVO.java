package com.spring.biz.vo;

import java.util.List;

public class CartVO extends BaseVO{
	
	private String cartId;
	private int goodsId;
	private String memberId;
	private String createDate;
	private int goodsCnt;
	private String isDelete;
	
	//pk값인 cartid를 list에 싹 넣어서 지울때 사용하려고 함
	private List<String> cartIdList;
	
	
	
	
	public List<String> getCartIdList() {
		return cartIdList;
	}
	public void setCartIdList(List<String> cartIdList) {
		this.cartIdList = cartIdList;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", goodsId=" + goodsId + ", memberId=" + memberId + ", createDate="
				+ createDate + ", goodsCnt=" + goodsCnt + ", isDelete=" + isDelete + ", cartIdList=" + cartIdList + "]";
	}
	
	
}










