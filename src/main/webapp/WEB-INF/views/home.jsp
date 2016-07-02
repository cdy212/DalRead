<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="com.dalnimsoft.etc.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ page session="true" %>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<meta name="Generator" content="">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

  
<!--   	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">		 -->
  	<spring:url value="/resources/css/main.css" var="mainCss" />
  	<spring:url value="/resources/css/w3.css" var="w3Css" />
  	<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />  
	<link href="${mainCss}" rel="stylesheet" />
	<link href="${w3Css}" rel="stylesheet" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	
	<style type="text/css">
	
	@media screen and (max-width:1200px) {
		//노트북 싸이즈
	}

	@media screen and (max-width:780px) {
		//태블릿 싸이즈
	}
	@media screen and (max-width:460px) {
		//폰 싸이즈
		#comment {
			//이건 안먹는거 같다...
			background-color: red;
		    height: 100px;
		}
	}

	

	/* The Close Button */
	.close {
	    color: gray;
	    float: right;
	    font-size: 28px;
	    font-weight: bold;
	}
	.close:hover,
	.close:focus {
	    color: #178acc;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	//커서를 모래시계 형태로 바꾸어줌.
	.ajaxLoading {  
	  cursor: progress !important;  
	}  
		
	.dropdown-menu > li > a {
	    color: black !important;
	}
	

	#addMeaning {
	    width: 100%;
	    margin-top: 5px;
	    margin-bottom: 5px;
	}
	
	//이걸 하면 레이어창이 내려올때 텍스트에어리어등은 흰색으로 그대로 남는다...
	.modal-backdrop {
	  z-index: -1;
	}
	
	</style>
	<spring:url value="/resources/js/main.js" var="mainJS" />
	<script src="${mainJS}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>

  <body>
    <div class="container">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			  </button>
			  <a class="navbar-brand" href="#">DalRead</a>
			</div>

			<div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1" aria-expanded="false" style="height: 1px;">
			  <ul class="nav navbar-nav">
<!-- 				<li class="active"><a href="#">Option <span class="sr-only">(current)</span></a></li> -->
				<li id="option"><a href="#" data-toggle="modal" data-target="#optionModal" class="dropdown-toggle"><span class="glyphicon glyphicon-option-horizontal"></span> Option</a></li>

				<li id="setWordLevel"><a data-toggle="modal" data-target="#MyDicModal" class="dropdown-toggle" href="#"><span class="glyphicon glyphicon-book"></span> My Dictionary</a></li>
					<!-- My Dictionary 모달 시작 -->
					<div class="modal modal-center fade" id="MyDicModal" role="dialog">
						<div class="modal-dialog modal-center modal-lg">
							<div class="modal-content">
								<div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>							        
							        <h4 class="modal-title">My Dictionary</h4>
						     	</div>
								<div class="modal-body">								
									<div>You know <span class="countOfKnownWordClass"></span> words.</div>
							    	<input type="number" id="wordLevelToUpdate" name="quantity" min="1" max="17" value=1>							    	
							    	<button type="button" class="btn btn-primary" name="updateWordLevel" id="updateWordLevel">Update word level</button><br />
									<table id="wordListTable" class="table table-bordered">
									    <thead>	   
 									     	<tr>
	 									        <th>Type</th>           
	 									        <th>Count</th>	 									        	        
	 										</tr>
									    </thead>
									    <tbody>
									    	<tr>
									    		<td>All Words</td>
									    		<td id="countOfAllWord">0</td>	
									      	</tr>
									    	<tr>
									    		<td>Known Words</td>
									    		<td id="countOfKnownWord">0</td>	
									      	</tr>
									    	<tr>
									    		<td>Unkonwn Words</td>
									    		<td id="countOfUnknownWord">0</td>	
									      	</tr>
									      	<tr>
									    		<td>Bookmark Words</td>
									    		<td id="countOfBookmarkWord">0</td>	
									      	</tr>
									    </tbody>
									</table>
							
							     <p id="wordForTheLevel">Some text in the Modal Body</p>              			
								</div>
							</div>
						</div>
					</div>
			  		<!-- My Dictionary 모달 끝 -->
					<!-- 단어 리스트 모달 시작 -->
					<div class="modal modal-center fade" id="wordListModal" tabindex="-1" role="dialog">
						<div class="modal-dialog modal-center modal-sm">
							<div class="modal-content">
								<div class="modal-body">
								  	<table id="wordTable">
										<thead>	   
									      <tr>
									        <th>Word</th>                			        
									        <th>Pronounce</th>
									        <th>Meaning</th>	        
									      </tr>	    
									    </thead>
									    <tbody>			    	
									    </tbody>
									</table>	
								</div>
							</div>
						</div>
					</div>
			  		<!-- 단어 리스트 모달 끝 -->	
			  </ul>
			  
			  <ul class="nav navbar-nav navbar-right">			  	
 	 	    	<c:choose>
				<c:when test="${sessionScope.LOGIN_ID == null}">
