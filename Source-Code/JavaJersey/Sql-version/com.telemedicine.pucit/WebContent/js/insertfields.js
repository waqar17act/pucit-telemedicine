$('document').ready(function(){
	  document.getElementById("persid").value = Math.floor(Math.random()*(1000000-0+1)+0);
		var things = ['the','of','and','a','to','in','is','you','that','it','he','was','for','on','are','as','with','his','they','I','at','be','this','have','from','or','one','had','by','word','but','not','what','all','were','we','when','your','can','said','there','use','an','each','which','she','do','how','their','if','will','up','other','about','out','many','then','them','these','so','some','her','would','make','like','him','into','time','has','look','two','more','write','go','see','number','no','way','could','people','my','than','first','water','been','call','who','oil','its','now','find','long','down','day','did','get','come','made','may','part'];
		var gender = ['Male','Female'];
		document.getElementById("name").value = things[Math.floor(Math.random()*things.length)];
		document.getElementById("gender").value = gender[Math.floor(Math.random()*gender.length)];
		document.getElementById("role").value = things[Math.floor(Math.random()*things.length)];
		document.getElementById("contact").value = things[Math.floor(Math.random()*things.length)];
		document.getElementById("name").value = things[Math.floor(Math.random()*things.length)];
		
		
		
		
		
		document.getElementById("submit_it").click();
		//document.forms["post_example"].submit();
	
		 
		//    window.location.reload();  
		
	$('#post_example').submit(function () {
		 return false;
		});
	
	  $("#post_example").submit(function(e) {
		    e.preventDefault();
		});
	
});