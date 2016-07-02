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
	<meta name="viewport" content="width=device-width, initial-scale=1">
  
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">		
  	<spring:url value="/resources/css/main.css" var="mainCss" />
  	<spring:url value="/resources/css/w3.css" var="w3Css" />  
	<link href="${mainCss}" rel="stylesheet" />
	<link href="${w3Css}" rel="stylesheet" />
	
	<style type="text/css">
	
	@media screen and (max-width:1200px) {
		//노트북 싸이즈
	}

	@media screen and (max-width:780px) {
		//태블릿 싸이즈
	}
	@media screen and (max-width:460px) {
		//폰 싸이즈
		.modal {
		    padding-top: 20px; /* Location of the box */		   
		}
		.modal-content {
		    width: 80%;		    
		}
		
		.main-footer .foot_wrap {
			width:100%;
			height:30px;		
		}
		.main-footer .foot_wrap .copyright {
			width:100%;	
			clear : both;
/* 			display:block;		 */
		}
		
		.main-footer .foot_wrap .pull-right {			
			width: 100%;
			clear : both;
/* 			display:block; */
		}
	}
	
	.main-footer {
		font-size:0.9em;
	}
	
	.main-footer .copyright {
		float:left;
		width: 80%;
	}
	
	.main-footer .pull-right {
		float:right;
		width: 20%;
		text-align:right;
	}
	</style>
	<spring:url value="/resources/js/main.js" var="mainJS" />
	<script src="${mainJS}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>

<body>


<!-- 최상단 메뉴 화면 -->
<div class="container">
	 <nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <!-- <div class="navbar-header navbar-fixed-top"> --> <!-- 이건 상단에 고정시켜주는데 문제는 버튼들이 클릭이 안된다. -->
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#"></a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">DalRead</a></li>
	      <li id="myOption"><a href="#"><span class="glyphicon glyphicon-option-horizontal"></span> Option</a></li>	      
	      <li id="setWordLevel"><a href="#"><span class="glyphicon glyphicon-book"></span>Set Word Level</a></li>
	      
	      <div class="btn-group">
			    <button id="dispMeaningLanguage" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
			    Display meaning in Korean
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu" >			        
			        <li id="dispMeaningLangCH_S"><a href="#">Chinese</a></li>			        
			        <li id="dispMeaningLangKorea"><a href="#">Korean</a></li>
			        <li id="dispMeaningLangJP"><a href="#">Japanese</a></li>
			        <li id="dispMeaningLangENG"><a href="#">English</a></li>
			    </ul>
			</div>
			
			<div class="btn-group">			    
			    <button id="studyLanguage" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
			    Study English
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu" >
			        <li id="studyLangEng"><a href="#">English</a></li>
			        <li id="studyLangCh"><a href="#">Chinese</a></li>
			        <li id="studyLangJp"><a href="#">Japanese</a></li>
			    </ul>
			</div>
	    </ul>
	    <ul class="nav navbar-nav navbar-right ">
<!-- 	    	<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
	    	<li id="login"><a href="#"><span class="dropdown-toggle glyphicon glyphicon-log-in"></span> Login</a></li>
 -->	    	
 	    	<c:choose>
				<c:when test="${sessionScope.LOGIN_ID == null}">
					<form action="sample_login" method="post" onSubmit="CURRENT_URL.value = window.location.href">
					  	<fieldset style="width: 350px" id="loginLogout">    
					  		<!-- <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
							<li class="dropdown">
								<a class="dropdown-toggle glyphicon glyphicon-log-in" href="#" data-toggle="dropdown" id="btnLogin"> Login</a>
								<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
									<form method="post" action="login" accept-charset="UTF-8">
										<input style="margin-bottom: 15px;" type="text" placeholder="ID" id="username" name="ID">
										<input style="margin-bottom: 15px;" type="password" placeholder="Password" id="password" name="PASSWORD">										
										<label class="string optional" for="user_remember_me"> Remember me</label>
										<input style="margin-right: 10px;" type="checkbox" name="remember-me" id="remember-me" value="1">
										<input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Login">
										<input TYPE="hidden" NAME=CURRENT_URL>
									</form>
								</div>
							</li>
					    </fieldset>
					</form>        		
				</c:when>
				<c:otherwise>
					<form action="NewLogout" method="get" onSubmit="CURRENT_URL.value = window.location.href">
						<span id="loginUserName">안녕하세요 : ${sessionScope.LOGIN_ID} 님</span>
			            	<input type="submit" value="LogOut" />
			            	<input TYPE="hidden" NAME=CURRENT_URL>
					</form>             			
				</c:otherwise>
			</c:choose>
      
	    </ul>
	  </div>
	</nav>
