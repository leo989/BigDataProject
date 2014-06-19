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
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById("result-details"));
}

var displayRoute = function(points) {
	var start = 'orte, VT';
	var endPoint = points.pop();
	var end = new google.maps.LatLng(endPoint.latitude, endPoint.longitude);
	var request = {
		origin: start,
		destination: end,
		travelMode: google.maps.TravelMode.DRIVING,
		waypoints: calculateWaypoints(points),
	};
	directionsService.route(request, function(result, status) {
    	if (status === google.maps.DirectionsStatus.OK) {
    		directionsDisplay.setDirections(result);
    	}
	});	
}

var calculateWaypoints = function(points) {
	var waypoints = new Array();
	points.forEach(function(entry) {
		var lat = entry.latitude;
		var lng = entry.longitude;
		waypoints.push(new Waypoint(new google.maps.LatLng(lat, lng), true))
	});
	return waypoints;
};

function Waypoint(coords, stopover) {
	this.location = coords;
	this.stopover = stopover;
}

google.maps.event.addDomListener(window, 'load', initialize);