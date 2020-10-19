/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	initValidate();
	//클릭 이벤트
	//$(document).on('click', '선택자', function() {

	//});
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	//validate 초기화 함수
	initValidate = function(){
		$("#goodsForm").validate({
			//joinform id 유효성 검사
	        //테스트를 위하여 유효성 검사가 완료되어도 submit을 처리하지 않음.
	        debug : false,
	        //유효성체크없이 무조건 submit
	        onkeyup:false,
	        //입력태그에 focus가 들어왔을때 유효성을 검사한다.
	        onfocusin: false,
	        //focus가 out은 입력한거 다하고 다른거 이동할때 유효성 검사를 하겠다. ex)id치고 password로 갈때.. 안할때는 즉,submit 할때만 하고싶으면 위와같이 false하면됨
	        onfocusout: function(element) {
	        	$(element).valid();
	        },
	        //검사할 필드와 검사 항목의 나열
	//        groups:{
	//           socialNum : 'socialNum1 socialNum2',
	//           emergencyPhoneNum : 'emergencyPhoneNum2 emergencyPhoneNum3',
	//           phoneNum : 'phoneNum2 phoneNum3'
	//        },
	        
	        rules : {
	          // className:{					//name값 적어주면됨
	          //    required : true             //필수여부 지정 (null체크)
	                //digits : true,               //숫자만 입력 지정
	               // email : true,                //이메일만 입력 지정
	               // url : true,                  //url만 입력 지정
	               // minlength : 4,               //최소길이 지정
	               // maxlength : 8,               //최대길이 지정
	               // equalTo : "대상요소의 id 값" //특정 요소와 입력값이 같은지 여부 검사
	        	   // regx:/^[a-zA-Z0-9]*$/i //영어&숫자만 현재 잘안됨
	          // },
	           goodsName:{
	              required : true,
	              minlength : 1
	           },
	           goodsPrice:{
	        	   required : true,
	        	   digits: true
	        	},
	        	goodsDeliveryPrice:{
	        		required : true,
	        		digits: true
	        	}
	        },
	        //검사를 충족하지 못할 경우 표시될 메시지의 나열                                                         
	        messages : {
	          // className : "과정명을 입력하세요.",
	          // className : {
	          //      required : "필수 입력 항목 입니다.",
	           //     number : "숫자만 입력하세요.",
	          //      minlength : "최소 {0}글자 입니다.",
	           //     maxlength : "최대 {0}글자 입니다.",
	           //     equalTo : "입력이 잘못되었습니다."
			   //	  regx:'영어와 숫자만 사용가능합니다.'
	           // },
	           //memberId:'ID를 입력하세요',
	        	goodsName:{
	              required: 'ID는 필수항목입니다.',
	              minlength:'ID는 1자 이상 입력하세요.',
	           },
	           goodsPrice:{
	        	   required: '가격은 필수항목입니다.',
	        	   digits:'숫자만 입력하세요.'
	           },
	           goodsDeliveryPrice:{
	        	   required: '배송비는 필수항목입니다.',
	        	   digits:'숫자만 입력하세요.'
	           }
		        
	        },
	      //실패한 요소에 대해서 
	           highlight: function(element) {
	              //bootstrap의 validate css를 적용
	              $(element).closest('.form-group').removeClass('has-success has-feedback');
	               $(element).closest('.form-group').addClass('has-error has-feedback');
	               $(element).next().remove();
	               $(element).after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
	           },
	           //성공한 요소에 대해서
	           unhighlight: function(element) {
	             //bootstrap의 validate css를 적용
	               $(element).closest('.form-group').removeClass('has-error has-feedback');
	               $(element).closest('.form-group').addClass('has-success has-feedback');
	               $(element).next().remove();
	               $(element).after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
	           },
	        
	        //유효성 검사 실패 시 메세지의 출력 방식을 설정
	        errorPlacement: function(error, element){
	        	error.insertAfter($(element).closest('.input-group'));
	           //
	        },
	        //유효성 검사 완료(성공) 후 실행할 코드
	        submitHandler: function(form) {
	        	form.submit();
	        // $( "#dialog-confirm" ).dialog( "open" );
	        }
		});
		
	};
	//};
})(jQuery);