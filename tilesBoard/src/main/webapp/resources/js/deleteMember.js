/* 페이지 로딩 후 실행 */
$(document).ready(function(){
   //클릭 이벤트
   $(document).on('click', '#deleteMemberBtn', function() {
      var inputPassword = $('#inputPassword').val();
      var memberId = $('#loginId').val();
      var loginPassword = $('#loginPassword').val();
      
      if(memberId == null || memberId == "") {
         alert('로그아웃 상태입니다. \n탈퇴를 하시려면 로그인 해주세요. \n메인페이지로 이동합니다.');
         location.href = 'shopList.sh';
      }
      else{
         if(inputPassword == loginPassword) {
            var isConfirm = confirm('정말 탈퇴 하시겠습니까?');
            
            if(isConfirm){
               location.href = 'updateYNdeleteMember.me?memberId=' + memberId + '&memberPassword=' + inputPassword + '&mainMenu=member&subMenu=deleteMember';
            }
         }else if(inputPassword == "" || inputPassword == null){
            alert('비밀번호를 입력하세요.')
            location.href = 'deleteMember.me?mainMenu=member&subMenu=deleteMember';
         }else{
            alert('비밀번호가 틀렸습니다. \n다시 확인하시어 입력해주세요.');
            location.href = 'deleteMember.me?mainMenu=member&subMenu=deleteMember';
         }
      }
   });
});

/* 함수선언 영역*/
(function($){
   //aaa라는 함수선언
   //aaa = function(){
   
   //};
})(jQuery);