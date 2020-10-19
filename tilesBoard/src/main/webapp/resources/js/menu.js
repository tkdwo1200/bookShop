/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//validate에 정규식 사용하게 설정
	//$.validator.addMethod('regx', function(value, element, regexpr){
	//   return regexpr.test(value);
	//});
	//validate(유효성 검사)
	//아래의 정규식은 /정규식/i 형태로 작성!!!!!!!!
	//1) 숫자만 : ^[0-9]*$
	//2) 영문자만 : ^[a-zA-Z]*$
	//3) 한글만 : ^[가-힣]*$
	//4) 영어 & 숫자만 : ^[a-zA-Z0-9]*$
	//5) E-Mail : ^[a-zA-Z0-9]+@[a-zA-Z0-9]+$
	//6) 휴대폰 : ^01(?:0|1|[6-9]) - (?:\d{3}|\d{4}) - \d{4}$
	//7) 일반전화 : ^\d{2,3} - \d{3,4} - \d{4}$
	//8) 주민등록번호 : \d{6} \- [1-4]\d{6}
	//9) 영문&숫자&특수문자 : ^(?=.[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$  - 지금 잘안됨
	
	//logout 클릭시
	$(document).on('click', '#logoutSpan', function() {
		alert('안전하게 로그아웃 되었습니다.');
		location.href="logout.me";
	});
	
	//클릭 이벤트 join Modal 열릴때 실행(모달이 닫힐때:hidden.bs.modal)
	$(document).on('show.bs.modal', '#joinModal', function() {
		initJoinModal();
	});
	
	//클릭 이벤트 join Modal 닫힐때:hidden.bs.modal)
	$(document).on('hidden.bs.modal', '#joinModal', function() {
		
		//가장 먼저 찾는 form값을 찾아서 reset시켜줌
		$('#joinModal').find('form')[0].reset();
		//validate된 label내용 삭제
		$('#joinForm label.error').remove();
		
	});
	
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	initJoinModal = function() {

		$("#joinForm").validate({
			//joinform id 유효성 검사
	        //테스트를 위하여 유효성 검사가 완료되어도 submit을 처리하지 않음.
	        debug : false,
	        //유효성체크없이 무조건 submit
	        onsubmit:true,
	        //키를 클릭하고 놓을때 발생
	        onkeyup:false,
	        //입력태그에 focus가 들어왔을때 유효성을 검사한다.
	        onfocusin: false,
	        //focus가 out은 입력한거 다하고 다른거 이동할때 유효성 검사를 하겠다. ex)id치고 password로 갈때.. 안할때는 즉,submit 할때만 하고싶으면 위와같이 false하면됨
	        onfocusout: function(element) { $(element).valid(); },
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
	          // },
	           memberId:{
	              required : true,
	              minlength : 2,
	             maxlength : 10,
	             regx:/^[a-zA-Z0-9]*$/i //영어&숫자만
	           },
	           memberPassword:{
	        	   required : true,
	        	   minlength : 4,
	        	   maxlength : 20,
	             regx:/^[a-zA-Z0-9]*$/i //영어&숫자만 현재 잘안됨
	        	},
	           memberPassword1:{
	        	   required : true,
	        	   minlength : 4, 
	        	   maxlength : 20,
	        	   equalTo : memberPassword,
	             regx:/^[a-zA-Z0-9]*$/i //영어&숫자만
	           },
	        	memberName:{
	        		required : true,
	        		minlength : 2, 
	        		maxlength : 16,
	        	},
	           tel1:{
	        	   required : true,
	           },
	        	tel2:{
	        		required : true,
	        	},
	        	email:{
	        		required : true,
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
	           // },
	           //memberId:'ID를 입력하세요',
	           memberId:{
	              required: 'ID는 필수항목입니다.',
	               minlength:'ID는 2자 이상 입력하세요.',
	               maxlength:'ID는 10자를 초과할 수 없습니다.',
	               regx:'영어와 숫자만 사용가능합니다.'
	           },
		        memberPassword:{
		        	required: 'PW는 필수항목입니다.',
		        	minlength:'PW는 4자 이상 입력하세요.',
		        	maxlength:'PW는 20자를 초과할 수 없습니다.',
		            regx:'영어와 숫자만 사용가능합니다.'
		        },
	           memberPassword1:{
	        	   required: 'PW는 필수항목입니다.',
	        	   minlength:'PW는 4자 이상 입력하세요.',
	        	   maxlength:'PW는 20자를 초과할 수 없습니다.',
	        	   equalTo:'PW가 다릅니다.',
	        	   regx:'영어와 숫자만 사용가능합니다.'
	           },
		        memberName:{
		        	required: 'NAME은 필수항목입니다.',
		        	minlength:'NAME은 2자 이상 입력하세요.',
		        	maxlength:'NAME은 16자를 초과할 수 없습니다.',
		        },
		        tel1:{
		        	required: 'PHONE1은 필수항목입니다.',
		        },
		        tel2:{
		        	required: 'PHONE1은 필수항목입니다.',
		        },
		        email:{
		        	required: 'EMAIL은 필수항목입니다.',
		        },
		        
	        },
	        //유효성 검사 실패 시 메세지의 출력 방식을 설정
	        errorPlacement: function(error, element){
	           error.insertAfter(element);
	        },
	        //유효성 검사 완료(성공) 후 실행할 코드
	        submitHandler: function(form) {
	        	form.submit();
	        // $( "#dialog-confirm" ).dialog( "open" );
	        }
		});
	}
	//};
})(jQuery);