<!-- 					<li class="signup"><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
					<li id="btnLogin"><a href="#" data-toggle="modal" data-target="#loginModal" class="dropdown-toggle"><span class="glyphicon glyphicon-user"></span> Log In</a></li>
					<!-- 로그인 모달 시작 -->
					<div class="modal modal-center fade" id="loginModal" role="dialog">
						<div class="modal-dialog modal-center modal-sm">
							<div class="modal-content">
								<div class="modal-body">
								  	<form action="login" method="post" onSubmit="CURRENT_URL.value = window.location.href">    
										<input class="loing_input" type="text" placeholder="ID" id="username" name="ID">
										<input class="loing_input" type="password" placeholder="Password" id="password" name="PASSWORD">										
										<label class="string optional" for="user_remember_me"> Remember me</label>
										<input style="margin-right: 10px;" type="checkbox" name="remember-me" id="remember-me" value="1">
										<input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Log In">
										<input class="btn btn-default btn-block glyphicon glyphicon-user" type="button" id="openSignupModal" value="Sign Up">
										<input TYPE="hidden" NAME=CURRENT_URL>
									</form>        			
								</div>
							</div>
						</div>
					</div>
			  		<!-- 로그인 모달 끝 -->		
			  		
					<!-- 회원가입 모달 시작 아직 기능은 구현해놓지 않았음.-->
					<div class="modal modal-center fade" id="signupModal" tabindex="-1" role="dialog">
						<div class="modal-dialog modal-center modal-sm">
							<div class="modal-content">
								<div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>							        
							        <h4 class="modal-title">Sign Up</h4>
						     	</div>
								<div class="modal-body">
								  	<form action="signup" method="post" onSubmit="CURRENT_URL.value = window.location.href">    
										<input class="loing_input" type="text" placeholder="ID" id="username" name="ID">
										<input class="loing_input" type="password" placeholder="Password" id="password" name="PASSWORD">										
										<input class="loing_input" type="password" placeholder="name" id="name" name="NAME">										
										<input class="btn btn-primary btn-block glyphicon glyphicon-user" type="submit" id="signup" value="Sign Up">										
										<input TYPE="hidden" NAME=CURRENT_URL>
									</form>        			
								</div>
							</div>
						</div>
					</div>
			  		<!-- 회원가입 모달 끝 -->		
			  		
				</c:when>
				<c:otherwise>
<!-- 				로그인 사람 이를을 보여주는데 줄이 안 맞는다... -->
					<li class="signup"><span id="loginUserName">안녕하세요 : ${sessionScope.NAME} 님</span></li>
					<li id="btnLogin"><a data-toggle="modal" data-target="#logoutModal" class="dropdown-toggle" href="#"><span class="glyphicon glyphicon-user"></span> Log Out</a></li>
						<!-- Log out 모달 시작 -->
						<div class="modal modal-center fade" id="logoutModal" tabindex="-1" role="dialog">
							<div class="modal-dialog modal-center modal-sm">
								<div class="modal-content">
									<div class="modal-body">
									  	<form action="NewLogout" method="get" onSubmit="CURRENT_URL.value = window.location.href">
												<span> Want log out?</span>
								            	<input class="btn btn-primary btn-block" type="submit" value="Log Out" />
								            	<input TYPE="hidden" NAME=CURRENT_URL>
										</form>         			
									</div>
								</div>
							</div>
						</div>
				  		<!-- Log out 모달 끝 -->					        			
					</c:otherwise>
				</c:choose>
			  	
			  	
