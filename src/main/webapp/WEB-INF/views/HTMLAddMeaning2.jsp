<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dalnimsoft.etc.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">		
  	<spring:url value="/resources/css/main.css" var="mainCss" />
  	<spring:url value="/resources/css/w3.css" var="w3Css" />  
	<link href="${mainCss}" rel="stylesheet" />
	<link href="${w3Css}" rel="stylesheet" />
	
	<spring:url value="/resources/js/main.js" var="mainJS" />
	<script src="${mainJS}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
	<script src='http://code.jquery.com/jquery-1.8.3.js'></script>
	<script type='text/javascript' src='jquery.bpopup.min.js'></script>

<!-- 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
<!-- 	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">	 -->
<!-- 	<link rel="stylesheet" href="/resources/css/dr_main.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script> -->
<!-- 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->


	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<spring:url value="/resources/js/jquery.bpopup.min.js" var="bpopupJS" />
	<script src="${bpopupJS}"></script>
	

  	

<style type="text/css">
#element_to_pop_up { 
    background-color:#fff;
    border-radius:15px;
    color:#000;
    display:none; 
    padding:20px;
    min-width:400px;
    min-height: 180px;
}
.b-close{
    cursor:pointer;
    position:absolute;
    right:10px;
    top:5px;
}
</style>
	<script>
