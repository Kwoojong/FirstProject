<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if(session.getAttribute("ValidMem") != null) {%>
	<jsp:forward page="mainView.do"></jsp:forward>
<% } %>


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

<script>
	function form_check(){
		var queryString = $("#LoginForm").serialize();
		
		$.ajax({
	        url : '/FirstWebProject/blackCheck.do',
	        type : 'POST',
	        data : queryString,
	        dataType: 'text',
	        success : function(json) {
	        	
	            console.log(json);
	            var result =JSON.parse(json);
	            
	            if(result.code=="success"){
	            	submit_ajax();
	            }else{
	            
	            	alert(result.desc);
	            }
	        }
	    });
	}
	
	function submit_ajax() {
	    queryString=$("#LoginForm").serialize();
	    $.ajax({
	        url : '/FirstWebProject/login.do',
	        type : 'POST',
	        data : queryString,
	        dataType: 'text',
	        success : function(json) {
	            console.log(json);
	            var result =JSON.parse(json);
	            if(result.code=="success"){
	            	alert(result.desc)
	            	window.location = document.referrer
	            }else{
	            	alert(result.desc);
	            }
	        }
	    });
	}
</script>

<!-- 구글 로그인 -->
	<script src="https://accounts.google.com/gsi/client" async defer></script>
    <script src="https://unpkg.com/jwt-decode/build/jwt-decode.js"></script>
    <script>
	function onSignIn() {
	    google.accounts.id.initialize({
	        client_id: "605800980482-qpmc1i35be1mrqq1vauah1tf85e7p3r4.apps.googleusercontent.com",
	        callback: handleCredentialResponse
	    });
	    google.accounts.id.prompt(); 
	}

	function handleCredentialResponse(response) {
	    var profile = jwt_decode(response.credential);
		console.log("ID: " + profile.sub);
		console.log('Name: ' + profile.name);
	    console.log("Image URL: " + profile.picture);
	    console.log("Email: " + profile.email);    
		
		$('#login').css('display', 'none');
    	$('#logout').css('display', 'block');
    	$('#upic').attr('src', profile.picture);
    	$('#uname').html('[ ' +profile.name + ' ]'); 
    	
    	
   	    var queryString= "id="+profile.sub+"&name="+profile.name;
   	    console.log(queryString);
   	    $.ajax({
   	        url : '/FirstWebProject/SnsLoginService.do',
   	        type : 'POST',
   	        data : queryString,
   	        dataType: 'text',
   	        success : function(json) {
   	            console.log(json);
   	            var result =JSON.parse(json);
   	            if(result.code=="success"){
   	            	alert(result.desc)
   	            	window.location = document.referrer
   	            }else{
   	            	alert(result.desc);
   	            }
   	        }
   	    });
    	
	}
	
	function signOut() {
	    google.accounts.id.disableAutoSelect();
	    
        $('#login').css('display', 'block');
        $('#logout').css('display', 'none');
        $('#upic').attr('src', '');
        $('#uname').html('');
	}
	
	</script>
	
	<!-- 페북로그인 -->
	<script>
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '1132713237293794',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v14.0'
	    });
	
	    FB.getLoginStatus(function(response) {
	    	console.log(response);
	      statusChangeCallback(response);
	    });
	  };
	
	  // Load the SDK asynchronously
	  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "https://connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk'));
	
	  function statusChangeCallback(response) {
	    if (response.status === 'connected') {
	      getINFO();
	    } else {
	      $('#fblogin').css('display', 'block');
	      $('#fblogout').css('display', 'none');
	      $('#fbupic').attr('src', '');
	      $('#fbuname').html('');
	    }
	  }
		  
	  function fbLogin () {
	    FB.login(function(response){
	      statusChangeCallback(response);
	    }, {scope: 'public_profile, email'});
	  }
	
	  function fbLogout () {
	    FB.logout(function(response) {
	      statusChangeCallback(response);
	    });
	  }
	
	  function getINFO() {
	    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
	      console.log(response);
	      $('#fblogin').css('display', 'none');
	      $('#fblogout').css('display', 'block');
	      $('#fbupic').attr('src', response.picture_small.data.url );
	      $('#fbuname').html('[ ' + response.name + ' ]');
	      
	      var queryString= "id="+response.id+"&name="+response.name;
	   	    console.log(queryString);
	   	    $.ajax({
	   	        url : '/FirstWebProject/SnsLoginService.do',
	   	        type : 'POST',
	   	        data : queryString,
	   	        dataType: 'text',
	   	        success : function(json) {
	   	            console.log(json);
	   	            var result =JSON.parse(json);
	   	            if(result.code=="success"){
	   	            	alert(result.desc)
	   	            	window.location = document.referrer
	   	            }else{
	   	            	alert(result.desc);
	   	            }
	   	        }
	   	    });
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
	
	<h1>로그인</h1>
	<form id="LoginForm">
    <div class="form-group">
	      <label for="exampleInputEmail1">아이디&nbsp;&nbsp;&nbsp;&nbsp;</label>
	      <input type="text" class="form-control form-control-lg" name="id" id="exampleInputEmail1" value="<% if(session.getAttribute("id") != null)
	      						out.println(session.getAttribute("id"));%>" placeholder="Enter Id">
	      
    </div>
    <div class="form-group">
	      <label for="exampleInputPassword1">비밀번호</label>
	      <input type="password" class="form-control form-control-lg" name="pw" id="exampleInputPassword1" placeholder="Enter Password">
    </div>
    	<input type="button" class="btn btn-primary" value="회원가입" onclick="javascript:window.location='joinView.do'">
    	
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" value="로그인" onclick="form_check();"> 
  	</form>
	<br>
	<h3>SNS로그인</h3>
	<!-- 구글로그인 -->	
	<div id="login">
		<img src="googleLoginImage.png" onclick="onSignIn();" width="280"><br>
	</div>
	
	<div id="logout" style="display: none;">
	    <input type="button" onclick="signOut();" value="Google로그아웃" /><br>
	
	    <img id="upic" src=""><br>
	    <span id="uname"></span>
	</div>
	<!-- 페북로그인 -->   
	 <div id="fblogin" style="display: block;">
	 	<img src="fbLoginImage.png" width="280" onclick="fbLogin();">
	</div>
	
	<div id="fblogout" style="display: none;">
	    <input type="button" onclick="fbLogout();" value="facebook로그아웃" /><br>
	
	    <img id="fbupic" src=""><br>
	    <span id="fbuname"></span>
	</div>
	<div id="fb-root"></div>

	<!-- 카카오로그인 -->
	<div id="kakaologin" style="display: block">
	<a id="custom-login-btn" href="javascript:loginWithKakao()">
	<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="280"/>
	</a>
	</div>
	
	<div id="kakaologout" style="display: none;">
	<input type="button" class="btn btn-success" onclick="kakaosignOut();" value="카카오로그아웃" /><br>
	
	<img id="kakaoupic" src=""><br>
	   <span id="kakaouname"></span>
	</div>
	
	<script type='text/javascript'>
	Kakao.init('197af33154be6813f00da604726cbf76');
	function loginWithKakao() {
	  // 로그인 창을 띄웁니다.
	  Kakao.Auth.login({
	    success: function(authObj) {
	      //alert(JSON.stringify(authObj));
	      signIn(authObj);
	    },
	    fail: function(err) {
	      alert(JSON.stringify(err));
	    }
	  });
	};
	
	function signIn(authObj) {
	    //console.log(authObj);
	    Kakao.API.request({
	        url: '/v2/user/me',
	        success: function(res) {
	            //console.log(res);
	            console.log(res.id);
	            console.log(res.properties.nickname);
	            $('#kakaologin').css('display', 'none');
	               $('#kakaologout').css('display', 'block');
	            $('#kakaoupic').attr('src', res.properties.thumbnail_image );
	               $('#kakaouname').html('[ ' + res.properties.nickname + ' ]');
	               
	            var queryString= "id="+res.id+"&name="+res.properties.nickname;
	   	   	    console.log(queryString);
	   	   	    $.ajax({
	   	   	        url : '/FirstWebProject/SnsLoginService.do',
	   	   	        type : 'POST',
	   	   	        data : queryString,
	   	   	        dataType: 'text',
	   	   	        success : function(json) {
	   	   	            console.log(json);
	   	   	            var result =JSON.parse(json);
	   	   	            if(result.code=="success"){
	   	   	            	alert(result.desc)
	   	   	            	window.location = document.referrer
	   	   	            }else{
	   	   	            	alert(result.desc);
	   	   	            }
	   	   	        }
	   	   	    });   
	         }
	     })
	}
	
	function signOut() {
	    Kakao.Auth.logout(function () {
	        $('#kakaologin').css('display', 'block');
	        $('#kakaologout').css('display', 'none');
	        $('#kakaoupic').attr('src', '');
	        $('#kakaouname').html('');
	    });
	}
	</script>
	<!-- 네이버로그인 -->

	<div>
	    <div>
	    </div>
	        <div id="naverIdLogin">
	        <a id="naverIdLogin_loginButton" href="#" role="button"><img src="https://static.nid.naver.com/oauth/big_g.PNG" width=200></a>
	        </div>
	</div>

	<!-- (2) LoginWithNaverId Javscript SDK -->
	<script src="naveridlogin_js_sdk_2.0.2.js"></script>
	<!-- (3) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>  
	    var naverLogin = new naver.LoginWithNaverId(
	        {
	
	            clientId: "6QY6K45RfgUWj5pH667a",
	            callbackUrl: "http://localhost:8081/FirstWebProject/loginView.jsp",
	            isPopup: false,
	            loginButton: {color: "green", type: 3, height: 60}
	        }
	    );
	    /* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
	    naverLogin.init();
	    
	    /* (4-1) 임의의 링크를 설정해줄 필요가 있는 경우 */
	    $("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());
	
	    /* (5) 현재 로그인 상태를 확인 */
	    window.addEventListener('load', function () {
	        naverLogin.getLoginStatus(function (status) {
	            if (status) {
	                /* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
	                   사용자 정보를 출력합니다. */
	                setLoginStatus();
	            }
	        });
	    });
	
	    /* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
	       사용자 정보를 출력합니다. */
	    function setLoginStatus() {
	        console.log(naverLogin.user);
	        var uid = naverLogin.user.getId();
	        var profileImage = naverLogin.user.getProfileImage();
	        var uName = naverLogin.user.getName();
	        var nickName = naverLogin.user.getNickName();
	        var eMail = naverLogin.user.getEmail();
	        
	        var queryString= "id="+uName+"&name="+nickName;
	   	    console.log(queryString);
	   	    $.ajax({
	   	        url : '/FirstWebProject/SnsLoginService.do',
	   	        type : 'POST',
	   	        data : queryString,
	   	        dataType: 'text',
	   	        success : function(json) {
	   	            console.log(json);
	   	            var result =JSON.parse(json);
	   	            if(result.code=="success"){
	   	            	alert(result.desc)
	   	            	window.location = document.referrer
	   	            }else{
	   	            	alert(result.desc);
	   	            }
	   	        }
	   	    });
	        $("#naverIdLogin_loginButton").html(
	                '<br><br><img src="' + profileImage + 
	                '" height=50 /> <p>' + uid + "-" + uName + '님 반갑습니다.</p>');
	        $("#gnbLogin").html("Logout");
	        $("#gnbLogin").attr("href", "#");
	        
	        /* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
	        $("#gnbLogin").click(function () {
	            naverLogin.logout();
	            location.reload();
	        });
	    }
	</script>
	<!-- 손대지말것  -->
	</div>
	</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>