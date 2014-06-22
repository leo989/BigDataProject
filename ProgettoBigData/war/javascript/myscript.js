$(document).ready(function() {
	
	$('#search-btn').click(function(e) {
		$(this).button('loading');
		search();
	});

	$('#type-of-search').change(function() {
		switch (this.value) {
			case '2':
				$('#enableAPIs').fadeIn();
				break;
			case '3':
				$('#best-total-cost').fadeIn();
				$('#enableAPIs').fadeIn();
				break;
			default:
				$('#best-total-cost').hide();
				$('#enableAPIs').hide();
		}
	});
})

var search = function() {
	hideRoute();
	visualizer('hide', '#no-results')
	visualizer('hide', '#error')
	$.ajax('/search', {
		dataType: 'json',
		data: $('#input-form').serializeArray(),
		success: function(data) {
			if (data != null) {
				displayRoute(data);
			} else {
				visualizer('show', '#no-results');
			}
		},
		error: function() {
			visualizer('show', '#error');
		},
		complete: function() {
			$('#search-btn').button('reset');
		},
	});
};

var visualizer = function(toggle, item_id) {
	var item = $(item_id);
	switch (toggle) {
		case 'show':
			item.show();
			break;
		case 'hide':
			item.hide();
			break;
	}
}