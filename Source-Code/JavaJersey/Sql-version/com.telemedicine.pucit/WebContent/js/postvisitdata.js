/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	console.log("ready");
	
	var $post_example = $('#post_example');
	
	var url      = window.location.hostname;     // Returns full URL
	console.log(url);
	$('#submit_it').click(function(e) {
		
		console.log("submit button has been clicked");
		//e.preventDefault(); //cancel form submit
		
		 
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://"+url+":7001/com.telemedicine.pucit/api/addvisitdata",
//			url: "http://localhost:7001/com.telemedicine.pucit/api/addvisitdata",
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				//console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
				console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
	
});