</div>

<%-- <%@include file="include/header.jsp" %> --%>
<%-- <%
//고유한 세션 객체의 ID를 되돌려준다.
String id_str=session.getId();
//세션에 마지막으로 엑세스한 시간을 되돌려준다.
long lasttime=session.getLastAccessedTime();
//세션이 생성된 시간을 되돌려 준다.
long createdtime=session.getCreationTime();
//세션에 마지막으로 엑세스한 시간에서 세션이  생성된 시간을 빼면
//웹사이트에 머문시간이 계산된다.
long time_used=(lasttime-createdtime)/60000;
//세션의 유효시간 얻어오기
int inactive=session.getMaxInactiveInterval()/60;
//세션이 새로 만들어졌는지 알려 준다.
boolean b_new=session.isNew();
String birth = (String)session.getAttribute("LOGIN_ID");
out.println("\n"  +  birth);
%> --%>

<!-- 로그인 화면 -->
<%-- <div class="container">
	<c:choose>
		<c:when test="${sessionScope.LOGIN_ID == null}">
			<form action="NewLogIn" method="post" onSubmit="CURRENT_URL.value = window.location.href">
			  	<fieldset style="width: 300px">    
			        <legend> Login to App </legend>
			        <table>
			            <tr>
			                <td>User ID</td>
			                <td><input type="text" name="ID" required="required" /></td>
			            </tr>
			            <tr>
			                <td>Password</td>
			                <td><input type="password" name="PASSWORD" required="required" /></td>
			            </tr>
			            <tr>
			                <td>
			                	<input type="submit" value="Login" />
			                	<input TYPE="hidden" NAME=CURRENT_URL>
			                </td>				                    
			            </tr>
			        </table>
			    </fieldset>
			</form>        		
		</c:when>
		<c:otherwise>
			<form action="NewLogout" method="get" onSubmit="CURRENT_URL.value = window.location.href">
				안녕하세요 : ${sessionScope.LOGIN_ID} 님 <br>
	            	<input type="submit" value="LogOut" />
	            	<input TYPE="hidden" NAME=CURRENT_URL>
			</form>             			
		</c:otherwise>
	</c:choose>
</div> --%>
        
<div class="container">
  <div class="jumbotron">
    <!-- <h1>What is DalRead?달리드</h1> -->
    <!-- <p>DalRead find the unknown words for you automatically.</p> -->
  </div>
</div>

<div class="container">
	<ul class="nav nav-tabs">
	  <li class=""><a href="#first">Web</a></li>
	  <li class="active"><a href="#second">Text</a></li>	  
	</ul>
</div>

