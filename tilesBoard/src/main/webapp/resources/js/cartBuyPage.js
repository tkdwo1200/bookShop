/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	initTotalPrice();
	
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	initTotalPrice = function(){
		var totalPrice = 0;
		$('.totalPriceTd').each(function(index,element){
			var price = parseInt($(element).text());
			totalPrice += price;
		});
		$('#totalPrice').text(totalPrice);
	};
})(jQuery);