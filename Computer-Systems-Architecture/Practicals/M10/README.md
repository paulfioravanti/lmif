# Computer Systems Architecture - Practical 10

Sockets.

## Summarised Original Specifications

Write some basic networking programs using the Socket classes in Java.

## Dependencies

[Telnet][].

```sh
brew install telnet
```

## Compile

```sh
javac *.java
```

## Run

### EchoServer

```sh
java NetworkEchoServer
# in another terminal
telnet localhost 9999
```

### HelloServer

```sh
java NetworkHelloServer
# in another terminal
telnet localhost 9999
```

### NetworkClient

The `NetworkClient` was originally created to be run against a university
server, but as it is, it can be run against the `NetworkHelloServer`

```sh
java NetworkHelloServer
# in another terminal
java NetworkClient
```

[Telnet]: https://en.wikipedia.org/wiki/Telnet
