function getContentFromContentServlet(servletContent) {
	//alert("getContent was pressed");
	$.ajax({
		method : 'POST',
		url : 'contentServlet',
		data : {
			content : servletContent
		},
		success : function(response) {
			//document.getElementById("servlet_content").innerHTML += response + "\r\n";
			$('#servlet_content').html(response);
		}
	});
	//alert("function getContent() ended");
}