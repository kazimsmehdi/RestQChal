
$(function () {
    "use strict";
    var status = $('#status');
    var input = $('#input');

    var socket = $.atmosphere;
    var request = { url: 'http://localhost:8080/lastjob',
        contentType : "application/json",
        logLevel : 'debug',
        transport : 'websocket' ,
        fallbackTransport: 'long-polling'};


    request.onOpen = function(response) {
        status.text('Atmosphere connected using ' + response.transport);
    };

    request.onMessage = function (response) {
        var message = response.responseBody;
        try {
            console.log(message);
            var json = jQuery.parseJSON(message);

            var newRow='<tr>'+
            '<td>'+json.jobType+'</td>'+
            '<td>'+json.url+'</td>'+
            '<td>'+json.source+'</td>'+
            '<td>'+(new Date(json.jobCreated)).toLocaleString()+'</td>'+
            '</tr>';
            $('#jobs tbody').append(newRow);

        } catch (e) {
            console.log('This doesn\'t look like a valid JSON: ', message.data);
            return;
        }


    };

    request.onClose = function(response) {
          status.text('Atmosphere connected close ');
    }

    request.onError = function(response) {
       status.text('Sorry, but there\'s some problem with your '
                                    + 'socket or the server is down');
    };

    this.subSocket = socket.subscribe(request);

    input.keydown(function(e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();

            // First message is always the author's name

           // subSocket.push(jQuery.stringifyJSON({ author: msg, message: msg }));
            subSocket.push();
            $(this).val('');
        }
    });

  });


