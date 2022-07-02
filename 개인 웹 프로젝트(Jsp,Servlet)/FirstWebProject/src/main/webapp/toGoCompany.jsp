<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% 
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
    <div id="msg"></div>
    <div id="map"></div>
    <script>
        if(!navigator.geolocation)
            alert("지원하지 않음");
        else // found()콜백 함수 등록
            navigator.geolocation.getCurrentPosition(found);

        //위치가 파악되면 found()가 호출
        //위치 정보 들어 있는 position 객가 매개 변수로 넘어온다.
        function found(position) {
            var now = new Date(position.timestamp);
            var lat = position.coords.latitude; //위도
            var lon = position.coords.longitude; //경도
            var acc = position.coords.accuracy; //정확도

            //위도와 경도의 소수점 이하 자리가 너무 길어 유효 숫자 6자리로 짜름
            lat = lat.toPrecision(6); lon = lon.toPrecision(6);

            var text = "현재 시간 " + now.toUTCString() + "<br>";
            text += "현재 위치 (위도 " +lat + "˚, 경도 " +lon +"˚ )<br>";
            text += "정확도 " +acc + "m<br>"; 
            
            var address = "서울특별시 동대문구 신설동 25-39"
            			+"<br><br>[1호선]신설동역 3번출구에서 스타벅스 다음 골목 FROMHEART Coffee 4층<br><br>"
            			+"Tel. 010-7188-8309 Fax.054-434-9617<br><hr><br>"
            
            document.getElementById("msg").innerHTML = address;
            var img = new Image();
            img.src = "https://maps.googleapis.com/maps/api/staticmap?center=" + lat
    		+ "," + lon + "&zoom=13&size=400x300&sensor=false&markers=color:red%7Clabel:C%7C"+ lat +"," + lon + "&key=AIzaSyBRncrw28JWI7gv2MTIAjFWEasoVxUlIds";
            document.getElementById("map").appendChild(img); // 구글 지도 이미지를 div의 자식으로 붙임
			
        }
    </script>
    <!-- 여기서부터 복사 -->
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>