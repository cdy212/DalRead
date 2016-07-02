/**
 * 
 */

function readFile() {
	alert('readfile');
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

//function isEmpty(str) {
//	  return typeof str == 'string' && !str.trim() || typeof str == 'undefined' || str === null;
//}
//
