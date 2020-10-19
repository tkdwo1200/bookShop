package com.spring.biz;

import java.util.List;

import com.spring.biz.vo.BuyListVO;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.MemberVO;

public interface MemberService {
	
	int insertMember(MemberVO memberVO);
	
	MemberVO login(MemberVO memberVO);
	
	   //회원탈퇴
	   int updateYNdeleteMember(MemberVO memberVO);
	   
	   
	List<String> selectBuyDateList(String memberId);
	
	List<BuyListVO> selectBuyList(BuyVO buyVO);
	
}




















