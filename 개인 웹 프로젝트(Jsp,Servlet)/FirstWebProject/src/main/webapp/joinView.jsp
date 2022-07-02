<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<meta name="google-signin-client_id" content="605800980482-t40k0cc846ebnl8r1u51cfu6867rh23t.apps.googleusercontent.com">
<script>

function form_check(){
	if($('#validationCustom01').val().length == 0){
		alert("아이디는 필수사항입니다.");
		$('#validationCustom01').focus();
		return;
	}
	
	if($('#validationCustom01').val().length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		$('#validationCustom01').focus();
		return;
	}
	
	if($('#validationCustom02').val().length == 0){
		alert("비밀번호는 필수사항입니다.");
		$('#validationCustom02').focus();
		return;
	}
	
	if($('#validationCustom02').val().length < 4){
		alert("비밀번호는 4글자 이상이어야 합니다.");
		$('#validationCustom02').focus();
		return;
	}
	
	if($('#validationCustom02').val() != $('#validationCustomUsername').val()){
		alert("비밀번호가 일치하지 않습니다..");
		$('#validationCustom02').focus();
		return;
	}
	
	if($('#validationCustom03').val().length == 0){
		alert("이름은 필수 사항입니다.");
		$('#validationCustom03').focus();
		return;
	}
	if($('#validationCustom04').val().length == 0){
		alert("이메일은 필수 사항입니다.");
		$('#validationCustom04').focus();
		return;
	}
	
	var form01Data = $("#JoinForm").serialize();
	console.log(form01Data);
	$.ajax({
		url : "naverCaptcha.jsp",
		data : form01Data,
		success : function(data) {
			console.log(data);
			if(data=="true"){
				submit_ajax();
			}else{
				alert("보안코드를 다시입력해주세요");
			}
		}
	});
}

function submit_ajax() {
    var queryString=$("#JoinForm").serialize();
    $.ajax({
        url : '/FirstWebProject/join.do',
        type : 'POST',
        data : queryString,
        dataType: 'text',
        success : function(json) {
            console.log(json);
            var result =JSON.parse(json);
            if(result.code=="success"){
            	alert(result.desc)
            	window.location.replace('mainView.do');
            }else{
            	alert(result.desc);
            }
        }
    });
}

function idCheck() {
	var queryString=$("#validationCustom01").serialize();
    $.ajax({
        url : '/FirstWebProject/idCheck.do',
        type : 'POST',
        data : queryString,
        dataType: 'text',
        success : function(json) {
            console.log(json);
            var result =JSON.parse(json);
            alert(result.desc)
        }
    });
}

function codeReset()	{
	$.ajax({
		url : "naverCaptcha.jsp",
		dataType:"json",
		success : function(data) {
			console.log(data.key);
			$("#key").val(data.key);
			$("#div01").html("<img src='captchaImage//"+data.captchaImageName+"'>");
		}
	});
}

</script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "naverCaptcha.jsp",
			dataType:"json",
			success : function(data) {
				console.log(data.key);
				$("#key").val(data.key);
				$("#div01").html("<img src='captchaImage//"+data.captchaImageName+"'>");
			}
		});
	});
</script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="mainView.do">WooJ</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      	<br><br>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="aListView.do">공지사항 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" onclick="javascript:window.location='fListView.do'">자유게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="iListView.do">정보게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="chatView.do">채팅</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="toGoCompany.do">찾아오는 길</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="loginView.do">로그인</a>
            </li>
            
            <li>
            <div></div>
				<form action="logoutOk.do" method="post">
					<input type="submit" class="btn btn-outline-secondary" value="로그아웃">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-outline-secondary" value="정보수정"
						   onclick="javascript:window.location='modifyView.do'">
					<input type="button" class="btn btn-outline-secondary" value="회원탈퇴" onclick="javascript:window.location='withdrawView.do'">
					
				</form>
            </li>
          </ul>
        </div>
      </nav>
      
<br><br>
    <div class="row justify-content-md-center">
    <div class="col-md-8 test2">
    
     <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
          'use strict';
          window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
              form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                  event.preventDefault();
                  event.stopPropagation();
                }
                form.classList.add('was-validated');
              }, false);
            });
          }, false);
        })();
     </script>
        
    <h1>회원가입</h1>
   <form id="JoinForm" class="needs-validation" novalidate>
    <div class="form-row">
      <div class="col-md-4 mb-3">
        <label for="validationCustom01">아이디</label>
        <input type="text" class="form-control" name="id" id="validationCustom01" placeholder="enter Id"  required>
        <div class="valid-feedback">
          <input type="button" class="btn btn-outline-secondary" value="중복확인" onclick="idCheck()">
        </div>
        <div class="invalid-feedback">
          <input type="button" class="btn btn-outline-secondary" value="중복확인" onclick="idCheck()">
        </div>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationCustom02">비밀번호</label>
        <input type="password" class="form-control" name="pw" id="validationCustom02" placeholder="enter password" required>
        <div class="valid-feedback">
          입력하셨습니다.
        </div>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationCustomUsername">비밀번호확인</label>
          <input type="password" class="form-control" name="pw_check" id="validationCustomUsername" placeholder="enter password for confirm" required>
          <div class="valid-feedback">
            입력하셨습니다.
          </div>
      </div>
    </div>
    <div class="form-row">
      <div class="col-md-3 mb-3">
        <label for="validationCustom03">이름</label>
        <input type="text" class="form-control" name="name" id="validationCustom03" placeholder="enter name" required>
        <div class="invalid-feedback">
          이름을 입력해주세요.
        </div>
      </div>
      <div class="col-md-5 mb-3">
        <label for="validationCustom04">메일</label>
        <input type="text" class="form-control" name="eMail" id="validationCustom04" placeholder="enter email" required>
        <div class="invalid-feedback">
          메일을 입력해주세요.
        </div>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationCustom05">주소</label>
        <input type="text" class="form-control" name="address" id="validationCustom05" placeholder="enter address" required>
        <div class="invalid-feedback">
          주소를 입력해주세요.
        </div>
      </div>
    </div>
    <div class="form-group">
      <div id="div01">
      </div>
      <input type="button" class="btn btn-outline-secondary" value="보안코드새로고침" onclick="codeReset()"><br>
      <input type="hidden" id="key" name="key"><br>
      <div class="col-md-4 mb-4">
        <label for="validationCustom06">보안코드입력</label>
        <input type="text" class="form-control" name="value" id="validationCustom06" placeholder="enter code" required>
        <div class="invalid-feedback">
          보안코드를 입력하세요.
        </div>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
        <label class="form-check-label" for="invalidCheck">
          회원가입 동의
        </label>
        <div class="invalid-feedback">
          동의화면에 체크해주세요.
        </div>
      </div>
    </div>
    <input type="button" value="회원가입" class="btn btn-primary" onclick="form_check()">
    
  </form>
	
	
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>