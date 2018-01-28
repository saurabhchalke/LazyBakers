$(document).ready(function() { 
	document.getElementById("username").focus();

	$("#view_button").bind("mousedown touchstart", function() {
		$("#password").attr("type", "text");
	}), $("#view_button").bind("mouseup touchend", function() {
		$("#password").attr("type", "password");
	});             
});



function  valUsername(){
	if(document.getElementById("username").value.trim()==="" && document.getElementById("username").value!==null)
	{
		$('#responseFail').val('');
		$('#username').val('');

		// $("#above").addClass('hidden');
		$('#message').css('color','red');
		$('#message').html('Please enter username');

		$('input:text').focus(
				function(){
					$(this).css({'border-color' : 'red'});
					$(this).css({'box-shadow' : 'inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px #f15f5f'});
				});

		$('input:text').blur(
				function(){
					$(this).css({'border-color' : '#ccc'});
					$(this).css({'box-shadow' : 'none'});



					$('#username').css({'border-color' : 'red'});
					$('#username').css({'box-shadow' : 'inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px #f15f5f'});
					document.getElementById("username").focus();
					return false;
				}
				else
				{
					var name = $('#username').val();
					if(name=="admin" || name =="Admin" || name=="ADMIN"){
						//$("#above").removeClass('hidden');
						$('#message').html('');
						$('input:text').focus(
								function(){
									$(this).css({'border-color' : 'red'});
									$(this).css({'box-shadow' : 'inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px #f15f5f'});
								});
						document.getElementById("username").focus();
						$('#responseFail').css({'color' : 'red'});
						$('#responseFail').text('You have entered invalid username');

					}
					else{
						$('#responseFail').css({'color' : '#555'});
						$('#responseFail').text('Enter the password for '+name);

						$("#first").addClass('hidden');
						//  $("#above").addClass('hidden');
						$("#first1").addClass('hidden');
						$("#first2").addClass('hidden');
						$("#first3").addClass('hidden');//to hide

						// $("#myId").removeClass('hidden');	//to show
						$("#myId1").removeClass('hidden');
						$("#myId2").removeClass('hidden');
						$("#myId3").removeClass('hidden');
						$("#myId4").removeClass('hidden');

						$("#myId8").removeClass('hidden');
						$('#message').html('');
						// $('#message1').html('');

						$('input:password').focus(
								function(){
									$(this).css({'border-color' : '#66afe9'});
									$(this).css({'box-shadow' : 'inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102,175,233,.6)'});
								});
						$('input:password').blur(
								function(){
									$(this).css({'border-color' : '#ccc'});
									$(this).css({'box-shadow' : 'none'});
								});

						$('Response').html('Enter the password for');
						document.getElementById("password").focus();
					}
				}
	}
	function prevPage() {
		$('#message').html('');
		// $('#message1').html('');
		$('#password').val('');
		$('#responseFail').val('');

		$("#first").removeClass('hidden');
		$("#first1").removeClass('hidden');
		$("#first2").removeClass('hidden');
		$("#first3").removeClass('hidden');//to hide

		//$("#myId").addClass('hidden');	//to show
		$("#myId1").addClass('hidden');
		$("#myId2").addClass('hidden');
		$("#myId3").addClass('hidden');
		$("#myId4").addClass('hidden');

		$("#myId8").addClass('hidden');
		$('#ajaxResponse').css('color','#555');
		$('input:text').focus(
				function(){
					$(this).css({'border-color' : '#66afe9'});
					$(this).css({'box-shadow' : 'inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102,175,233,.6)'});
				});
		$('input:text').blur(
				function(){
					$(this).css({'border-color' : '#ccc'});
					$(this).css({'box-shadow' : 'none'});
				});

		// document.getElementById("username").blur();// to remove auto focus on usename after back is clicked
		document.getElementById("username").focus();
	}
