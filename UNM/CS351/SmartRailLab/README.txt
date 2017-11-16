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
If you start up the program and immediately slam the MakeTrain button 5x or more, very occasionally the canvas
    will "reset" itself and never come back. I'm not entirely sure how this happens, but is a super fringe
    case for those that are a bit impatient to get everything going. Likely has something to do with how JavaFX
    interfaces with the ExecutorService thread that redraws the whole canvas.


Program Flow:
Main launches the UI
Controller holds all of the UI elements in FXML. Calls userConfig to get the configuration to draw/setup.
This configuration is loaded into the conductor that calls a series of classes to load the configuration
    file and create the corresponding objects.
First, RailConfigurationLoader loads the file. 
RailConfiguration loads the lines (from the file) into TrackLine objects.
The TrackLine objects ultimately make all the RailTrack, RailSwitch, RailLight, Station objects.
RailConfiguration now has a hook on all of these and some of these are fed back into the Conductor for
    initial setup. The conductor needs the station to be able to place trains into them and request
    routes to them.

    
There are two types of Interfaces that allow grouping of objects to happen for drawing
and setting up messaging. IDrawable and IMessagable.

RailLight, RailSwitch, RailTrack, Station and Train implement IDrawable
This allows us to get a full list of drawable objects from RailConfiguration to load into
     a thread (reDraw() in Conductor) that constantly redraws the canvas.
     
Railswitch, RailTrack, Station and Train implement IMessagable
This allows us to attach messaging neighbors to each messagable object in each TrackLine as well as
    attaching switches in RailConfiguration.
    
Messaging is critical to our program flow and thread communication. As our messaging methods are synchronized
    this allows multiple threads to simultaneously execute. The queue of messages in each IMessagable is a 
    ConcurrentLinkedQueue which is a thread-safe FIFO data structure.


List of Extra Features:
Sounds for station arrival and train "GO".
Ability to create LOTS(30 is current max..., could go even higher) of trains
Ability to click on the trains and move them into stations dynamically
Two user alert boxes. Gives user info on what to do and an insight into
    what the simulation is doing.
Trains are able to be re selected after arriving in a station or after being
    left for dead (i.e., put a train in station, then click a new train)
Moving trains are able to be given new routes to go to. They will arrive at the
    first station and then reserve a new route to the next station in the queue.
    

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

