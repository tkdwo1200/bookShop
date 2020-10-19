/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	//구매상품 수량 변경시
	$(document).on('change keyup click', '.buyCnt', function() {
		//class나 id 주면 절대안됨 여러개 해버리면 중복으로 안되기때문에 아래와 같이 해야함!!!!!!!!!!!!!! ex)for문 같은거
		//총가격 계산 : 단가 * 수량 + 배송비
		var goodsPrice = $(this).parent().prev().prev().text();
		var cnt = $(this).val();
		var goodsDeliveryPrice = $(this).parent().next().text();
		var price = goodsPrice * cnt + parseInt(goodsDeliveryPrice);
		$(this).parent().next().next().text(price);
		$('.totalPrice').text(price);
		//수량이 변경될때마다 value값을 바꿀것이다.
		$('#hiddenOrderPrice').val(price);
		
	});
	
	//바로 구매하기 버튼 클릭 시
	$(document).on('click', '#buyBtn', function() {
		//buyForm의 클릭을 하면 submit을 함
		$('#buyForm').submit();
		
	});
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	
	//};
})(jQuery);