<!-- 			  	<li id="li_help" class="help"><a href="#"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;<span class="unvisible">Help</span></a></li> -->
				<li id="li_help" class="help"><a data-toggle="modal" data-target="#helpModal" class="dropdown-toggle" href="#"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;Help</a></li>
				<!-- Help 모달 시작 -->
				<div class="modal modal-center fade" id="helpModal" tabindex="-1" role="dialog">
					<div class="modal-dialog modal-center modal-lg">
						<div class="modal-content">
							<div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>							        
						        <h4 class="modal-title">Help</h4>
					     	</div>
							<div class="modal-body">
								이 프로그램은 모르는 단어만 찾아서 뜻을 표시해줍니다.
							  	<ol>
							  		<li> 회원가입은 DalTalk 앱에서 하세요.<br>
							  			<button type="button" class="btn btn-primary" id="download_iOS">iOS</button>
							  			<button type="button" class="btn btn-primary" id="download_Android">Android</button>
							  			
							  		</li>
							  		<li>  DalTalk의 아이디로 로그인후 단어레벨을 설정하세요.<br> 모르는 단어를 찾을려면 먼저 아는 단어가 필요합니다.(아는 단어가 아닌것은 전부 모르는것으로 가정합니다.)</li>
							  		<li>뜻을 달고자 하는 텍스트를 복사한후에 뜻달기 버튼을 누릅니다.</li>
							  		<li>모르는 단어에 대해서만 자동으로 뜻을 달아줍니다.</li>
							  		<li>글을 읽다가 단어의 아는정도를 바꿀수 있습니다.</li>
							  		<li>단어에 뜻이 없으면 단어 상세창에서 뜻을 추가할 수 있습니다.</li>
							  	</ol>								
							</div>
							<div class="modal-footer">						        							        
						        Mail to DalRead : <a href="#"><span class="glyphicon glyphicon-envelope"></span></a>						        
					     	</div>
						</div>
					</div>
				</div>
		  		<!-- Help 모달 끝 -->		
			  </ul>
			</div>
		  </div>
		</nav>
		
		<!--carousel-->
<!-- 		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel"> -->
<!-- 			  Indicators -->
<!-- 			  <ol class="carousel-indicators"> -->
<!-- 				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li> -->
<!-- 				<li data-target="#carousel-example-generic" data-slide-to="1"></li> -->
<!-- 				<li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
<!-- 			  </ol> -->

<!-- 			  Wrapper for slides -->
<!-- 			  <div class="carousel-inner" role="listbox"> -->
<!-- 				<div class="item active"> -->
<!-- 				  <img src="..." alt="...">테스트1 -->
<!-- 				  <div class="carousel-caption"> -->
<!-- 					... -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 				<div class="item"> -->
<!-- 				  <img src="..." alt="...">테스트2 -->
<!-- 				  <div class="carousel-caption"> -->
<!-- 					... -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 				... -->
<!-- 			  </div> -->

<!-- 			  Controls -->
<!-- 			  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> -->
<!-- 				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> -->
<!-- 				<span class="sr-only">Previous</span> -->
<!-- 			  </a> -->
<!-- 			  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> -->
<!-- 				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> -->
<!-- 				<span class="sr-only">Next</span> -->
<!-- 			  </a> -->
<!-- 		</div> -->
	
		<!--collapse-->
<!-- 		<a class="btn btn-primary" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> -->
<!-- 		  Link with href -->
<!-- 		</a> -->
<!-- 		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> -->
<!-- 		  Button with data-target -->
<!-- 		</button> -->
<!-- 		<div class="collapse" id="collapseExample"> -->
<!-- 		  <div class="well"> -->
<!-- 			... -->
<!-- 			 Button with data-target -->
<!-- 			  Button with data-target -->
<!-- 			   Button with data-target -->
			   
