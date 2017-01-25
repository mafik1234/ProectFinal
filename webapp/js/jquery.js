$(document).ready(function(){
	$('.closeMessage').click(function(){
		$('.messagesContainer').css("display", "none");
	});
	
	$('.contactsList ul li').click(function(){
		$('.messagesContainer').css("display", "block");
		var contactId = $(this).attr("id");
		var contactName = $(this).html();
		$('#contactName').html(contactName);
	});
	
	$('.contactsTitle').click(function(){
		if ($('.contactsListContainer').css("top") == "0px") {
			$('.contactsListContainer').css("top", "-250px");
			$('.contactsListScroll').css("display", "block");
			getUsersList();
		} else {
			$('.contactsListContainer').css("top", "0px");
			$('.contactsListScroll').css("display", "none");
		}
	});
	
	function getUsersList() {
		$.ajax({
			type: "get",
			dataType: "json",
			url: "listUsers",
			succes: function(data) {
				alert("succes");
			},
			error: function(e) {
				var out = '';
			    for (var i in e) {
			        out += i + ": " + e[i] + "\n";
			    }

			    //alert(out);
			},
			done: function() {
				alert("done");
			}
		});
	}
});