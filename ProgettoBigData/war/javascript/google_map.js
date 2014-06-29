var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
	var mapCanvas = document.getElementById('map_canvas');
	var mapOptions = {
		center: new google.maps.LatLng(42.463581, 12.305059),
		zoom: 15,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
	}
	directionsDisplay = new google.maps.DirectionsRenderer();
	map = new google.maps.Map(mapCanvas, mapOptions);
}

var displayRoute = function(points) {
	var startPoint = points[0];
	var endPoint = points[points.length - 1];
	var start = new google.maps.LatLng(startPoint.latitude, startPoint.longitude)
	var end = new google.maps.LatLng(endPoint.latitude, endPoint.longitude);
	var request = {
		origin: start,
		destination: end,
		travelMode: google.maps.TravelMode.DRIVING,
		waypoints: calculateWaypoints(points),
	};
	directionsService.route(request, function(result, status) {
    	if (status === google.maps.DirectionsStatus.OK) {
    		directionsDisplay.setMap(map);
    		directionsDisplay.setDirections(result);
    	}
	});	
}

var hideRoute = function() {
	directionsDisplay.setMap(null);
}

var calculateWaypoints = function(points) {
	var waypoints = new Array();
	var lat, lng;
	for (var i = 1; i < points.length - 1; i++) {
		lat = points[i].latitude;
		lng = points[i].longitude;
		waypoints.push(new Waypoint(new google.maps.LatLng(lat, lng), true));
	}
	return waypoints;
};

function Waypoint(coords, stopover) {
	this.location = coords;
	this.stopover = stopover;
}

google.maps.event.addDomListener(window, 'load', initialize);