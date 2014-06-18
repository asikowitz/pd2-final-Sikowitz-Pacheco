pd2-final-Sikowitz-Pacheco
==========================

Period 2 Final Project--Dot Warfare

Group Members: Andrew Sikowitz, Kai Pacheco<br>
Description: Dot Warfare, a game of multitasking in which you must try to survive against the attacks of an opponent player by dodging their weapons and making your own walls, while sending your own weapons at them. <br> <br>

Instructions: The files for the game are in the folder "Processing/src".<br>
javac -cp "../core.jar:." Server.java Proc.java <br>
To run, you must first open a server. <br>
java -cp "../core.jar:." Server <br>
The two players (on different computers) should then open clients. Note that they must be on the same network as the server.  (Tested on Homer) <br>
java -cp "../core.jar:." Proc <Server IP> <br>
The game will start once both clients have connected <br> <br>

Features: Ability to draw lines in processing and "walk" on those lines as player. Ability to have multiple keyboard inputs at once. Multiple weapons with varied movement and effects. Networking between a server and two clients sending information about weapons, walls, and player location. Energy mechanic. Ability to create weapons at different angles. Linked List for storing walls, because I can store them by always added to the tail and deleting the head only. Stack storage for power ups. <br> <br>

Problems: Server does not automatically remove clients from arraylist, eventually causing null-pointer exception. <br> <br>

Algorithms: Basic socket programming with a server that waits for two connections before initiating a game that is run on a "MiniServer"<br>
Information is sent over the network by transforming it into an array of ints and reading this array of ints in a particular manner. <br>
Moving left or right along a wall checks for several possible locations, allowing for scaling of uneven walls. <br>
Multiple variables that determine if keys are still pressed down to allow multiple keyboard inputs, because Processing usually does not allow this (only has variable key, which is the last-pressed key). <br>
Acceleration of players and grenades that increase vertical speed by a certain amount each call. <br>
Calculation of distance using the distance formula to determine if objects are touching and to determine how much energy to remove. <br>
Variables to make sure a mouse click meant for one action (e.g. drawing walls) does not later lead to another action (e.g. making a weapon). <br><br>

Using Eclipse because my computer can only run Processing 1.5.