$(document).ready(function(){
	$("#p_strOri").hide();			
	var showAddBookmarkImage = true;
	$("tbody tr input[type='checkbox']:not(:checked)").closest('tr').css('background-color', "yellow");
	$("tbody tr input[type='checkbox']:checked").closest('tr').css('background-color', "gray");
	$("tbody tr input[type='checkbox']:checked").closest('tr').hide();
	
	fnShowCountOfWords();
	
	$(document.body).css('padding-top', $('#topnavbar').height() + 10);
    $(window).resize(function(){
        $(document.body).css('padding-top', $('#topnavbar').height() + 10);
    });
    
    
    
/*     $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m); 
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
            "copy": {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: "quit"}
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    }) */
    
	/* $('[data-toggle="tooltip"]').tooltip();    */
	
	//P태그에 있는 모든 단어를 <span>태그를 붙여준다.(나중에 따로 선택할려)
	//TODO : 영어단어만 되고 한글, 중국어등은 안된다.
	//TODO : 영어한글자씩은 안된다. 
/*  	$("p").each(function() { 		
		var $this = $(this);
		 $this.html($this.text().replace(/\b(\w+)\b/g, '<span>$1</span>'));		
		/* $this.html($this.text().replace(/\b(\w+)\b/g, '<span id="$1" data-word="$1" data-wordori="$1_or" data-meaning="단어뜻"><a href="#" data-toggle="tooltip" title="[뜻]">$1</a></span>')); */
		
	/*}); */
	
// 	$(".dalread-text").popover({
//  		trigger:"hover",
//  		html : true,
//  		content : function() {
// 			var htmlContent = "<div>";
// 			htmlContent += "<b>contents</b>";
// 			htmlContent += "</div>";
// 			alert('contetn');
//  			return htmlContent; // $("#contentID").html();
//  		},
//  		title  : function() {
// 			var htmlContent = "<div>";
// 			htmlContent += "<b>title</b>";
// 			htmlContent += "</div>";
//  			return htmlContent; //$("#titleID").html();
//  		}
//  	});	
	
	
	
 	/* $("p span[data-word=the]").hover ( */
//  	$("p span").hover(
// 		function() { $("#word").text($(this).css('background-color', '#ff00ff').text());console.log("hovering : ", $(this).text());},
// 		function() { $("#word").text(''); $(this).css('background-color', ''); }
// 	);
	
 	$("p").mousedown(function() {
 		//마우스 다운을 했을때는 이미 선택된것은 전부 지운다.
 		//뜻 안달린 본문을 선택했을때...
		if (window.getSelection) {
			  if (window.getSelection().empty) {  // Chrome
			    window.getSelection().empty();
			  } else if (window.getSelection().removeAllRanges) {  // Firefox
			    window.getSelection().removeAllRanges();
			  }
			} else if (document.selection) {  // IE?
			  document.selection.empty();
		}
 		
		$("#lblSelectedWord").text('');
// 		$("#lblPronounce").text('');
// 		$("#lblMeaning").text('');
	    $("#txtMeaning").val('');	
		$("#lblMeaning").text('');
		$("#txtPronounce").val('');
		$("#lblPronounce").text('');			
		$("#txtSelectedWord").val('');
		console.log("p mousedown");		
 	}).mouseup(function() {
 		var strPartlySelected = window.getSelection().toString() + "";
 		$("#lblSelectedWord").text(strPartlySelected.trim());
 		openWordDetail();
 		console.log("p mouseup");
 		console.log("strPartlySelected : " + strPartlySelected); 		
 	});
 	 			
 	
	//P태그안에서 span태그가 있을
	$("p span").mousedown ( function() {
// 		$("p span").mousedown ( function() {		
	
		//마우스 다운을 했을때는 이미 선택된것은 전부 지운다.
		if (window.getSelection) {
			  if (window.getSelection().empty) {  // Chrome
			    window.getSelection().empty();
			  } else if (window.getSelection().removeAllRanges) {  // Firefox
			    window.getSelection().removeAllRanges();
			  }
			} else if (document.selection) {  // IE?
			  document.selection.empty();
		}
		console.log("mousedown selected text : " + window.getSelection().toString());
		console.log("mousedown : " + $(this).text());
		/* $("#selectedWord").val(''); */
		$("#lblSelectedWord").text('');
// 		$("#lblPronounce").text('');
// 		$("#lblMeaning").text('');
	    $("#txtMeaning").val('');	
		$("#lblMeaning").text('');
		$("#txtPronounce").val('');
		$("#lblPronounce").text('');			
		$("#txtSelectedWord").val('');
		return false;
	}).mouseup ( function() {
		var strPartlySelected = window.getSelection().toString() + "";
		var strSelected = $(this).attr("data-word");
		var strFinal = "";
		console.log("mouseup selected text : " + strPartlySelected);
		console.log("mouseup : " + strSelected);
		
		//마우스 업을 했을때는 부분 선택한것이 없으면 클릭한 곳의 텍스트를 가져오고, 부분선택한것이 있으면 부분선택한것의 텍스트를 가져온다.
		if (strPartlySelected.length === 0) {				
			console.log("final : " + strSelected);
			strFinal = strSelected;			
		} else {
			console.log("final : " + strPartlySelected);
			strFinal = strPartlySelected;			
		}
		var strMeaning = $(this).attr("data-meaning");
		if (strMeaning) {
			$("#txtMeaning").val(strMeaning);	
			$("#lblMeaning").text(strMeaning);
		}
		var strPronounce = $(this).attr("data-pronounce");
		if (strPronounce) {
			$("#txtPronounce").val(strPronounce);
			$("#lblPronounce").text(strPronounce);
		}
		console.log("mouseup strFinal : " + strFinal);
		$("#txtSelectedWord").val(strFinal);
		$("#lblSelectedWord").text(strFinal);
		
		openWordDetail();
		return false;
	}).dblclick(function() {
		//마우스를 더블클릭했을때의 텍스트를 가져온다.
		var strSelected = $(this).attr("data-word");
		var strFinal = "";
		console.log("dblclick : " + strSelected);		
		$("#txtSelectedWord").val(strSelected);
		$("#lblSelectedWord").text(strSelected);
		var strMeaning = $(this).attr("data-meaning");
		if (strMeaning) {				
			/* $("#txtMeaning").val(strMeaning); */
			$("#lblMeaning").text(strMeaning);
		}
		var strPronounce = $(this).attr("data-pronounce");
		if (strPronounce) {
			/* $("#txtPronounce").val(strPronounce); */
			$("#lblPronounce").text(strPronounce);
		}
	});
	
	$("#btnBookmark").click(function(){	
		
		
		/*  var fromJsp = '${mapWords}';
		alert("daA clicked" + fromJsp); */
		  		  
	});
	

	//단어리스트에서 아는단어를 체크했을때
	$("tbody tr input[type='checkbox']").change(function () {
		var word = $(this).attr("data-word");
		if($(this).is(':checked')){			
			//아는단어라고 체크했으면
			$(this).closest('tr').css('background-color', "Beige");
			fnSetToKnownWord(word); 		
		} else {
			//아는단어라고 체크안했으면(모르는단어이면)
			/* alert("unchecked"); */
			$(this).closest('tr').css('background-color', "Beige");
			fnSetToUnknownWord(word);
		}
	});
	//모르는 단어로 설정
	$("#setToUnknownWord").click(function(){		
		var word = $("#lblSelectedWord").text();		
		fnSetToUnknownWord(word); 				
		
		//단어리스트도 모르는 단어로 해준다.				
		$this = $("#tblWordList > tbody tr input[data-word=\"" + word + "\"]");
		$this.closest('tr').css('background-color', "Beige");
		$this.prop("checked",false);
		$this.closest('tr').show();
	});
    
	//아는 단어로 설정
	$("#setToKnownWord").click(function(){		
		var word = $("#lblSelectedWord").text();			
		fnSetToKnownWord(word);
		
		//단어리스트도 아는 단어로 해준다.
		$this = $("#tblWordList > tbody tr input[data-word=\"" + word + "\"]");
		$this.closest('tr').css('background-color', "gray");
		$this.prop("checked",true);			 		
		if (attrHowToShowWord.toLowerCase() == "show_all_words") {
			$this.closest('tr').show();			
		} else {
			$this.closest('tr').hide();
		}
	});
	
	function fnSetToKnownWord(word) {
		
		$this = $("span[class='dalread_" + word);
		var strMeaning = $this.attr("data-meaning");
// 		alert("$this.attr('data-word') : " + $this.attr("data-word"));
// 		alert("text : " + $this.text());		
		$this.text($this.attr("data-word"));
// 		alert("after $this.attr('data-word') : " + $this.attr("data-word"));
// 		alert("after text : " + $this.text());
		$this.attr("data-know", "2");
		$this.css('font-weight','normal');
		
		var uid = "${sessionScope.UID}";
		/* alert("uid : " + uid); */
		
		$.ajax({
			/* url: 'countOfKnownWord.ajax?uid=' + uid, */
				url: 'setWordKnown.ajax',
				type:'post',
			data: ({uid : uid, word : word}),
			success:function(data) {
					//이건성공하지 못하는데 DB에는 성공하는걸로 나온다.
				/* alert("setWordKnown : " + word); */
			}, error: function (ajaxContext) {
		        /* alert(ajaxContext.responseText) */
		    }
		});
		
		fnShowCountOfWords();
	}
	function fnSetToUnknownWord(word) {			
		$this = $("span[class='dalread_" + word);
		$this.append("<a href='#' data-toggle='tooltip' title=" + strMeaning +">");
		
		var strMeaning = $this.attr("data-meaning");
		var strPronounce = $this.attr("data-pronounce");
// 		alert("Before word : " + word);
// 		alert("Before $this.attr('data-word') : " + $this.attr("data-word"));
		
		if (strMeaning) {
			if (strPronounce) {
				$this.text($this.attr("data-word") + "[" + strPronounce + ", " + strMeaning +"]");
			} else {
				$this.text($this.attr("data-word") + "[" + strMeaning +"]");
			}			
			/* $this.text($this.attr("data-word") + "[" + strMeaning +"]"); */
		} else {
			if (strPronounce) {
				$this.text($this.attr("data-word") + "[" + strPronounce + "]");							
			} else {
				$this.text($this.attr("data-word"));
			}			
		}
		
// 		alert("after $this.attr('data-word') : " + $this.attr("data-word"));
// 		alert("text : " + $this.text());		
		
		$this.attr("data-know", "1");
		$this.css("font-weight", "bold");
		var uid = "${sessionScope.UID}";
		
		$.ajax({
			/* url: 'countOfKnownWord.ajax?uid=' + uid, */
				url: 'setWordUnknown.ajax',
				type:'post', 			
			data: ({uid : uid, word : word}),
				success:function(data) {
				/* alert("setWordKnown : " + word); */
			}, error: function (ajaxContext) {
				
		        /* alert(ajaxContext.responseText) */
		    }
		});
		
		fnShowCountOfWords();
	}
	
	$("#showUnknownOnly").click(function(){		
		var attrHowToShowWord = $(this).attr("data-howToShowWord");		
		if (attrHowToShowWord.toLowerCase() == "show_all_words") {			
			
			$(this).attr("data-howToShowWord", "show_unknown_words").text("Show unknown words");			
			$("tbody tr input[type='checkbox']:not(:checked)").closest('tr').show();
			$("tbody tr input[type='checkbox']:checked").closest('tr').show();
		} else {
			/* var countAllWords = ${listWordsTemp}; */
			$(this).attr("data-howToShowWord", "show_all_words").text("Show all words");
			$("tbody tr input[type='checkbox']:not(:checked)").closest('tr').show();
			$("tbody tr input[type='checkbox']:checked").closest('tr').hide();
		}
		$("tbody tr input[type='checkbox']:checked").closest('tr').toggle();	
	});
	
	$("#showMeaningAdded").click(function(){
		/*//아래는 지우지 말것...javascript에서 jsp에 있는 변수 접근하기이다.. 주석처리를 별표 없이 하면 에러가난다.		
		//var strOri = "${strOri}";
		//alert("strOri : " + strOri);*/
		
		var attrshowMeaningAdded = $(this).attr("data-showMeaningAdded");
		if (attrshowMeaningAdded.toLowerCase() == "show_without_meaning") {						
			$(this).attr("data-showMeaningAdded", "show_meaning_added").text("Show meaning added");			
			$("#p_strOri").show();
			$("#p_addMeaning").hide();
		} else {
			$(this).attr("data-showMeaningAdded", "show_without_meaning").text("Show without meaning");
			$("#p_strOri").hide();
			$("#p_addMeaning").show();
		}						
	});
	
	function fnShowCountOfWords() {


		var cntOfAllWords = $('#tblWordList > tbody > tr').length;
		var cntOfUnknownWords = $('#tblWordList > tbody > tr').find(':checkbox:not(:checked)').length;
		var precentOfKnownWords = (cntOfAllWords - cntOfUnknownWords) / cntOfAllWords;
		precentOfKnownWords *= 100;		
		
		$("#countOfWords").text("모르는 단어수 : " +  cntOfUnknownWords + "/" + cntOfAllWords + "(아는 수준 " + precentOfKnownWords.toFixed(0) + "%)");
	}
// 	//모르는 단어로 설정
// 	$("#setToUnknownWord").click(function(){
// 		/* var word = $("#txtSelectedWord").val(); */
// 		var word = $("#lblSelectedWord").text();
// 		$this = $("span[class='dalread_" + word);		
// 		var strMeaning = $this.attr("data-meaning");
// 		if (strMeaning) {
// 			$this.text($this.attr("data-word") + "[" + strMeaning +"]");
// 		} else {
// 			$this.text($this.attr("data-word"));
// 		}
// 		$this.attr("data-know", "1");
// 		/* alert($this.attr("data-word") + "를 모르는 단어로 변경하였습니다."); */
// 	});
    
// 	//아는 단어로 설정
// 	$("#setToKnownWord").click(function(){
// 		/* var word = $("#txtSelectedWord").val(); */
// 		var word = $("#lblSelectedWord").text();
// 		$this = $("span[class='dalread_" + word);	
// 		$this.text($this.attr("data-word"));
// 		$this.attr("data-know", "2");
// 		/* alert($this.attr("data-word") + "를 아는 단어로 변경하였습니다."); */
// 	});
	
	//단어 뜻 변경
	$("#btnSaveMeaning").click(function() {		
		updateMeaning();
	});
	
	$("#txtMeaning").keyup(function(event){
	    if(event.keyCode == 13){
	    	updateMeaning();
	    }
	});

	function updateMeaning() {
		var strMeaning = $("#txtMeaning").val().trim();
		var strWord = $("#lblSelectedWord").text().trim();
		var strPronounce = $("#txtPronounce").val().trim();		
		
// 		if (strMeaning) {						
			$this = $(".dalread_" + strWord);
			$this.attr("data-meaning", strMeaning);
			/* alert($this.attr("data-word") + "의 뜻을 " + strMeaning + "로 변경하였습니다."); */
			
			var know = $this.attr("data-know");			
			if (know != "2") {
				/* var strPronounce = $this.attr("data-pronounce"); */
				if (strPronounce) {
					$this.text($this.attr("data-word") + "[" + strPronounce + ", " + strMeaning +"]");
				} else {
					$this.text($this.attr("data-word") + "[" + strMeaning +"]");
				}
				$this.css('font-weight','bold');
			}
			
			if (isEmpty(strPronounce)) {
				strPronounce = "";
			}
			$('#messageAfterUpdateMeaning').text(strWord + " changed...").delay(500).fadeIn('fast');
			$('#messageAfterUpdateMeaning').text(strWord + " changed...").delay(1000).fadeOut('fast');
			
			var uid = "${sessionScope.UID}";
			var temp;
			var temp2;
			$.ajax({				
	 			url: 'updateWordMeaning.ajax',
	 			type:'post', 			
				data: ({uid : uid, word : strWord, meaning : strMeaning, pronounce : strPronounce}),
	 			success:function(data) {	 				
					/* alert("updateWordMeaning : " + word); */
				}, error: function (ajaxContext) {
					
			        /* alert(ajaxContext.responseText); */
			    }
			});
// 		}	
	
	}
	
/* 	$("table").click(function() {
		alert("table");
	}); */
	
	$('#my-button').bind('click', function(e) {

        // Prevents the default action to be triggered. 
        e.preventDefault();

        // Triggering bPopup when click event is fired
        $('#element_to_pop_up').bPopup();

    });
	
	//단어리스트에서 레코드를 선택했을때...
	$("table > tbody > tr").click(function(e){     //function_td
		var indexOfFields = 0;
 	   $(this).find('td').each (function() {
 		  // 각 필드의 값을 가져와서 단어, 발음등에 뿌려준다.
 		  if (indexOfFields == 0) { 		
 			 var strWordWithPronounce = $(this).text();
 			var start = strWordWithPronounce.indexOf("[");
 			var strWord  = strWordWithPronounce.substring(0,start);
//  			alert("strWord : " + strWord);
 			$("#txtSelectedWord").val(strWord);
 			$("#lblSelectedWord").text(strWord);
 			
 			var end = strWordWithPronounce.indexOf("]");
 			if (end == 0) {
 				end =  strWordWithPronounce.length;
 			}
 			var strPronounce = strWordWithPronounce.substring(start + 1, end);
// 			alert("strPronounce : " + strPronounce);
			if (strPronounce) {
				$("#txtPronounce").val(strPronounce);
				$("#lblPronounce").text(strPronounce);
			} 		
			
 		  } else if (indexOfFields == 2){
 			  var strMeaning = $(this).text();
//  			 alert("strMeaning : " + strMeaning);
 	  	 		if (strMeaning) {
 	  	 			$("#txtMeaning").val(strMeaning);	
 	  	 			$("#lblMeaning").text(strMeaning);
 	  	 		} 	
 		  }
    		indexOfFields++;
 		});      
		
// 		  $(this).css("font-weight","bold");
		  openWordDetail();
		  e.stopPropagation();
	});
	
	//단어상세창을 띄운다.	
	function openWordDetail() {
		// wordFamily를 가져와서 화면에 보여준다.		
		//테이블의 내용을 지우고 헤더 필드의 색상을 준다.
		$("#wordTable tr:not(:first)").empty();
		$("#wordTable tr:first").css('background-color', "green");
		//아래는 테이블의 헤더까지 지운다.
		/* $('#wordTable').empty(); */
		var uid = "${sessionScope.UID}";
		var strWord = $("#lblSelectedWord").text().trim();
		
		if (strWord == "") {
			return;
		}
		console.log("openWordDetail : " +  strWord);
		$.ajax({
			url: 'getWordFamily',
			data: ({uid : uid, word : strWord}),
			success:function(data) {	
				
				if (isEmpty(data)) {
				
					$("#wordTable").hide();					
				}
				
				var wordOriMeaning = "";
				var wordMeaning = "";
				$.each(data, function(i, word) {
					//기본형의 뜻을 가져오기 위해서 미리 한번 돈다.
					if (word["Word"] == word["WordOri"]) {
						wordOriMeaning = word["Meaning"];							
					}
				});
				
				$.each(data, function(i, word) {
					if (word["Word"] == word["WordOri"]) {
						$("#wordTable").last().append( '<tr style="border-collapse:collapse; border:2px black solid;" id="wordTable_' + word["Word"] + '">' + '<td>'  + word["Word"] + '</td>'+ '<td>' + word["Pronounce"] + '</td>'+ '<td>' + word["Meaning"] + '</td>'+  '</tr>' );					
					} else {
						if (isEmpty(word["Meaning"])) {
							wordMeaning = wordOriMeaning; 							 
						} else {
							wordMeaning = word["Meaning"];
						}
						$("#wordTable").last().append( '<tr style="border-collapse:collapse; border:1px white solid;" id="wordTable_' + word["Word"] + '">' + '<td>'  + word["Word"] + '</td>'+ '<td>' + word["Pronounce"] + '</td>'+ '<td>' + wordMeaning + '</td>'+  '</tr>' );						
					}
					$("#wordTable").last().css('background-color', "gray");
					i++;
				});								
			}, error: function (ajaxContext) {
				$("#wordTable").hide();			
		        alert(ajaxContext.responseText);
		    }
		});			
		
		$.ajax({
			url: 'blnBookmarkedWord',
			data: ({uid : uid, word : strWord}),
			success:function(data) {				
				if (data == true) {
					//단어가 북마크 되어 있으면 북마크 해제하는걸 보여준다.	
					showAddBookmarkImage = false;						
				} else {
					showAddBookmarkImage = true;
						
				}
				fnAddBookmark();
				
			}, error: function (ajaxContext) {
				alert(ajaxContext.responseText);
		    }
		});			
		
		//설정창을 보여줌.
		$("#modalWordDetail").css("display", "block");   
	}

	//단어설정창을 닫는다.
	$("#closeLayerWordDetail").click(function(){	
		$("#modalWordDetail").css("display", "none");   
	});

	//이것을 main.js 로빼면 안먹는다.	
	//비어있는 문자열인지 확인...
	function isEmpty(str) {
		  return typeof str == 'string' && !str.trim() || typeof str == 'undefined' || str === null;
	}

	//북마크 버튼을 눌렀을때...
	$("#btnBookmark").click(function(){		
		var uid = "${sessionScope.UID}";
		var strWord = $("#lblSelectedWord").text();
// 		if ($(this).text() == "Add Bookmark") {
		if (showAddBookmarkImage == true) {			
			showAddBookmarkImage = false;		
			$.ajax({
				url: 'addToBookmark',
				data: ({uid : uid, word : strWord}),
				success:function(data) {								
					fnAddBookmark();	
				}, error: function (ajaxContext) {										
			        alert(ajaxContext.responseText);
			    }
			});	
		} else {				
			showAddBookmarkImage = true;		
			$.ajax({
				url: 'deleteFromBookmark',
				data: ({uid : uid, word : strWord}),
				success:function(data) {			
					fnAddBookmark();
				}, error: function (ajaxContext) {										
					alert(ajaxContext.responseText);
			    }
			});	
			
		}
	});
	
	function fnAddBookmark() {
		if (showAddBookmarkImage == true) {
// 			alert("showAddBookmark");
			$("#btnBookmark").removeClass("glyphicon-star");
			$("#btnBookmark").addClass("glyphicon-star-empty");
// 			$("#btnBookmark").text("Add Bookmark").append("<span class='ui-icon ui-icon-minus'></span>");	
		} else {
// 			alert("showDeleteBookmark");
			$("#btnBookmark").removeClass("glyphicon-star-empty");
			$("#btnBookmark").addClass("glyphicon-star");
			
// 			$("#btnBookmark").text("Delete Bookmark").append("<span class='ui-icon ui-icon-plus'></span>");	
		}
	}
	
	
	//단어 패밀리에서 단어를 선택했을때...
	$("#wordTable > tbody ").on("click", "tr", function(event){
//  	    alert($(this).attr("id"));
//  	  var strMeaning = $(this).find('td nth:child(0)').text();
//  	  alert ("mean" + strMeaning);
		var indexOfFields = 0;
		 $("#lblSelectedWord").text('');
	   $("#txtMeaning").val('');	
	   $("#lblMeaning").text('');
		$("#txtPronounce").val('');
		$("#lblPronounce").text('');
		$(this).siblings().css('background-color', 'gray');
		
 	   $(this).find('td').each (function() {
 		  // 각 필드의 값을 가져와서 단어, 발음등에 뿌려준다.
 		  if (indexOfFields == 0) {
 			 $("#lblSelectedWord").text($(this).text());
 		  } else if (indexOfFields == 1) {
			var strPronounce = $(this).text();
			if (strPronounce) {
				$("#txtPronounce").val(strPronounce);
				$("#lblPronounce").text(strPronounce);
			}
 		  } else {
 			  var strMeaning = $(this).text();
 	  	 		if (strMeaning) {
 	  	 			$("#txtMeaning").val(strMeaning);	
 	  	 			$("#lblMeaning").text(strMeaning);
 	  	 		} 	
 		  }
		indexOfFields++;
 		  $(this).css('background-color', "Beige");

 		});      
	});
	
// 	//단어리스트에서 아는단어를 체크했을때
// 	$("#wordTable > tbody").click(function () {
// 		alert($(this).text());
// 		$(this).css('background-color', "Beige");
// 		e.stopPropagation();
// 	});
// 	//단어리스트에서 전체단어를 보여줄지, 모르는 단어만 보여줄지
// 	$("#showUnknownOnly").click(function(){		
// 		var attrHowToShowWord = $(this).attr("data-howToShowWord");
// 		if (attrHowToShowWord.toLowerCase() == "show_all_words") {
// 			$(this).attr("data-howToShowWord", "show_unknown_words").text("Show unknown words");			
// 		} else {
// 			$(this).attr("data-howToShowWord", "show_all_words").text("Show all words");
// 		}
// 		$("tbody tr input[type='checkbox']:checked").closest('tr').toggle();
// 	});
	
// 	//단어리스트에서 아는단어를 체크했을때
// 	$("tbody tr input[type='checkbox']").change(function () {
// 		if($(this).is(':checked')){
// 			/* alert("checked"); */
// 			//아는단어라고 체크했으면
// 			$(this).closest('tr').css('background-color', "gray");	
// 			var word = $(this).attr("data-word");
// 			$this = $("span[class='dalread_" + word);	
// 			$this.text($this.attr("data-word"));
// 			$this.attr("data-know", "2");
// 		} else {
// 			//아는단어라고 체크안했으면(모르는단어이면)
// 			/* alert("unchecked"); */
// 			$(this).closest('tr').css('background-color', "yellow");
// 			var word = $(this).attr("data-word");
// 			$this = $("span[class='dalread_" + word);		
// 			var strMeaning = $this.attr("data-meaning");
// 			if (strMeaning) {
// 				$this.text($this.attr("data-word") + "[" + strMeaning +"]");
// 			} else {
// 				$this.text($this.attr("data-word"));
// 			}
// 			$this.attr("data-know", "1");
// 		}
// 	});
// 	//아는 단어들/전체단어를 보여준다.
// 	$("#showUnknownOnly").click(function() {
// 		data-show_all_words
// 	});
/* 	$(function() {
	   $( "#tabs" ).tabs();
	});
	
	$( "#datepicker1" ).datepicker({
	    dateFormat: 'yy-mm-dd'
	  }); */
});


