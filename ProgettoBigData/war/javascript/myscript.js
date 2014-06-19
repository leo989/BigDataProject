$(document).ready(function() {
	
	$('#search-btn').click(search);

	$('#type-of-search').change(function(){
		if(this.value === '2'){
			$('#enableAPIs').fadeIn();
		}else{
			if (this.value === '3') {
				$('#best-total-cost').fadeIn();
				$('#enableAPIs').fadeIn();
			} else {
				$('#best-total-cost').hide();
				$('#enableAPIs').hide();
			}
		}
	});
})

var search = function() {
	$.ajax('/search', {
		dataType: 'json',
		data: $('#input-form').serializeArray(),
		success: function(data) {
			if(data != null){
				displayRoute(data);
				$('#result-details').fadeIn();
				$('#errore').fadeOut();
			}else{
				$('#result-details').fadeOut();
				$('#error').fadeIn();
			}
		},
		error: function() {
			console.log('[ERRORE]');
		},
	});
};

