$(document).ready(function() {
	
		
	// Do DELETE a Customer via JQUERY AJAX
	$(document).on("click","a",function() {
		
		var customerId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "./delete/" + customerId,

			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Customer with Id=" + customerId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#customerTable tbody tr:odd" ).addClass("info");
				$( "#customerTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				//alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
})