</script>

</head>

</head>
<body>

<button id="my-button">POP IT UP</button>
<!-- Element to pop up -->
<div id="element_to_pop_up">
    <a class="b-close">x<a/>
    Content of popup
</div>


<!-- My Dictionary 모달 시작 -->
<div class="modal modal-center fade" id="MyDicModal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-center modal-lg">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>							        
		        <h4 class="modal-title">My Dictionary</h4>
	      </div>
			<div class="modal-body">								
				<div>You knows <span class="countOfKnownWordClass"></span> words.</div>
		    	<input type="number" id="wordLevelToUpdate" name="quantity" min="1" max="17" value=1>							    	
		    	<button type="button" class="btn btn-primary" name="updateWordLevel" id="updateWordLevel">Update word level</button><br />
		    	<div>
		    		<span>레벨에 맞는 단어들</span>
		    		<span></span>
		    	</div>
		    	
		    	
		<table class="table table-bordered">
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
		</div>
	</div>
</div>
		<!-- My Dictionary 모달 끝 -->
<!-- The Modal -->
<div id="modalWordDetail" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close" id="closeLayerWordDetail">×</span>      
    </div>
    <div class="modal-body">
	    <div class="container">
			<!-- <div class="w3-green w3-container w3-light-grey"> -->
				<button type="button" class="btn btn-default glyphicon glyphicon-star" id="btnBookmark" ></button>선택된 단어 : <span id="lblSelectedWord"></span> <br>
				<!-- <input type="text" id="txtSelectedWord" class="form-control" placeholder="text here"> <br> -->
				발음 : <span id="lblPronounce"></span> <br>
				<input type="text" id="txtPronounce" class="" placeholder="pronounce here"> <br>
				뜻 : <span id="lblMeaning"></span> <br>
				<input type="text" id="txtMeaning" class="" placeholder="meaning here"> <br>
				
				<button type=button class="btn btn-primary" id="btnSaveMeaning">Save Meaning</button> <br>
				
				단어 아는 정도 설정 : 
				 <div class="btn-group">
				  <button type="button" class="btn btn-primary" id="setToUnknownWord">Unknown word</button>
				  <button type="button" class="btn btn-primary" id="setToKnownWord">Known word</button>
					

