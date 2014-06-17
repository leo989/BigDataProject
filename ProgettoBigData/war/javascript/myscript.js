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
	})
})

var search = function() {
	$.ajax('/search', {
		dataType: 'json',
		data: $('#input-form').serializeArray(),
		success: function(data) {
			displayRoute(data);
		},
		error: function() {
			console.log('[ERRORE]');
		},
	});
};