import java.util.Random;
public class NPC extends Creature{

    public NPC(String creatureName, String creatureDescription){
        super(creatureName, creatureDescription);
    }

    public void makeClean(){
        System.out.println("The room is currently " + assignedRoom.getStateOfRoom());
        String currRS = assignedRoom.getStateOfRoom();
        if (currRS.equalsIgnoreCase("dirty")) {
            assignedRoom.setStateOfRoom("half-dirty");
            System.out.println("The room is now " + assignedRoom.stateOfRoom);
        }
        if (currRS.equalsIgnoreCase("half-dirty")) {
            assignedRoom.setStateOfRoom("clean");
            System.out.println("The room is now " + assignedRoom.stateOfRoom);
        }
        else if (currRS.equalsIgnoreCase("clean")) {
            System.out.println("The room is immaculate; it could not be cleaner.");
        }
    }
    
    public void makeDirty(){
        System.out.println("The room was " + assignedRoom.getStateOfRoom());
        String currRS = assignedRoom.getStateOfRoom();
        if (currRS.equalsIgnoreCase("clean")) {
            assignedRoom.setStateOfRoom("half-dirty");
            System.out.println("The room is now " + assignedRoom.stateOfRoom);
        }
        if (currRS.equalsIgnoreCase("half-dirty")) {
            assignedRoom.setStateOfRoom("dirty");
            System.out.println("The room is now " + assignedRoom.stateOfRoom);
        }
        else if (currRS.equalsIgnoreCase("dirty")) {
            System.out.println("The room is horrifying; it cannot get dirtier.");
        }
        
    }


    public int react() {
        //react to a change in the state of room done by itself, another animal or NPC or the PC
        String reaction = "";
        Random rand = new Random(4);
        if (assignedRoom.getStateOfRoom() == assignedRoom.prevState){
            return 0;
        }
        else {
            for(String s : availableRooms) {
                System.out.println(s);
            }
            if (assignedRoom.getStateOfRoom() == "dirty") {
                reaction = (creatureName + " smiles at you widely.");
                System.out.println(reaction);
                return +1;
            } 
            else if (assignedRoom.getStateOfRoom() == "half-dirty") {
                if (assignedRoom.prevState == "dirty") {
                reaction = (creatureName + " smiles at you widely.");
                System.out.println(reaction);
                System.out.println(availableRooms.size());
                return +1;   
                }
                else {
                reaction = (creatureName + " frowns at you and shakes their head.");
                System.out.println(reaction);
                return -1;
                }
            } 
            else if (assignedRoom.getStateOfRoom() == "clean") {
                reaction = (creatureName + " frowns at you and shakes their head.");
                System.out.println(reaction);
                boolean succesfulMove = false;
                String[] directions = { "north", "south", "east", "west" };
                String toMove = "";
                for (int i = 0; i < 100; i++) {
                    toMove = directions[rand.nextInt(4)];
                    if (checkMove(toMove)) {
                        succesfulMove = true;
                        System.out.println(creatureName + " has gone " + toMove);
                        leaveRoom(toMove);
                        return -1;
                    }
                }
                if (!succesfulMove) {
                    System.out.println(creatureName + " drills a hole and leaves through the ceiling.");
                    leaveRoom(toMove);
                    return -1;
                }
            }   
        return 0;
    }
}

    public boolean checkMove(String direction) {
        if (direction.equalsIgnoreCase("north")) {
            if (assignedRoom.toNorth != null && assignedRoom.toNorth.creatures.size() < 10) {
                return true;
            }
        }

        return false;
    }
    }