<!-- 				  <button type="button" class="btn btn-primary" id="btnBookmark">Add Bookmark</button> -->
				  <span id="messageAfterUpdateMeaning">update.....</span>
				</div>				
		</div>
		<div >
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
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div>
</div>


<%-- <div class="container">
	<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="topnavbar">
	<!-- <div class="navbar-header navbar-fixed-top"> -->
	<!-- <div class="w3-green w3-container w3-quarter dr_left w3-light-grey"> -->
	<div class="w3-green w3-container w3-light-grey">
		<jsp:useBean id="bean" class="com.dalnimsoft.dalread.DalReadText" />
	Message is: <jsp:getProperty name="bean" property="text" /> 
	
		
		선택된 단어 : <span id="lblSelectedWord"></span> <br>
		<!-- <input type="text" id="txtSelectedWord" class="form-control" placeholder="text here"> <br> -->
		발음 : <span id="lblPronounce"></span> <br>
		<input type="text" id="txtPronounce" class="form-control" placeholder="pronounce here"> <br>
		뜻 : <span id="lblMeaning"></span> <br>
		<input type="text" id="txtMeaning" class="form-control" placeholder="meaning here"> <br>
		
		<button type=button class="btn btn-primary" id="btnSaveMeaning">Save Meaning</button> <br>
		
		단어 아는 정도 설정 : 
		 <div class="btn-group">
		  <button type="button" class="btn btn-primary" id="setToUnknownWord">Unknown word</button>
		  <button type="button" class="btn btn-primary" id="setToKnownWord">Known word</button>
		  <button type="button" class="btn btn-primary" id="doA">doA</button>
		  <span id="messageAfterUpdateMeaning">update.....</span>
		</div>
		
	</div>
	</div>
