$(document).ready(function() {
    loadNewsFeed();
});

function loadNewsFeed() {
    var sightingTable = $("#sighting-table");
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/SuperSightings/recentsightings",
        success: function(sightingList){
            $.each(sightingList, function(index, item){
                var addItem = "<tr>";
                    addItem += "<td>" + item.location.name + "</td>";
                    addItem += "<td>";
                    addItem += item.date.monthValue + "/" + item.date.dayOfMonth + "/" + item.date.year;
                    addItem += "</td>";
                    addItem += "<td>";
                    addItem += "<a href='sightingDetails?sightingId=" + item.sightingId + "'>";
                    addItem += "View Details";
                    addItem += "</a>";
                    addItem += "</td>";
                    addItem += "</tr>";
                    
                    sightingTable.append(addItem);
            });
        },
        error: function(){
            alert("Could not get recent sightings from server");
        }
    });
}