<div class="container">
	<div class="tab-content" style="margin-top: 80px;">
	  <div class="tab-pane fade" id="first">
	    <p>
			
			<label class="sr-only" for="myurl">URL : </label>	    			
			<input type="text" class="form-control" name="myurl" id="myurl" placeholder="URL" value="http://www.cnn.com">
	    
	        <br/>
	        <iframe class="webframe" id="webframe" src="" width="1000" height="500"></iframe>        
	    </p>
	  </div>
	  <div class="tab-pane fade active in" id="second">
	  	 
		  <form id="formText" role="form" action="${pageContext.request.contextPath}/DalReadText" method="post" target="_blank">
		  	<div class="form-group">
			    <p>
			        <input type="file" id="filepath" >
			     	<button type=button class="btn btn-primary" name="fileopen" id="fileopen">Open file</button>
				   	<button type=button class="btn btn-primary" name="delContentInTextarea" id="delContentInTextarea">Clear Textarea</button>
				   	<button type=button class="btn btn-primary" name="doA" id="doA">call doA</button>
				   	<button type="submit" class="btn btn-primary" name="addMeaning" id="addMeaning">Add Meaning</button>
				   	<button type=button class="btn btn-primary" name="addMeaningWithClipboard" id="addMeaningWithClipboard">Use clipboard</button><br />
				   	<input TYPE="hidden" NAME="UID" VALUE=${sessionScope.UID}>
<!-- 				   	아래 두개는 나중에는 DB에 저장하고 DB에 있는것을 가져오게 수정해야 함. -->
				   	<input TYPE="hidden" id="studyLangInFormText" NAME="studyLang">
				   	<input TYPE="hidden" id="dispMeaningLangInFormText" NAME="dispMeaningLang">
				   	
				   	
				   	<br/>
			        <div>
			            <span id="fileName">File Name</span>
			            <span id="fileSize">File Size</span>
			        </div>
			        <!-- <textarea placeholder="Add text here." class="form-control" rows="10" name="comment" id="comment" style="width:600px; height:400px;"></textarea> -->
			        <textarea placeholder="Add text here." class="form-control" rows="10" name="comment" id="comment" >The prosecution raided the head office of Lotte Group, the nation’s fifth-largest conglomerate, and its seven affiliates in Seoul on Friday over allegations that they created a multibillion won slush fund through shady asset transfers between subsidiaries as well as possible capital inflow to Japan.

(Yonhap)

More than 200 investigators combed 17 Lotte offices including residences of Lotte chairman Shin Dong-bin and other top executives and the secluded office of Lotte founder Shin Kyuk-ho at Hotel Lotte.

Computer hard disks, accounting books and asset transfer records were confiscated during the raid. Prosecutors imposed an emergency travel ban on Lotte’s executives including a vice chairman surnamed Lee, believed to be the second most powerful man within the group.

Chairman Shin is reportedly in the U.S. for a business trip.

“We have conducted searches and seizures over speculations that Lotte established black funds via asset trading processes between its subsidiaries,” said an official at Seoul Central Prosecutors’ Office. “The case involves key executives with embezzlement and malpractice allegations.”

The prosecution has been tracking down suspicious capital flow between a policy coordination division of Lotte Group and its key units, including Hotel Lotte, Lotte Shopping and Lotte Home Shopping.

Investigators believe that executives had created secret funds by inflating supply prices from their subcontractors. They are reportedly looking into possibilities of the slush funds flowing into Lotte owner families and also to the unlisted companies in Japan that control the group’s Korea operations through opaque cross shareholding structure.

Calling it an outflow of national wealth, the prosecution is likely to conduct an intensive probe into the connection between Hotel Lotte and companies in Japan that collectively owns 99 percent of shares in the group’s hotel unit, according to industry sources and reports. </textarea>
			    </p>
		    </div>
	    </form>
	  </div>
	  <div class="tab-pane fade" id="third">
	    <p>

	    </p>
	  </div>
	</div>
</div>