<!-- 		  </div> -->
<!-- 		</div> -->
		
		<div id="divTextArea">		
		<!-- TextArea -->
		  <form id="formText" role="form" action="${pageContext.request.contextPath}/DalReadText" method="post" target="_blank">
			  	<div class="form-group">
				    <p>
<!-- 				        <input type="file" id="filepath" > -->
<!-- 				     	<button type=button class="btn btn-primary" name="fileopen" id="fileopen">Open file</button> -->
					   	<button type=button class="btn btn-primary" name="delContentInTextarea" id="delContentInTextarea">Clear Textarea</button>					  
					   	
					   	<button type=button class="btn btn-primary" name="addMeaningWithClipboard" id="addMeaningWithClipboard">Use clipboard</button><br />
					   	<input TYPE="hidden" NAME="UID" VALUE=${sessionScope.UID}>
	<!-- 				   	아래 두개는 나중에는 DB에 저장하고 DB에 있는것을 가져오게 수정해야 함. -->
					   	<input TYPE="hidden" id="studyLangInFormText" NAME="studyLang">
					   	<input TYPE="hidden" id="dispMeaningLangInFormText" NAME="dispMeaningLang">
					   	
					   	
					   	<br/>
					   	<button type="submit" class="btn btn-primary" name="addMeaning" id="addMeaning">Add Meaning</button>
				        <!-- <textarea placeholder="Add text here." class="form-control" rows="10" name="comment" id="comment" style="width:600px; height:400px;"></textarea> -->
				        <textarea placeholder="Add text here." class="form-control" name="comment" id="comment" > The prosecution raided the head office of Lotte Group, the nation’s fifth-largest conglomerate, and its seven affiliates in Seoul on Friday over allegations that they created a multibillion won slush fund through shady asset transfers between subsidiaries as well as possible capital inflow to Japan.
						
							(Yonhap)					
							
							The prosecution has been tracking down suspicious capital flow between a policy coordination division of Lotte Group and its key units, including Hotel Lotte, Lotte Shopping and Lotte Home Shopping.							
							Investigators believe that executives had created secret funds by inflating supply prices from their subcontractors. They are reportedly looking into possibilities of the slush funds flowing into Lotte owner families and also to the unlisted companies in Japan that control the group’s Korea operations through opaque cross shareholding structure.
							
							Calling it an outflow of national wealth, the prosecution is likely to conduct an intensive probe into the connection between Hotel Lotte and companies in Japan that collectively owns 99 percent of shares in the group’s hotel unit, according to industry sources and reports. 
						</textarea>
				        <div>
				            <span id="countWordsInTextarea"></span>
				        </div>
						
				    </p>
			    </div>
		    </form>
	    	<!-- Text Area finish -->
	    </div>
	</div>

