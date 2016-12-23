# Codenames Server

run locally: `mvn spring-boot:run`

run on cloud: make sure gcloud project is configured properly, then run
`mvn appengine:deploy`

### More Information

Based on the [springboot example](https://github.com/GoogleCloudPlatform/getting-started-java/tree/master/helloworld-springboot)

### Potentially Useful Links

- [ControllerNotFound](https://smarterco.de/java-spring-boot-mvc-controller-not-called/)

### WebSockets

Websocket support is super limited at this point - create a new snippet in chrome:

```
var stompClient = null;

var stompScript = document.createElement('script');
stompScript.type = 'text/javascript';
stompScript.src = 'https://rawgit.com/jmesnil/stomp-websocket/master/lib/stomp.js';
document.head.appendChild(stompScript);

var sockScript = document.createElement('script');
sockScript.type = 'text/javascript';
sockScript.src = 'https://rawgit.com/sockjs/sockjs-client/master/dist/sockjs-1.1.1.min.js';
document.head.appendChild(sockScript);

function connect() {
    var socket = new SockJS('/ws-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(greeting);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName(name) {
    stompClient.send("/app/hello", {}, name);
}

function showGreeting(message) {
    console.log(message);
}
```

1. Execute this snippet
1. Connect with `connect();`
1. Send a message with `sendName(<your name>);`
1. Return your jaw to its closed position after it dropped upon seeing a response