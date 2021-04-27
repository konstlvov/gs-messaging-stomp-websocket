var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var statusLineTextElement = document.getElementById('statusline');
    var statusLineTextNode = statusLineTextElement.childNodes[0];
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/msg', function (msgFromServer) {
            //console.log("msg from server received");
            //console.log(msgFromServer);
            var msg = JSON.parse(msgFromServer.body); // msgFromServer contains much more than body
            if (msg) {
                //console.log(msg); // debug
                var elementId = msg.elementId;
                var elementText = msg.elementText;
                if (elementId && elementText) {
                    //document.getElementById(elementId).innerText = elementText; // вроде бы медленный способ
                    //$('#' + elementId).text(elementText); // вроде бы быстрее
                    //document.getElementById(elementId).childNodes[0].nodeValue = elementText; // вроде бы еще быстрее...
                    //statusLineTextNode.nodeValue = elementText; // возможно, еще быстрее - экономим на getElementById
                    // (но так не установить произвольный элемент, т.е. теряется универсальность)
                    statusLineTextElement.textContent = elementText; // вроде бы тоже быстро
                }
            }
            else {
                console.error("msg cannot be parsed");
            }

        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function runLongJob() {
    stompClient.send("/app/run-long-job", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#runLongJob" ).click(function() { runLongJob(); });
});

