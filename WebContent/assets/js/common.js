 $.fn.toasterMessages= function() { 	 
toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "positionClass": "toast-top-right",
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			};
};
$.fn.selectTotalCheckboxes= function() {  
$('#maincheckbox').click(function(event) {
	if ($('#maincheckbox').prop('checked')) {
		// alert("checked");		    		 

		$('input[name=delete]').each(function() {
			//alert("calling");
			// alert($(this).attr("id"));
			$(this).prop('checked', true);
		});
	} else {
		// alert("Un checked");
		$('input[name=delete]').each(function() {
			//alert("calling");
			//alert($(this).attr("id"));
			$(this).prop('checked', false);
		});
	}

});
};