<!-- The Modal -->
<div id="modalSetWordLevel" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close" id="closeLayerSetWordLevel">×</span>
	   	<c:choose>
		<c:when test="${sessionScope.LOGIN_ID == null}">
			<span>데모유저가 아는단어수 : <span class="countOfKnownWordClass"></span> </span>
		</c:when>
		<c:otherwise>
			<span>${sessionScope.LOGIN_ID} 님이 아는단어수 : <span class="countOfKnownWordClass" id="countOfKnownWord"></span></span>
		</c:otherwise>
	</c:choose>
      
    </div>
    <div class="modal-body">
    	<input type="number" id="wordLevelToUpdate" name="quantity" min="1" max="17" value=1>
    	
    	<button type="button" class="btn btn-primary" name="updateWordLevel" id="updateWordLevel">Update word level</button><br />
    	<button type=""button"" class="btn btn-primary" name="deleteWordLevel" id="deleteWordLevel">delete word level</button><br />
    	<button type=""button"" class="btn btn-primary" name="countWordLevel" id="countWordLevel">count word level</button><br />
    	<button type=""button"" class="btn btn-primary" name="getSomeWords" id="getSomeWords">getSomeWords</button><br />
<!--      	 <form id="formUpdateMeaning" role="form" action="SetWordLevel" method="post">
		  	<div class="form-group">
			    <p>
			     	<input type="hidden" name="word" value="Norway"> 
				   	<button type="submit" class="btn btn-primary" name="updateWordLevel" id="updateWordLevel">Update word level</button><br />
			    </p>
		    </div>
		</form>		
 -->		<table class="table table-bordered">
	    <thead>	   
	      <tr>
	        <th>Level</th>                
	        <th>Word</th>
	        <th>Know</th>	        
	      </tr>	    
	    </thead>
	    <tbody>
	    	<c:forEach var="level" begin="1" end="10">
	    	<tr>
	    		<td id="wordLevel_${level}">Level : ${level }</td>
	    		<td><c:out value="단어들"/></td>	
	    		<td>				<input type="checkbox" checked="checked" value="">					</td>    
						
	      </tr>
	      </c:forEach>
	    </tbody>
	</table>

      <p id="wordForTheLevel">Some text in the Modal Body</p>      
    </div>
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div>
</div>

<!-- <span style="float: left">   
    <a href="?lang=en">en</a>   
    |   
    <a href="?lang=ko">ko</a>   
</span> 
 -->
<%@include file="include/footer.jsp" %>
<script>
$(document).ready(function(){
	var dispMeaningLang = "KOREAN";
	var studyLang = "ENGLISH";	
	
	fnShowStudyLang();
	fnShowDispMeaningLang();
	
	countWords();
	
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
		var uid = "${sessionScope.UID}";
		$.ajax({
			url: 'updateWordLevel.ajax?uid=' + uid + '&wordLevel='+$('#wordLevelToUpdate').val(),
			success:function(data) {
				alert(".countOfKnownWordClass : " + data);
				$(".countOfKnownWordClass").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
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
				/* alert("countOfKnownWord : " + data); */
				$(".countOfKnownWordClass").text(data);
			}, error: function (ajaxContext) {
		        alert(ajaxContext.responseText)
		    }
		});
		//해당레벨에 해당되는 단어리스트를 보여줌.
		$('#wordForTheLevel').text("word list for the level : " + $('#wordLevelToUpdate').val());
		//설정창을 보여줌.
		$("#modalSetWordLevel").css("display", "block");   
	});

	//단어설정창을 닫는다.
	$("#closeLayerSetWordLevel").click(function(){	
		
		
		$("#modalSetWordLevel").css("display", "none");   
	});

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
    $("#dispMeaningLangKorea").click(function(){
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
    $("#studyLangEng").click(function(e){
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
    
    $("#comment").on("change keyup paste", function() {    	    
    	countWords();
    });
    
    function countWords() {
    	var string = $("#comment").val();
    	var charactersCount = string.length;
    	var wordCount = string.replace( /[^\w ]/g, "" ).split( /\s+/ ).length;    	
        $("#fileSize").text("Words(" + wordCount + "), Characters(" + charactersCount + ")");            	
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
});



</script>
	
</body>
</html>

