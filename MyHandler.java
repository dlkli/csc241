import org.xml.sax.Attributes;
import org.xml.sax.helpers.*;

public class MyHandler extends DefaultHandler {
    private Room lastRoom;
    private Room[] rooms = new Room[100];
    private int numRooms = 0;
    public static PC readPC;

    @Override
    public void startDocument() {
    //    System.out.print ("Begin parsing");
        }

    private Room findOrCreateRoom(String name) {
        for(int i = 0; i < numRooms; i++) {
            if (rooms[i].roomName.equals(name))
            return rooms[i];
        }
        Room retValue = new Room(name, "", "");
        rooms[numRooms] = retValue;
        numRooms = numRooms + 1;
        return retValue;
    }

    @Override
    public void startElement (String uri, String localName, String qname, Attributes attributes) {
    //    System.out.print("Starting element..." + qname);
        if (qname.equals("room")) {

            String roomName = attributes.getValue("name");
            String stateOfRoom = attributes.getValue("state");
            String roomDescription = attributes.getValue("description");
            //System.out.println("name: " + roomName);
            //System.out.println("state: " + stateOfRoom);
            //System.out.println("description: " + roomDescription);
            Room currentRoom = findOrCreateRoom(roomName);
            currentRoom.roomDescription = roomDescription;
            currentRoom. stateOfRoom = stateOfRoom;
            

             String eastName = attributes.getValue("east");
             
                if (eastName != null) {
                    Room eastNeighbor = findOrCreateRoom(eastName);
                     currentRoom.toEast = eastNeighbor;
                     eastNeighbor.toWest = currentRoom;
                     //System.out.println("east: " + eastName);
                }
                else {
                    // System.out.println ("There actually is no east neighbor...");
                }
             String westName = attributes.getValue("west");
             
                if (westName != null) {
                     Room westNeighbor = findOrCreateRoom(westName);
                    currentRoom.toWest = westNeighbor;
                     westNeighbor.toEast = currentRoom;
                    // System.out.println("west: " + westName);
                }
                else {
                //    System.out.println ("There actually is no west neighbor...");
                }
            String northName = attributes.getValue("north");
            
                if (northName != null) {
                    Room northNeighbor = findOrCreateRoom(northName);
                    currentRoom.toNorth = northNeighbor;
                    northNeighbor.toSouth = currentRoom;
                //    System.out.println("north: " + northName);
                }  
                else {
                //    System.out.println("There actually is no north neighbor...");
                } 
            String southName = attributes.getValue("south");
            
                if (southName != null) {
                    Room southNeighbor = findOrCreateRoom(southName);
                    currentRoom.toSouth = southNeighbor;
                    southNeighbor.toNorth = currentRoom;
                //    System.out.println("south: " + southName);
                }
                else {
                //    System.out.println("There actually is no south neighbor...");
                }
        lastRoom = currentRoom;

    }  else if (qname.equals("animal")) {
            String creatureName = attributes.getValue("name");
            String creatureDescription = attributes.getValue("description");
            Animal currentCreature = new Animal(creatureName, creatureDescription);
            lastRoom.addCreature(currentCreature);
    }   else if (qname.equals("NPC")) {
            String creatureName = attributes.getValue("name");
            String creatureDescription = attributes.getValue("description");
            NPC currentCreature = new NPC(creatureName, creatureDescription);
            lastRoom.addCreature(currentCreature);
    }   else if (qname.equals("PC")) {
            String creatureName = attributes.getValue("name");
            String creatureDescription = attributes.getValue("description");
            readPC = new PC (creatureName, creatureDescription);
            lastRoom.addCreature(readPC);
    }
}   
@Override
public void endElement(String uri, String localName, String qname) {
//    System.out.println("Ending tag " + qname);
}
@Override
public void endDocument() {
   // System.out.println("Ending parsing.");
   // System.out.println("Number of rooms: " + numRooms);
    }

public Room[] getRooms() {
    return rooms;
}

public int getNumRooms() {
    return numRooms;
}
}