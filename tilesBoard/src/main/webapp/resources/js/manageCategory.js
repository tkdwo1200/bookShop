/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	//클릭 이벤트
	$(document).on('click', '.delBtn', function() {
		var result = confirm('삭제할거에요?');
		if(result){
			var category = $(this).attr('data-categoryCode');
			location.href = 'deleteCategory.ad?categoryCode=' + categoryCode;
		}

	});
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	
	//};
})(jQuery);