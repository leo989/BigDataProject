$(document).ready(function() {
	$('#search-btn').click(search);
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