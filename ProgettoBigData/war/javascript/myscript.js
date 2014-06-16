$(document).ready(function() {
	$('#search-btn').click(search);
	$('#type-of-search').change(function(){
		if (this.value === '3') {
			$('#best-total-cost').fadeIn();
		} else {
			$('#best-total-cost').hide();
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