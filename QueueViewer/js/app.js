var dwws = dwws || {};

(function (dwws, $) {
   "use strict";

   var $status = $("#status");
   var socket = null;

   function connectSocket() {
      if(socket == null) {
         socket = new WebSocket("ws://localhost:8080/ws/");

         socket.onopen = function() {
            console.log("Connected!");
            $status.prepend("<p>Connected websocket!</p>");
         };

         socket.onclose = function() {
            console.log("Closed!");
            $status.prepend("<p>Closed websocket</p>");
         };

         socket.onmessage = function(response) {
            console.log("Job:", response);
            $status.prepend("<p>" + response.data + "</p>");

             var json = jQuery.parseJSON(response.data);

                        var newRow='<tr>'+
                        '<td>'+json.jobType+'</td>'+
                        '<td>'+json.url+'</td>'+
                        '<td>'+json.source+'</td>'+
                        '<td>'+(new Date(json.jobCreated)).toLocaleString()+'</td>'+
                        '</tr>';
                        $('#jobs tbody').append(newRow);
         };
      }
   }

   $("#connectBtn").click(connectSocket);

   $("#disconnectBtn").click(function() {
      if(socket != null) {
         socket.close();
         socket = null;
      }
   });
}(dwws, jQuery));
