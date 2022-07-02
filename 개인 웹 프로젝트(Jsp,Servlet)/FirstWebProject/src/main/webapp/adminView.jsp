<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% if(session.getAttribute("ValidMem") == null) {%>
	<jsp:forward page="loginView.do"></jsp:forward>
<% 
	} 
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
%>
	<c:if test = "${'admin' ne (sessionScope.id)}">
      <jsp:forward page="mainView.do"></jsp:forward>
 	</c:if>
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
function seletAll()	{
	$.ajax({
		url : "SelectAllMemberService.do",
		dataType:"text",
		success : function(data) {
			console.log(data);
			
			$("#div100").html(data);
		}	
	});
}

function selectOne()	{
	
	var searchName= $('#searchName').val();
	console.log(searchName);
	
	$.ajax({
		url : "SelectAllMemberService.do",
		dataType:"text",
		success : function(data) {
			console.log(data);
			var Jsondata = JSON.parse(data);
			console.log(Jsondata);
			
			
			for(key in Jsondata){
				console.log(Jsondata[key].id + Jsondata[key].pw + Jsondata[key].name 
				         +Jsondata[key].eMail + Jsondata[key].rDate + Jsondata[key].address );
				
				if(Jsondata[key].id==searchName){
					
					var data_str = JSON.stringify(Jsondata[key]) 
					$("#div100").html(data_str);
				}	
			}
			
		}	
	});
}

function doStop()	{
	
	 var queryString=$("#stoplogin").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "StopMemberService.do",
		data : queryString,
		dataType:"text",
		success : function(data) {
			alert(data);
		}	
	});
}

function banMember()	{
	
	 var queryString=$("#deletemember").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "BanMemberService.do",
		data : queryString,
		dataType:"text",
		success : function(json) {
			console.log(json);
          	var result =JSON.parse(json);
            
            if(result.code=="success"){
            	alert(result.desc)
            }else{
            	alert(result.desc);
            	
            }
		}	
	});
}

function fmostWrite()	{
	
	 var queryString=$("#fm100").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "FmostWriterService.do",
		data : queryString,
		dataType:"text",
		success : function(json) {
			console.log(json);
			$("#div100").html(json);
		}	
	});
}

function fmostReply()	{
	
	 var queryString=$("#fm200").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "FmostReplyService.do",
		data : queryString,
		dataType:"text",
		success : function(json) {
			console.log(json);
			$("#div100").html(json);
		}	
	});
}

function imostWrite()	{
	
	 var queryString=$("#fm300").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "ImostWriterService.do",
		data : queryString,
		dataType:"text",
		success : function(json) {
			console.log(json);
			$("#div100").html(json);
		}	
	});
}

function imostReply()	{
	
	 var queryString=$("#fm400").serialize();
	 console.log(queryString);
	
	$.ajax({
		url : "ImostReplyService.do",
		data : queryString,
		dataType:"text",
		success : function(json) {
			console.log(json);
			$("#div100").html(json);
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
    
		<h1>관리자 페이지!!!</h1>
		
		<span class='text-info'>전체회원조회</span>
		<input type="button" class="btn btn-outline-info" value="전체회원조회" onclick="seletAll()"><br><hr>
		
		<form id="search">
		<span class='text-info'>개인회원조회 ID</span> : 
		<input id="searchName" type="text" name="searchName" >
		<input type="button" class="btn btn-outline-info" value="개별회원조회" onclick="selectOne()"><br><hr>
		</form>
		
		<form id="stoplogin">
		<span class='text-warning'>정지할 회원 ID</span> : 
		<input type="text" name="id" >
		정지기간 : <input type="text" name="stoplogindate">일
		<input type="button" class="btn btn-outline-warning" value="회원로그인정지" onclick="doStop()"><br><hr>
		</form>
		
		<form id="deletemember">
		<span class='text-danger'>강퇴할 회원 ID</span> : 
		<input type="text" name="id">
		<input type="button" class="btn btn-outline-danger" value="회원강퇴" onclick="banMember()"><br><hr>
		</form>
		
		<form id="fm100">
		<span class='text-primary'>자유게시판 게시글</span>을 많이 단 회원 
		<input type="text" name="date"> 일 동안
		<input type ="button" class="btn btn-outline-info" value="회원조회" onclick="fmostWrite()">
		</form>
		
		<form id="fm200">
		<span class='text-primary'>자유게시판 댓글</span>을 많이 단 회원 
		<input type="text" name="date"> 일 동안
		<input type ="button" class="btn btn-outline-info" value="회원 조회" onclick="fmostReply()"><br><hr>
		</form>
		
		<form id="fm300">
		<span class='text-primary'>정보게시판 게시글</span>을 많이 단 회원 
		<input type="text" name="date"> 일 동안
		<input type ="button" class="btn btn-outline-info" value="회원조회" onclick="imostWrite()">
		</form>
		
		<form id="fm400">
		<span class='text-primary'>정보게시판 댓글</span>을 많이 단 회원 
		<input type="text" name="date"> 일 동안
		<input type ="button" class="btn btn-outline-info" value="회원 조회" onclick="imostReply()"><br><hr>
		</form>
		
		<div id='div100' class="bg-warning"></div>
		
		
		<!-- 여기서부터 복사 -->
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>