Anthony Galczak and Anna Carey
UNM CS351 Fall 2017
Project 3: SmartRail

This project was assigned from CS 351 and due Nov. 16, 2017.
The goal was to created a multi-threaded train simulation using
Java and JavaFX.

How to Play:

First, click MakeTrain button to make a train in the trainyard.
Click your created train and click a station to put it in the station.
Click the trains' destination station.
Assuming you picked a valid destination:
    Lights and switches will be reserved on the way to the destination
    Your train will travel to the destination unreserving components
        along the way



Known Bugs:



Program Flow:





List of Extra Features:
Sounds for station arrival and train "GO".
Ability to create LOTS(30 is current max..., could go even higher) of trains
Ability to click on the trains and move them into stations dynamically
Two user alert boxes. Gives user info on what to do and an insight into
    what the simulation is doing.





Possible Future Features and Optimizations:

Dragging and dropping trains into stations.
    - This wouldn't be difficult to do, however bugs and requirements add up 
        quick so it isn't added in this time.

Dynamic filename population of configuration file names rather than hard-coded filename's.
    - I looked into this for about 2 hours. Apparently this is very difficult to
        do within a jar. Without a jar, it's straight-forward to read a directory's
        filename's. I can't spend any more time on it.
        
Making the configuration file able to handle "right-aligned" tracks better
    - I spent some time making this somewhat possible, but the refactor is too large.
        The program is capable of having a station on the right and not one on the left, but
        it requires having leading components leading upto the station only on the right.
        
        
        




