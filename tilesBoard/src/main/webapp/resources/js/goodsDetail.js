/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	//장바구니버튼 클릭시
	$(document).on('click', '#cartBtn', function() {
		insertCart();
		
	});
	
	//구매하기 버튼 클릭 시
	$(document).on('click', '#buyBtn', function() {
		var memberId = $('#loginId').val();
		
		//모달창에 가격정보를 세팅
		var cnt = $('input[type="number"]').val();//수량
		var goodsPrice = $('#goodsPrice').text();//단가
		var goodsDeliveryPrice = $('#goodsDeliveryPrice').text();//배송비
		var price = goodsPrice * cnt + parseInt(goodsDeliveryPrice);
//		alert(cnt);
//		alert(goodsPrice);
//		alert(goodsDeliveryPrice);
		$('#modalPrice').text(price);
		
		//미로그인시 
		if(memberId == ''){
			//로그인 하라는 alert
			alert('구매를 하시려면 로그인을 하세요.');
			
		}
		
		else {
			//구매정보 모달창 띄움
			$('#buyModal').modal();
			
		}
		
	});
	
	//구매시
	$(document).on('click', '#modalBuyBtn', function() {
		var goodsId = $('#goodsId').val();
		var cnt = $('input[type="number"]').val();
		location.href = 'buyPage.sh?goodsId=' + goodsId + '&cnt=' + cnt;
	});
	
});

/* 함수선언 영역*/
(function($){
	//장바구니 담기
	insertCart = function(){
		var goodsCnt = $('#goodsCnt').val();
		var goodsId = $('#goodsId').val();
		
	//Ajax 시작
	$.ajax({
	      url: 'insertCart.sh', //요청경로
	      type: 'post',
	      data: {'goodsId':goodsId, 'goodsCnt':goodsCnt}, //요청경로로 던질 파라메터. '파레메터명':파라메터
	      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
	    	  var isConfirm = confirm('해당 상품을 장바구니에 담았습니다\n장바구니로 이동할까요?')
	  		if(isConfirm){
	  			var memberId = $('#memberId').val();
	  			location.href = 'selectCartList.sh?memberId=' + memberId;
	  		}
	  		else{
	  			alert('실패');
	  			
	  		}
	  		
	      },
	      error: function(){ //ajax통신 실패 시 실행부분
	    	  alert('로그인이 필요합니다. ');
	      }
	});
	
	};
})(jQuery);