</div> --%>


<div class="w3-container w3-half dr_left w3-pale-blue">    
	<!-- 서블릿에서 req.setAttribute("comment", "my name is"); 로 값을 담아두었을때 꺼내오기  -->
<!-- 	<div class="container">
	  <div class="jumbotron">
 -->	    <!-- <h1>Added Meaning</h1> -->
 		<p id="p_strOri">${strOri }</p>
	    <p id="p_addMeaning">${addMeaning }</p>
	<!--   </div>
	</div> -->
</div>



<div class="w3-container w3-quarter dr_right w3-light-grey">		
	<button type=button class="btn btn-primary" name="showMeaningAdded" id="showMeaningAdded" data-showMeaningAdded ="show_without_meaning">Show without meaning</button>
	<button type=button class="btn btn-primary" name="showUnknownOnly" id="showUnknownOnly" data-howToShowWord ="show_all_words">Show all words</button>
	
<%-- 생년월일: <input type="text" id="datepicker1">

   이건 
	파싱된 문장 : <input type="text" id="selectedWord" class="form-control" placeholder="text here"> <br>
	<!-- HTML에서 직접 jsp를 호출할때  -->
	<p> param ${param.commenttt } </p><br>
	
	
	

    <div id="tabs">
  <ul>
    <li><a href="#tabs-1">Nunc tincidunt</a></li>
    <li><a href="#tabs-2">Proin dolor</a></li>
    <li><a href="#tabs-3">Aenean lacinia</a></li>
  </ul>
  <div id="tabs-1">
    <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
  </div>
  <div id="tabs-2">
    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
  </div>
  <div id="tabs-3">
    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
  </div>
