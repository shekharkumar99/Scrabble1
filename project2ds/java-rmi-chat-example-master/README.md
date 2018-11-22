# Intro
This is a very simple java RMI client and server applications for a command line chat.

The purpose of this project is to illustrate how Java RMI can be used for both sending data to a server and pushing 
data for the server to the clients without the need of handling raw TCP or UDP socket communication.

# Overview
## Remote
- Contains all remote interfaces that will be shared by both the server and the clients.
- This interfaces represent "the communication contract" between the server and the client.

## Server
This package contains all the server related logic
- `ChatServerDriver`: Entry point to the server program. Creates a server instance and binds it to the RMI registry. 
- `ChatServer`: This class contains all the functionality that our chat service provides.  It provides it's services to 
remote clients by implementing the remote `ChatServerInterface`.

## Client
This package contains all the client related logic
- `ChatServerDriver`: Entry point to the client program. Locates the remote server using the RMI registry, creates a
a `chatClient` instance and registers the instance with the remote server. 
- `chatClient`: This class contains all the behaviour of our chatting client.  It allows the server to push new messages
by implementing the `ChatClientInterface`.

# Configuration
RMI is a bit tricky to setup, so grab a hot drink and follow these steps.
1. Open a terminal and make sure that you can use `rmiregistry` anywhere.  This implies adding `rmiregistry` to your PATH.
2. On a terminal, go to the root of this project and then cd into `cd ./out/production/rmi-chat`.
3. Start the `rmiregistry` FROM THAT LOCATION. This is done by typing `rmiregistry`.
4. You need to modify your IDE's run configuration for both the `ChatServerDriver` and the `ClientServerDriver` as follows:
In VM Options write: `-Djava.rmi.server.codebase=file:/path/to/project/rmi-chat/out/production/rmi-chat`
5. Additionally `ChatServerDriver` needs a command line argument to start (e.g. Alice).

You are all set! You should be able to try this on your localhost with multiple clients on different terminal
sessions.