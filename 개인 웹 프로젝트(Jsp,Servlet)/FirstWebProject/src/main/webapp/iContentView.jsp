<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%
    String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
    %>
    
<!DOCTYPE html>
<html>
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

function likeService(){
	var bId = $('#bId').val();
	var queryString = "bId="+bId;
	console.log(queryString);
	$.ajax({
		url :'/FirstWebProject/iLike.do',
		type : 'POST',
		data : queryString,
		success : function(json) {
			
			window.location.replace('iContentView.do?bId='+bId);
		}
	});	
}


function form_check(){
	 submit_ajax();
}

function submit_ajax() {
    var queryString=$("#reply_form").serialize();
    console.log(queryString);
    var bId = $('#bId').val();
    $.ajax({
        url : '/FirstWebProject/iReply.do',
        type : 'POST',
        data : queryString,
        dataType: 'text',
        success : function(json) {
            console.log(json);
            
            window.location.replace('iContentView.do?bId='+bId);
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
<!-- 글 내용 -->
	<table class="table table-bordered">
       <tr>
           <th class="table-active">글번호</th>
           <td>${fContentView.bId}</td>
       </tr>
       <tr>
            <th class="table-active">조회수</th>
            <td>${fContentView.bHit}</td>
        </tr>
        <tr>
            <th class="table-active">이름</th>
            <td>${fContentView.bName}</td>
        </tr>
        <tr>
            <th class="table-active">제목</th>
            <td >${fContentView.bTitle}</td>
        </tr>
        <tr>
            <th class="table-active">제목</th>
            <td >${fContentView.bTitle}</td>
        </tr>
        <tr>
            <th class="table-active">파일</th>
            <td>
            <%
            	String file = (String)request.getAttribute("downloadview");
            	String directory = application.getRealPath("/fileFolder/");
            	
       			out.write("<a href=\"" + request.getContextPath() + "/DownloadAction?file=" +
            					java.net.URLEncoder.encode(file,"UTF-8") + "\">" +file+"</a><br>");
            %>
            	
            </td>
        </tr>
        <tr>
            <th class="table-active">내용</th>
            <td>${fContentView.bContent}</td>
        </tr>
        <tr>
        	<th class="table-active">좋아요</th>
        	<td>
        	<button type="button" class="btn btn-primary" onclick="likeService()">좋아요&nbsp;&nbsp; 
        	<span class="badge badge-light">${fContentView.bLike}</span></button>
        	</td>
        </tr>
        	<div class="row">
        	<div class="col-8"></div>
        	<div class="col-4">
                <a href="iModifyView.do?bId=${fContentView.bId}">수정</a>&nbsp;&nbsp;
                <a href="iListView.do?page=<%=session.getAttribute("cpage")%>">목록보기</a>&nbsp;&nbsp;
                <a href="iDelete.do?bId=${fContentView.bId}">삭제</a>
			</div>
			</div>
   </table>
   
   <!-- 댓글 쓰기 -->
   		<h3>댓글</h3>
   	
        <c:if test = "${null ==(sessionScope.id)}">
                비 로그인 사용자는 댓글을 사용할 수 없습니다.
        </c:if>
           
        <c:if test = "${null !=(sessionScope.id)}">
        <table class="table">
    			<form id="reply_form">
    				<input type="hidden" name="bTitle" value="${fReplyView.bTitle}"> 
			        <input type="hidden" id="bId" name="bId" value="${fReplyView.bId}">
			        <input type="hidden" name="bGroup" value="${fReplyView.bGroup}">
			        <input type="hidden" name="bStep" value="${fReplyView.bStep}">
			        <input type="hidden" name="bIndent" value="${fReplyView.bIndent}">
		        <tr>
		            <td name="bName" class="table-active"> <%= name %> </td>
		            <td><textarea name="bContent" id="bContent" rows="5" cols="100"></textarea>
		            </td>
               		<td class="table-active"><input class="btn btn-outline-secondary" type="button" value="댓글입력" onclick="form_check();"></td>
               </tr>
                </form>  
   		</table>
        </c:if>
        
    
   
   <!-- 댓글 목록 -->
   <h5>댓글 목록</h5>
   <table class="table">
   		<c:forEach items="${replylist}" var="dto">
   		<form id="reply_form${dto.bId}">
   			<input type="hidden" id="id" name="id" value="${dto.id}">
           	<input type="hidden" name="bId" value="${dto.bId}">
            <tr>
            	
                <td class="table-active">${dto.bName}</td>
                <td colspan="3">${dto.bContent}</td>  
                <td>
                <c:set var="TextValue" value="${dto.bDate}"/>
	                ${fn:substring(TextValue,5,10)}            
                </td>
              
	 			<c:if test = "${'admin' eq (sessionScope.id) }">
	                <td><input class="btn btn-outline-secondary" type="button" value="삭제" onclick="reply_delete${dto.bId}();"></td>
	 			</c:if>
	           	<c:if test = "${dto.id.equals(sessionScope.id)}">
	                <td><input class="btn btn-outline-secondary" type="button" value="삭제" onclick="reply_delete${dto.bId}();"></td>
	           	</c:if>
            </tr>
        </form>    
        
	        <script>
	        function reply_delete${dto.bId}() {
	        	
	        	
	            var queryString=$("#reply_form${dto.bId}").serialize();
	           
	            $.ajax({
	                url : '/FirstWebProject/iReplydelete.do',
	                type : 'POST',
	                data : queryString,
	                dataType: 'text',
	                success : function(json) {
	                	alert("댓글 삭제되었습니다.");
	                    window.location.reload();
	                       
	                }
	            });
	        }
	        </script>  
        </c:forEach>
   </table>
<!-- 여기서부터 복사 -->
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>