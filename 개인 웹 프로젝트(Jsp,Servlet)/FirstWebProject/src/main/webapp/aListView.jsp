<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
function search_form(){
	
	 submit_ajax();
}

function submit_ajax() {
	
	var search = $('#search').val();
	var searchSomething = $('#searchSomething').val();
	
   var queryString=$("#searchForm").serialize();
   
   console.log(queryString);
  
   $.ajax({
       url : '/FirstWebProject/aListView.do',
       type : 'POST',
       data : queryString,
       dataType: 'text',
       success : function(json) {
    	 
    	  
    	   window.location.replace('aListView.do?search='+search+'&searchSomething='+searchSomething);
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
	<h1>공지사항</h1>
	<div id="div01">
	<table class="table table-hover">
		<thead>
        <tr class="table-active">
            <th scope="row">번호</th>
            <th>이름</th>
            <th>제목</th>
            <th>날짜</th>
            <th>조회수</th>
        </tr>
        </thead>
       	<c:forEach items="${list}" var="dto" varStatus="status"> 
       	<tbody>	
       		<tr>
                <th scope="row">${dto.bId}</th>
                <td>${dto.bName}</td>
                <td>
      	            <a href="aContentView.do?bId=${dto.bId}">${dto.bTitle}</a>
      	            
      	            <c:if test = "${0.0417 > replycount[status.index].latestpost }">
      	            <span class="badge badge-secondary">New</span>
      	            </c:if>
      	            
      	            <c:if test = "${0 < replycount[status.index].replycount}">
      	           	&nbsp;[${replycount[status.index].replycount}]
      	            </c:if>
      	            
                </td>   
                <td>
	                <c:set var="TextValue" value="${dto.bDate}"/>
	                ${fn:substring(TextValue,5,10)}
                </td>
                <td>${dto.bHit}</td>
            </tr>
            
       	</c:forEach>
       	
		<tr>
			<td></td>
			<td></td>
            <td colspan="2">
        	<nav aria-label="...">
        	<ul class="pagination">
        	
            <!--처음-->
            <li class="page-item">
            <c:choose>
                <c:when test="${(page.curPage-1)<1}">
                    <span class="page-link" href="aListView.do?page=1">처음</span>
                </c:when>
                <c:otherwise>
                    <a class="page-link" href="aListView.do?page=1">처음</a>
                </c:otherwise>
            </c:choose>
            </li>
            
            <!--이전-->
            <li class="page-item">
            <c:choose>
                <c:when test="${(page.curPage-1) < 1}">
                    <span class="page-link" href="aListView.do?page=${page.curPage-1}">이전</span>
                </c:when>
                <c:otherwise>
                    <a class="page-link" href="aListView.do?page=${page.curPage-1}">이전</a>
                </c:otherwise>
            </c:choose>
			</li>
            <!--개별 페이지-->
            <c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
                <c:choose>
                    <c:when test="${page.curPage ==fEach}">
                        <li class="page-item active"><a class="page-link" href="#">${fEach} <span class="sr-only">(current)</span></a></li>
                    </c:when>

                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="aListView.do?page=${fEach}">${fEach}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!--다음-->
            <li class="page-item">
            <c:choose>
                <c:when test="${(page.curPage +1) > page.totalPage}">
                    <span class="page-link" href="aListView.do?page=${page.curPage+1}">다음</span>
                </c:when>
                <c:otherwise>
                    <a class="page-link" href="aListView.do?page=${page.curPage+1}">다음</a>
                </c:otherwise>
            </c:choose>
			</li>
            <!--끝-->
            <li class="page-item">
            <c:choose>
                <c:when test="${page.curPage == page.totalPage}">
                    <span class="page-link" href="aListView.do?page=${page.totalPage}">끝</span>
                </c:when>
                <c:otherwise>
                    <a class="page-link" href="aListView.do?page=${page.totalPage}">끝</a>
                </c:otherwise>
            </c:choose>
            </li>
            </ul>
    		</nav>
            </td>
            	<c:if test = "${'admin' eq (sessionScope.id) }">
	                <td><a href="awriteView.do" class="btn btn-primary" tabindex="-1" role="button" aria-disabled="true">글작성</a></td>
	 			</c:if>
            
		</tr>
		</tbody>
    </table>
    </div>
    
    <!-- 검색기능 -->
    <form id="searchForm">
    	<input type="hidden"  name="page" value="1">
    	 <select id="search" name="search">
	         <option id="sTitlecontent" value="sTitlecontent" selected>제목+내용</option>
	         <option id="sTitle" value="sTitle" >제목</option>
	         <option id="sContent" value="sContent">내용</option>
	         <option id="sName" value="sName">글쓴이</option>
	   
         </select>
         <input type="text" id="searchSomething" name="searchSomething">
         <input type="button" value="검색" onclick="search_form();">
    </form>
        
  </div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>