<%@include file="include/footer.jsp" %>
<script>
$(document).ready(function(){
	
	var dispMeaningLang = "KOREAN";
	var studyLang = "ENGLISH";
	var menuLang = "ENGLISH";
	
	fnShowStudyLang();
	fnShowDispMeaningLang();
	
	countWordsInTextarea();
	
	function fnShowDispMeaningLang() {
		var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'getDispMeaningLang',
			data: ({uid : uid}),
 			success:function(data) {
//  				alert("fnShowDispMeaningLang : " + data);
 				var strDispMeaningLang = "Display meaning in ";
 				if (data == "KOREAN") { 					
 					strDispMeaningLang = strDispMeaningLang  + "Korean "; 					
 				} else if (data == "JAPANESE") {
 					strDispMeaningLang = strDispMeaningLang + "Japanese "; 					
 				} else if (data == "CHINESE_SIMPLIFIED") { 					
 					strDispMeaningLang = strDispMeaningLang  + "Chinese ";
 				} else if (data == "ENGLISH") { 					
 					strDispMeaningLang = strDispMeaningLang  + "English ";
 				} else { 					
 					strDispMeaningLang = strDispMeaningLang  + "Korean ";
 					data = "KOREAN";
 				} 				
 		    	$("#dispMeaningLangInFormText").val(data); 		    	    
 				$("#dispMeaningLanguage").text(strDispMeaningLang).append("<span class='caret'></span>");
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
	}
	
	function fnShowStudyLang() {
		var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'getStudyLang',
			data: ({uid : uid}),
 			success:function(data) {
//  				alert("fnShowDispMeaningLang : " + data);
 				var strStudyLang = "Study ";
 				if (data == "ENGLISH") { 					
 					strStudyLang = strStudyLang  + "English "; 					
 				} else if (data == "JAPANESE") {
 					strStudyLang = strStudyLang + "Japanese "; 					
 				} else if (data == "CHINESE_SIMPLIFIED") { 					
 					strStudyLang = strStudyLang  + "Chinese ";
 				} else { 					
 					strStudyLang = strStudyLang  + "English ";
 					data = "ENGLISH";
 				} 				
 		    	$("#studyLangInFormText").val(data); 		    	    
 				$("#studyLanguage").text(strStudyLang).append("<span class='caret'></span>");
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
	}
	
		
	
	function fnSetStudyLang() {				
		var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'setStudyLang',
			data: ({uid : uid, studyLang : studyLang}),
 			success:function(data) {
			}, error: function (ajaxContext) {
// 		        alert(ajaxContext.responseText)
		    }
		});
	}
	
    function setDispMeaningLang() {    	
//     	$("#dispMeaningLang").val(dispMeaningLang);
    	var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'setDispMeaningLang',
			data: ({uid : uid, dispMeaningLang : dispMeaningLang}),
 			success:function(data) { 				
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
    }
    
	//단어레벨을 설정한다.
	$("#updateWordLevel").click(function() {
		//레벨을 업데이트 하는동안 마우스 커서를 바꾼다. (근데 업데이트하고 나면 이 버튼으로 들어오면 커서가 바뀐다.)
		$(document.body).css({ 'cursor': 'wait' })
		var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'updateWordLevel.ajax?uid=' + uid + '&wordLevel='+$('#wordLevelToUpdate').val(),
			success:function(data) {
				$(document.body).css({ 'cursor': 'default' })
// 				alert(".countOfKnownWordClass : " + data);
				$(".countOfKnownWordClass").text(data);
			}, error: function (ajaxContext) {
				$(document.body).css({ 'cursor': 'default' })
		        alert(ajaxContext.responseText);
		    }
		});
		/* $.ajax({
	        url: 'time.html',
	        data: ({name : "me"}),
	        success: function(data) {
	          alert("data : " + data);
	        }
      }); */
	});
	//단어레벨을 삭제한다. (테스트용)
	$("#deleteWordLevel").click(function() {
		var uid = "${sessionScope.UID}";		
		$.ajax({
			url: 'test2',
			data: ({uid : uid, word : 3}),
			success:function(data) {
				
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
		
		
/*  		var uid = "${sessionScope.UID}";
		alert("uid : " + uid);
		$.ajax({

 			url: 'setWordUnknown.ajax',
 			type:'post', 			
			data: ({uid : uid, word : "test"}),
 			success:function(data) {
				alert("setWordKnown : " + word);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
 */ 	
		
/* 		alert("deleteWordLevel");
		$.ajax({
			url: "deleteWordLevel.html",
			success:function(data) {
				alert("data : " + data);
			}
		});
 */		/* $.ajax({
	        url: 'time.html',
	        data: ({name : "me"}),
	        success: function(data) {
	          alert("data : " + data);
	        }
      }); */
	});
	//아는 단어수를 가져운다.
	$("#countWordLevel").click(function() {
		var uid = "${sessionScope.UID}";
		$.ajax({
			/* url: 'countOfKnownWord.ajax?uid=' + uid, */
 			url: 'countOfKnownWord.ajax',
 			type:'post',
			data: ({uid : uid}),
 			success:function(data) {				
				$(".countOfKnownWordClass").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
	});
	
	//레벨을 변경하면 해당되는 단어를 보여준다.
	$('#wordLevelToUpdate').bind('keyup mouseup', function () {	    
		var wordList = "";	
		switch ($('#wordLevelToUpdate').val()) {
		    case "1":
		    	wordList = "Sunday";
		        break;
		    case "2":
		    	wordList = "Monday";
		        break;
		    case "3":
		    	wordList = "Tuesday";
		        break;
		    case "4":
		    	wordList = "Thursday";
		        break;
		    case "5":
		    	wordList = "Friday";
		        break;
		    case "6":
		    	wordList = "Saturday";
		    default:
		    	wordList = "Looking forward to the Weekend";
		} 
	    $('#wordForTheLevel').text("You may know : " + wordList);
	});
	
	
	$("#getSomeWords").click(function() {
		alert("getSomeWords");
		$.ajax({
			url: "getSomeWords",
			success:function(data) {
				alert("data : " + data.Word);
				console.log(data);
	            
			    var json_obj = $.parseJSON(data);//parse JSON
			    alert("data : " + data);    
	            var output="<ul>";
	            alert("data : " + data);
	            for (var i in json_obj) 
	            {
	            	alert("data : " + data);
	                output+="<li>" + json_obj[i].Language + ",  " + json_obj[i].ID + "</li>";
	            }
	            alert("data : " + data);
	            output+="</ul>";
	            alert("data : " + data);
	            alert("output : " + output);
		            
			}
		});
		/* $.ajax({
	        url: 'time.html',
	        data: ({name : "me"}),
	        success: function(data) {
	          alert("data : " + data);
	        }
      }); */
	});
		
	//단어설정창을 띄운다.	
	$("#setWordLevel").click(function() {
		//아는단어의 수를 가져와서 화면에 보여준다.		
		var uid = "${sessionScope.UID}";		
		$.ajax({
			url: 'countOfKnownWord.ajax?uid=' + uid,
			success:function(data) {
// 				alert("countOfKnownWord : " + data);
				$("#countOfKnownWord").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
				
		$.ajax({			
 			url: 'countOfUnknownWord.ajax',
 			type:'post',
			data: ({uid : uid}),
 			success:function(data) {				
				$("#countOfUnknownWord").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
		
		$.ajax({			
 			url: 'countOfBookmarkWord.ajax',
 			type:'post',
			data: ({uid : uid}),
 			success:function(data) {				
				$("#countOfBookmarkWord").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});

		
		//해당레벨에 해당되는 단어리스트를 보여줌.
		$('#wordForTheLevel').text("word list for the level : " + $('#wordLevelToUpdate').val());
		//설정창을 보여줌.
		$("#modalSetWordLevel").css("display", "block");   
	});


	//단어 설정창에서 아는단어들, 모르는 단어들을 클릭했을때...
	$("#wordListTable > tbody ").on("click", "tr", function(event){
//  	    alert($(this).attr("id"));
//  	  var strMeaning = $(this).find('td nth:child(0)').text();
//  	  alert ("mean" + strMeaning);
		
		var rowIndex = $(this).parent().children().index($(this));
// 		alert($(this).children('td:nth-child(2)').text());			
				
		$(this).siblings().css('background-color', 'white');
		$(this).css('background-color', '#178acc');
		
		
		fnShowWordList(rowIndex);
	});
	
	function fnShowWordList(rowIndex) {				
		if (rowIndex == 0) {
			//모든 단어일때...
		} else if (rowIndex == 1) {
			//아는 단어일때...			
		} else if (rowIndex == 2) {
			//모르는 단어일때...
		} else if (rowIndex == 3) {
			//북마크한 단어일때...			
		}
// 		 $("body").addClass('ajaxLoading');		 
// 		 $(this).addClass('ajaxLoading');
		 $(document.body).css({ 'cursor': 'wait' })
		//해당되는 단어의 리스트를 가져와서 화면에 보여준다.		
		var uid = "${sessionScope.UID}";		
		$.ajax({				
 			url: 'getWordList',
 			type:'post', 			
			data: ({uid : uid, wordListType : rowIndex}),
 			success:function(data) {	 	
 				$(document.body).css({ 'cursor': 'default' })
//  				$("body").removeClass('ajaxLoading');		 
//  				$(this).removeClass('ajaxLoading');
// 				alert("data : " + data);
				$.each(data, function(i, word) {
					if (isEmpty(word["Meaning"])) {
						wordMeaning = wordOriMeaning; 							 
					} else {
						wordMeaning = word["Meaning"];
					}
					$("#wordTable").last().append( '<tr style="border-collapse:collapse; border:1px white solid;" id="wordTable_' + word["Word"] + '">' + '<td>'  + word["Word"] + '</td>'+ '<td>' + word["Pronounce"] + '</td>'+ '<td>' + wordMeaning + '</td>'+  '</tr>' );						
			
					$("#wordTable").last().css('background-color', "gray");
					i++;
				});			
				
 			    $("#MyDicModal").modal('hide');
 			 	$("#wordListModal").modal();

			}, error: function (ajaxContext) {
				$(document.body).css({ 'cursor': 'default' })
//  				$("body").removeClass('ajaxLoading');		 
// 				$(this).removeClass('ajaxLoading');				
		        /* alert(ajaxContext.responseText); */
		    }
		});
		
	}
/* 	$('#comment').on('input change keyup', function () {
	  if ($(this).value.length) {
	    // textarea has content
	    alert("text : " + $(this).value.length);
	  } else {
	    // textarea is empty
	    alert("text is empty");
	  }
	}); */

	$("#comment").change(function() {
		/* alert("comment"); */
	});
	$("#login").click(function() {
		alert("login clicked");
	});
	
	$("#searchIcon").click(function() {
		alert("searchIcon clicked");
	});
	$("#fileopen").click(function() {		
		 readFile();
	});

	$("#default").click(function() {		
		window.open("/DalRead/doB", "myTitle", "height=800, width=300");
	});	
	$("#delContentInTextarea").click(function() {
		$("#comment").val("");
	});

	$("#doA").click(function(){	
		alert("daA clicked");
		window.open("doA", "myTitle", "height=800, width=300");  
		$("#myForm").submit();    
	});
	
	$("#btnLoadText").click(function(){	
		alert("btnLoadText clicked");
	   window.open("/jooo?name=dalnim", "myTitle", "height=800, width=300");
	  });
	
    $("#btn1").click(function(){
    	
    	$("#comment").val($("#webframe").contents().find("html").html());
        /* alert("Text: " + $("#webframe").contents().find("html").html()); */
    });
    $("#btn2").click(function(){
       /*  alert("HTML: " + $("#webframe").html()); */
        var url=$("#myurl").val();
        $('#webframe').attr('src', url);
        /* alert($("#myurl").val()); */
    });
    $("#addMeaningWithClipboard").click(function(e){

    });
    
    //뜻 표시하는 언어를 바꿀때...
    $("#dispMeaningLangKO").click(function(){
    	dispMeaningLang = "KOREAN";
		$("#dispMeaningLanguage").text("Display meaning in Korean ").append("<span class='caret'></span>");		
		setDispMeaningLang();
    });
    $("#dispMeaningLangCH_S").click(function(e){    	
    	dispMeaningLang = "CHINESE_SIMPLIFIED";
		$("#dispMeaningLanguage").text("Display meaning in Chinese ").append("<span class='caret'></span>");		
		setDispMeaningLang();
    });    
    $("#dispMeaningLangJP").click(function(e){
    	dispMeaningLang = "JAPANESE";
		$("#dispMeaningLanguage").text("Display meaning in Japanese ").append("<span class='caret'></span>");		
		setDispMeaningLang();
    });
    $("#dispMeaningLangENG").click(function(e){
    	dispMeaningLang = "English";
		$("#dispMeaningLanguage").text("Display meaning in English ").append("<span class='caret'></span>");		
		setDispMeaningLang();
    });

    //공부하는 언어를 바꿀때...
    $("#studyLangENG").click(function(e){
    	studyLang = "ENGLISH";
		$("#studyLanguage").text("Study English ").append("<span class='caret'></span>");
    	$("#dispMeaningLangInFormText").val(dispMeaningLang);
    	$("#studyLangInFormText").val(studyLang);    	
    	fnSetStudyLang();
    });
    $("#studyLangCh").click(function(e){
    	studyLang = "CHINESE_SIMPLIFIED";
    	$("#studyLanguage").text("Study Chinese ").append("<span class='caret'></span>");
    	$("#dispMeaningLangInFormText").val(dispMeaningLang);
    	$("#studyLangInFormText").val(studyLang);
    	fnSetStudyLang();
    });    
    $("#studyLangJp").click(function(e){
    	studyLang = "JAPANESE";
    	$("#studyLanguage").text("Study Japanese ").append("<span class='caret'></span>");
    	$("#dispMeaningLangInFormText").val(dispMeaningLang);
    	$("#studyLangInFormText").val(studyLang);    	
    	fnSetStudyLang();
    });
    
    //메뉴 언어를 바꿀때... (아직 메뉴는 구현안했다.. 쿠키로 구현할까?)
    $("#menuLangENG").click(function(e){
    	menuLang = "ENGLISH";
		$("#menuLanguage").text("Display menu in English(구현중) ").append("<span class='caret'></span>");
    });
    $("#menuLangKO").click(function(e){
    	menuLang = "KOREAN";
    	$("#menuLanguage").text("한글로 메뉴 표시(구현중) ").append("<span class='caret'></span>");
    });    

    
    $("#comment").on("change keyup paste", function() {    	    
    	countWordsInTextarea();
    });
    
    function countWordsInTextarea() {
    	var string = $("#comment").val();
    	var charactersCount = string.length;
    	var wordCount = string.replace( /[^\w ]/g, "" ).split( /\s+/ ).length;    	
        $("#countWordsInTextarea").text("Words(" + wordCount + "), Characters(" + charactersCount + ")");            	
    }
    
    $(document).on('keypress', '#myurl', function(e) {

        if ( e.keyCode == 13 ) {  // detect the enter key
            $('#btn2').click(); // fire a sample click,  you can do anything
          
        }
    });
    
    
    $('ul.nav-tabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
      })
    
    
    $("#openSignupModal").click(function(e) {    	
    	$("#loginModal").modal('hide');
     	$("#signupModal").modal();	
    	console.log("btnLogin");
    });
    
	//이것을 main.js 로빼면 안먹는다.	
	//비어있는 문자열인지 확인...
	function isEmpty(str) {
		  return typeof str == 'string' && !str.trim() || typeof str == 'undefined' || str === null;
	}    
    
});



</script>


					<!-- Option 모달 시작 -->
					<div class="modal modal-center fade" id="optionModal" tabindex="-1" role="dialog">
						<div class="modal-dialog modal-center modal-lg">
							<div class="modal-content">
								<div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>							        
							        <h4 class="modal-title">Option</h4>
						     	</div>
								<div class="modal-body">		
									<div class="btn-group">			    
									    <button id="studyLanguage" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
									    Study English
									    <span class="caret"></span>
									    </button>
									    <ul class="dropdown-menu" >
									        <li id="studyLangENG"><a href="#">English</a></li>
									        <li id="studyLangCh"><a href="#">Chinese</a></li>
									        <li id="studyLangJp"><a href="#">Japanese</a></li>
									    </ul>
									</div>
														
									<div class="btn-group">
									    <button id="dispMeaningLanguage" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
									    Display meaning in Korean
									    <span class="caret"></span>
									    </button>
									    <ul class="dropdown-menu" >			        
									        <li id="dispMeaningLangCH_S"><a href="#">Chinese</a></li>			        
									        <li id="dispMeaningLangKO"><a href="#">Korean</a></li>
									        <li id="dispMeaningLangJP"><a href="#">Japanese</a></li>
									        <li id="dispMeaningLangENG"><a href="#">English</a></li>
									    </ul>
									</div>
									
									<div class="btn-group">			    
									    <button id="menuLanguage" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
									    Display menu in English 
									    <span class="caret"></span>
									    </button>
									    <ul class="dropdown-menu" >
									        <li id="menuLangENG"><a href="#">English</a></li>
									        <li id="menuLangKO"><a href="#">한국어</a></li>									        
									    </ul>
									</div>
									
									<div class="checkbox">
										<label><input id="chk_ShowMeaing" type="checkbox" value="true">Show meaning of word</label>
									</div>
									<div class="checkbox">
									  	<label><input id="chk_ShowPronounce" type="checkbox" value="true">SHow pronounce of word</label>
									</div>        			
								</div>								
							</div>
						</div>
					</div>
			  		<!-- Option 모달 끝 -->	
</body>
</html>

