<%@page import="com.study.jsp.dto.MemberDto" %>
<%@page import="com.study.jsp.dao.MemberDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <%
    	String id = (String)session.getAttribute("id");
   	    String name = (String)session.getAttribute("name");
    	MemberDao dao = MemberDao.getInstance();
    	MemberDto dto = dao.getMember(id);
    %>
<!DOCTYPE html>
<html>
<head>
<!-- 여기서부터 복사 -->
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<!-- 여기까지 복사 -->
<script>
	function form_check(){
		if($('#pw').val() == ""){
			alert("패스워드를 입력하세요.");
			$('#pw').focus();
			return;
		}
		
		if($('#pw').val() != $('#pw_check').val()){
			alert("패스워드가 일치하지않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
	    submit_ajax();
	}
	
	function submit_ajax() {
	    var queryString=$("#ModifyForm").serialize();
	    $.ajax({
	        url : '/FirstWebProject/modify.do',
	        type : 'POST',
	        data : queryString,
	        dataType: 'text',
	        success : function(json) {
	            console.log(json);
	            var result =JSON.parse(json);
	            if(result.code=="success"){
	            	alert(result.desc)
	            	window.location.replace('mainView.jsp');
	            }else{
	            	alert(result.desc);
	            }
	        }
	    });
	}
</script>
</head>
<body>
<!-- 여기서부터 복사 -->
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
            <%= name %>님 안녕하세요.
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
    
    <!-- 여기 까지 복사 -->
	<form id="ModifyForm">
		아이디 : <%= dto.getId() %><br>
		비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
		이름 : <%= dto.getName() %><br>
		메일 : <input type="text" id="eMail" name="eMail" size="20" value="<%= dto.geteMail() %>"><br>
		주소 : <input type="text" id="address" name="address" size="50" value="<%= dto.getAddress() %>"><br>
		
		
		<input type="button" class="btn btn-outline-primary" value="회원정보수정" onclick="form_check()"> &nbsp;&nbsp;&nbsp;
		<input type="reset" class="btn btn-outline-primary" value="취소" onclick="javascript:window.location='mainView.do'">
	</form>
	
	<!-- 여기서부터 복사 -->
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>