</div>
      --%>
<%-- 	<table class="table table-hover">
	    <thead>
	      <tr>
	        <th>Word</th>                
	        <th>Know</th>
	        <th>Meaning</th>
	        
	      </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="myDic" items="${myDicList}" varStatus="status">
	    	<tr>
	    		<td><c:out value="${myDic.Word }"/><br><c:out value="[${myDic.Pronounce }]"/></td>
	    		<td>
		    		<c:choose>
					    <c:when test="${myDic.Know eq '3'}">
					        <input id="input_${myDic.Word }" class="input_${myDic.Word }" data-word=${myDic.Word } type="checkbox" checked="checked" value="">
					    </c:when>
					    <c:when test="${myDic.Know ne '3'}">
					        <input id="input_${myDic.Word }" class="input_${myDic.Word }" data-word=${myDic.Word } type="checkbox" value="">
					    </c:when>
					    <c:otherwise>
					         <input id="input_${myDic.Word }" class="input_${myDic.Word }" data-word=${myDic.Word } type="checkbox" value="">
					    </c:otherwise>
					</c:choose>
				</td>    
				<td><c:out value="${myDic.Meaning }"/></td>		
	      </tr>
	      </c:forEach>
	    </tbody>
	</table> --%>
		<%-- <span id="countOfWords">단어수 : ${fn:length(listWordsTemp)}</span> --%>
		<span id="countOfWords">단어수 : ${fn:length(listWordsTemp)}</span>
		<table class="table table-hover" id="tblWordList">
		    <thead>
		      <tr>
		        <th>Word</th>                
		        <th>Know</th>
		        <th>Meaning</th>
		        <th>Frequency</th>
		        
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach var="word" items="${listWordsTemp}" varStatus="status">
		    	<%-- word : ${word } <br> --%>
		    	<c:set var="word" value="${word}"/>
		    	<tr>
		    	
		    		<td><c:out value="${word }"/><br><c:out value="[${mapWords[word]['Pronounce'] }]"/></td>
		    		<td>
		    			<c:set var="word_known" value="${Constants.WORD_KNOWN}"></c:set>
