<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>김형</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
	.bg-1 {
	    background-color: #1abc9c; /* Green */
	    color: #ffffff;
	}
	.bg-2 {
	    background-color: #474e5d; /* Dark Blue */
	    color: #ffffff;
	}
	.bg-3 {
	    background-color: #ffffff; /* White */
	    color: #555555;
	}
	.container-fluid {
	    padding-top: 70px;
	    padding-bottom: 70px;
	}
  </style>
	<script>
$(document).ready(function(){
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

	$("#btn3").click(function(){	
		alert("btn3 clicked");
		window.open("/DalRead/doA", "myTitle", "height=800, width=300");  
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

    $( "#formText" ).submit(function( event ) {
    	  return true;
    	  event.preventDefault();
    });
    
    $( "#addMeaning" ).click(function() {
    	  $( "#formText" ).submit();
    });
    
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

function readFile() {
	var file = $("#filepath").prop("files")[0];
    $("#fileName").text(file.name);
    $("#fileSize").text("(" + file.size + "byte)");

    var reader = new FileReader();




   reader.readAsText(file, $("#encoding option:selected").val());
   reader.onload = function() {
       $("#comment").val($("#comment").val() + "\n\n" + reader.result);
  };
  reader.onerror = function(evt) {
      alert(evt.target.error.code);
  };

};

</script>

</head>

</head>
<body>
<div class="container">
	 <nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#"></a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">DalRead</a></li>
	      <li id="myOption"><a href="#"><span class="glyphicon glyphicon-option-horizontal"></span> Option</a></li>
	      <li id="myDic"><a href="#"><span class="glyphicon glyphicon-book"></span> Dictionary</a></li>
	      
			<div class="btn-group">
			    
			    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
			    Study
			    <span class="caret"></span>
			    </button>

			    <ul class="dropdown-menu" >

			        <li><a href="#">English</a></li>

			        <li><a href="#">Chinese</a></li>
			    </ul>
			</div>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
	      <li id="login"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	    </ul>
	  </div>
	</nav>
</div>

<div class="container">
  <div class="jumbotron">
    <h1>What is DalRead?</h1>
    <p>DalRead find the unknown words for you automatically.</p>
  </div>
</div>

<div class="container">
	<ul class="nav nav-tabs">
	  <li class="active"><a href="#first">Web</a></li>
	  <li><a href="#second">Text</a></li>	  
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
				   	<button type="submit" class="btn btn-primary" name="addMeaning" id="addMeaning">Add Meanging</button><br />
				   	
				   	<br/>
			        <div>
			            <span id="fileName">File Name</span>
			            <span id="fileSize">File Size</span>
			        </div>
			        <textarea placeholder="Add text here." class="form-control" rows="10" name="comment" id="comment" style="width:600px; height:400px;"></textarea>
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

<span style="float: left">   
    <a href="?lang=en">en</a>   
    |   
    <a href="?lang=ko">ko</a>   
</span> 

달님 이상한
기형달
    
</body>
</html>
