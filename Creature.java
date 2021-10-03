import java.util.*;
public abstract class Creature{

    String creatureName;
    String creatureDescription;
    Room assignedRoom;
    LinkedList<String> availableRooms;

    public Creature(String creatureName, String creatureDescription){
        this.creatureName = creatureName;
        this.assignedRoom = null;
        this.creatureDescription = creatureDescription;
        availableRooms = new LinkedList<String>();
    }

    public String toString(){
        return creatureName + " " + creatureDescription + "\n";
    }

    public void setAssignedRoom(Room r){
        this.assignedRoom = r;
    }

    public abstract int react();
    //when the state of the room is changed, the creature is alerted and able to react accordingly.

    public abstract void makeDirty();
    //the creature is given the ability to make the room dirtier

    public abstract void makeClean();
    //the creature is given the ability to make the room cleaner

    public void leaveRoom(String direction) {
        if (direction.equalsIgnoreCase("north") && assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size()<10) {
            assignedRoom.removeCreature(this);
            assignedRoom.toNorth.addCreature(this);
        }
        else if (direction.equalsIgnoreCase("north") && assignedRoom.toNorth == null) {
            System.out.println("You cannot walk through walls. Turn around please.");
        }
        if (direction.equalsIgnoreCase("south") && assignedRoom.toSouth != null && assignedRoom.toSouth.creatures.size()<10) {
            assignedRoom.removeCreature(this);
            assignedRoom.toSouth.addCreature(this);
        }
        else if (direction.equalsIgnoreCase("south") && assignedRoom.toSouth == null) {
            System.out.println("You cannot walk through walls. Turn around please.");
        }    
        if (direction.equalsIgnoreCase("east") && assignedRoom.toEast != null && assignedRoom.toEast.creatures.size()<10) {
            assignedRoom.removeCreature(this);
            assignedRoom.toEast.addCreature(this);
        }
        else if (direction.equalsIgnoreCase("east") && assignedRoom.toEast == null) {
            System.out.println("You cannot walk through walls. Turn around please.");
            }    
        if (direction.equalsIgnoreCase("west") && assignedRoom.toWest != null && assignedRoom.toWest.creatures.size()<10) {
            assignedRoom.removeCreature(this);
            assignedRoom.toWest.addCreature(this);
            }
        else if (direction.equalsIgnoreCase("west") && assignedRoom.toWest == null) {
            System.out.println("You cannot walk through walls. Turn around please.");
            }    
        }

        public boolean checkMove(String direction) {
            if (direction.equalsIgnoreCase("north")) {
                if (assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size() < 10) {
                    return true;
                }
            }
            if (direction.equalsIgnoreCase("east")) {
                if (assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size() < 10) {
                    return true;
                }
            }
            if (direction.equalsIgnoreCase("west")) {
                if (assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size() < 10) {
                    return true;
                }
            }
            if (direction.equalsIgnoreCase("south")) {
                if (assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size() < 10) {
                    return true;
                }
            }
    
            return false;
        }
        
    }