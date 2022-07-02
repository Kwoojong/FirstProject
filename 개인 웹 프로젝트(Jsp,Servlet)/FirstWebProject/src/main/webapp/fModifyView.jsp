<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") == null) {%>
	<jsp:forward page="loginView.do"></jsp:forward>
<% 
	} 
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
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

<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>


<script>

function form_check(){
	oEditors.getById["bContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
    submit_ajax();
}

function submit_ajax() {
    var queryString=$("#modify_form").serialize();
    var bId = $('#bId').val();
    $.ajax({
        url : '/FirstWebProject/fModify.do',
        type : 'POST',
        data : queryString,
        dataType: 'text',
        success : function(json) {
            window.location.replace('fContentView.do?bId='+bId);
        }
    });
}
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
	<table class="table">
    <form id="modify_form">
        <input type="hidden" id="bId" name="bId" value="${fContentView.bId}">
        <input type="hidden" id="id" name="id" value="${fContentView.id}">
    <tr> 
           <td>번호</td>
           <td>${fContentView.bId}</td>
       </tr>
       <tr>
            <td>히트</td>
            <td>${fContentView.bHit}</td>
        </tr>
        <tr>
        <td>아이디</td>
        <td>${fContentView.id}</td>
        </tr>
        <tr>
            <td>이름</td>
            <td name="bName" size="50">${fContentView.bName} </td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type="text" name="bTitle" value="${fContentView.bTitle}"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
            
            <textarea name="bContent" id="bContent" rows="10" cols="100">${fContentView.bContent}</textarea>
            <script type="text/javascript">
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
			    oAppRef: oEditors,
			    elPlaceHolder: "bContent",
			    sSkinURI: "./naver-editor/SmartEditor2Skin.html",
			    fCreator: "createSEditor2"
			});
			</script>
            </td>
            
        </tr>
        <tr>
            <td colspan="2">
               	<input type="button" class="btn btn-primary" value="수정완료" onclick="JavaScript:form_check();">  &nbsp;&nbsp;
                <a class="btn btn-primary" href="fContentView.do?bId=${fContentView.bId}">취소</a> &nbsp;&nbsp;
                <a class="btn btn-primary" href="fListView.do?page=<%=session.getAttribute("cpage")%>">목록보기</a> &nbsp;&nbsp;
              

            </td>
        </tr>
    </form>  
   </table>
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>