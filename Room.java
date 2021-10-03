import java.util.*;

public class Room{

    String roomName;
    String stateOfRoom;
    String prevState;
    String roomDescription;
    HashMap<String, Creature> creatures;
    Room toNorth;
    Room toSouth;
    Room toEast;
    Room toWest;
   

    public Room(String roomName, String stateOfRoom, String roomDescription){
        this.roomName = roomName;
        this.stateOfRoom = stateOfRoom;
        this.prevState = stateOfRoom;
        this.roomDescription = roomDescription;
        creatures = new HashMap<>();

    }

    public void setStateOfRoom(String stateOfRoom){
        this.stateOfRoom = stateOfRoom;
    }

    public String getStateOfRoom(){
        return stateOfRoom;
    }

    public String getRoomName(){
        return roomName;
    }

    public String checkSurroundings(){
        //method to look to adjacent rooms, see their name and description
        String neighbors = "The rooms around you are ";
        if (this.toNorth == null) {
            System.out.println("There is actually no room North of here.");
        } else {
            neighbors = neighbors + toNorth.roomName;
        }
        return neighbors;
        
    }

    public void addCreature(Creature a){
       creatures.put(a.creatureName.toLowerCase(), a);
        a.setAssignedRoom(this);
        }


    public void removeCreature(Creature creatureToRemove){
        creatures.remove(creatureToRemove.creatureName.toLowerCase());
    }

    public String toString(){
        String retValue = "Room ";
        retValue = retValue + roomName + ", " + roomDescription + ". with creatures: \n";
        for (Creature c : creatures.values()) {
            retValue = retValue + c.toString();
        }
        return retValue;
    }

    public void checkState(){
        System.out.println(stateOfRoom);
    }

    public int stateChange(){
        int respChange = 0;
        HashMap<String, Creature> iterator = new HashMap<>();
        iterator.putAll(creatures);
        for(Creature c : iterator.values()) {
            respChange = respChange + c.react();
        }
        prevState = stateOfRoom;
        
        return respChange;
    }
}
