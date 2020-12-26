# Computer Systems Architecture - Assignment 2

## Summarised Original Specifications

Implement client and server programs for a shared whiteboard application.

## Compile/Run

```sh
javac -Xlint *.java
# In one terminal
java Server 9999
# In another terminal
java Client localhost 9999 Player1
# In yet another terminal
java Client localhost 9999 Player2
```

Drawing lines in one client should be visible also in the other client.