<%-- 		    			word_known : ${word_known }
		    			Constants.WORD_KNOWN : ${Constants.WORD_KNOWN} --%>
			    		<c:choose>
						    <c:when test="${mapWords[word]['Know'] eq '2'}">
						    <!-- 아래와 같은 표현은 안 먹히네... 쩝... -->
						    <%-- <c:when test="${mapWords[word]['Know'] eq ${word_known}}"> --%>
						        <input id="input_${mapWords[word]['Word']}" class="input_${mapWords[word]['Word']}" data-word="${mapWords[word]['Word']}" type="checkbox" checked="checked" value="">
						    </c:when>
						    <c:when test="${mapWords[word]['Know'] ne '2'}">
						    <%-- <c:when test="${mapWords[word]['Know'] ne '${word_known}'}"> --%>
						        <input id="input_${mapWords[word]['Word']}" class="input_${mapWords[word]['Word']}" data-word="${mapWords[word]['Word']}" type="checkbox" value="">
						    </c:when>
						    <c:otherwise>
						         <input id="input_${mapWords[word]['Word']}" class="input_${mapWords[word]['Word']}" data-word="${mapWords[word]['Word']}" type="checkbox" value="">
						    </c:otherwise>
						</c:choose>
					</td>    
					<td><c:out value="${mapWords[word]['Meaning']}"/></td>			
					<td><c:out value="${mapWords[word]['Frequency']}"/></td>
		      </tr>
		      </c:forEach>
		    </tbody>
		</table>
</div>
    
</body>
</html>
