/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//전체가격
	setTotalPrice();
	
	//전체체크박스 클릭 시
	$(document).on('click', '#checkAll', function() {
		//체크유무 확인 (true, false)
		var isChecked = $('#checkAll').is(':checked');
		
		if(isChecked){
			$('.chk').prop('checked', true);
		}
		else{
			$('.chk').prop('checked', false);
		}
		setTotalPrice();
	});
	
	//각상품의 체크박스 클릭 시
	$(document).on('click', '.chk', function() {
		setTotalPrice();
		
	});
	
	//장바구니 비우기 클릭 시
	$(document).on('click', '#deleteCartBtn', function() {
		var checkedCnt = $('.chk:checked').length;
		
		if(checkedCnt == 0){
			alert('장바구니에서 비울 상품을 선택하세요');
			return;
		}
		
		//cartId 가 담길 배열 생성
		var cartIds = new Array();
		
		//체크박스 중 체크가 된 노드의 value값(cartId)를 가져온다.
		$('.chk:checked').each(function(index,element){
			var cartId = $(element).val();
			cartIds[index] = cartId;
			
		});
		
		location.href = 'deleteCart.sh?cartIds=' + cartIds;
		
	});
	
	//수량 변경시
	$(document).on('change keyup', '.goodsCnt', function() {
		var cartId = $(this).attr('data-cartId');
		var goodsCnt = $(this).val();
		var goodsPrice = $(this).parent().prev().text();
		var totalPrice = goodsPrice * goodsCnt;
		var totalPriceNode = $(this).parent().next();
		
		updateGoodsCnt(cartId, goodsCnt, totalPrice, totalPriceNode);
		
	});
	//바로 구매하기 버튼 클릭시
	$(document).on('click', '#cartBuyBtn', function() {
		var cartIdArray = new Array();
		$('.chk:checked').each(function(index,element){
			var cartId = $(element).val();
			cartIdArray[index] = cartId;
		});
		
		location.href = 'cartBuyPage.sh?cartIdArray=' + cartIdArray;
		
	});
});

/* 함수선언 영역*/
(function($){
	//총금액을 계산하는 함수
	setTotalPrice = function(){
		var totalPrice = 0;
		
		//클래스가 chk인거중에 checked가 된것만 들고오겠다. each문의 문법(index, element)
		$('.chk:checked').each(function(index, element){
			//첫번째자식
//			$(element).parent().parent().children().eq(':first')
//			$(element).parent().parent().children().eq(0)
//			$(element).parent().parent().children(':first')
			var price = parseInt($(element).parent().parent().children(':last').text());
			totalPrice += price;
		});
		
		//text안에 () 안의값을 넣어주면 세팅됨
		$('.totalPriceSpan').text(totalPrice);
		
	};
	
	updateGoodsCnt = function(cartId, goodsCnt, totalPrice, totalPriceNode){
	
		
		//Ajax 시작
		$.ajax({
		      url: 'updateCartCnt.sh', //요청경로
		      type: 'post',
		      data: {'cartId':cartId, 'goodsCnt':goodsCnt }, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  $(totalPriceNode).text(totalPrice);
		    	  setTotalPrice();
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류!!!');
		      }
		});
		
	}
	
	
})(jQuery);