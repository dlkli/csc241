import java.util.Random;

public class Animal extends Creature {

    public Animal(String creatureName, String creatureDescription) {
        super(creatureName, creatureDescription);

    }

    public int react() {
        // react to a change in the state of room done by itself, another animal or NPC
        // or the PC
        String reaction = "";
        Random rand = new Random(4);
        if (assignedRoom.getStateOfRoom() == assignedRoom.prevState) {
            return 0;
        } 
        else {
            if (assignedRoom.getStateOfRoom() == "dirty") {
                reaction = (creatureName + " looks at you and growls.");
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
                return -1;
            } 
            else if (assignedRoom.getStateOfRoom() == "half-dirty") {
                if (assignedRoom.prevState == "dirty") {
                    reaction = (creatureName + " jumps on you and licks your face.");
                    System.out.println(reaction);
                    return +1;
                } else {
                    reaction = (creatureName + " looks at you and growls.");
                    System.out.println(reaction);
                    return -1;
                }
            } else if (assignedRoom.getStateOfRoom() == "clean") {
                reaction = (creatureName + " jumps on you and licks your face.");
                System.out.println(reaction);
                return +1;
            }
            return 0;
        }
    }

    public void makeClean() {
        System.out.println("The room was " + assignedRoom.getStateOfRoom());
        String currRS = assignedRoom.getStateOfRoom();
        if (currRS.equalsIgnoreCase("dirty")) {
            assignedRoom.setStateOfRoom("half-dirty");
            System.out.println("The room is now " + assignedRoom.stateOfRoom + ". ");
            react();
        }
        if (currRS.equalsIgnoreCase("half-dirty")) {
            assignedRoom.setStateOfRoom("clean");
            System.out.println("The room is now " + assignedRoom.stateOfRoom + ". ");
            react();
        } else if (currRS.equalsIgnoreCase("clean")) {
            System.out.println("The room is immaculate; it could not be cleaner.");
            react();
        }
    }

    public void makeDirty() {
        System.out.println("The room is currently " + assignedRoom.getStateOfRoom());
        String currRS = assignedRoom.getStateOfRoom();
        if (currRS.equalsIgnoreCase("clean")) {
            assignedRoom.setStateOfRoom("half-dirty");

            System.out.println("The room is now " + assignedRoom.stateOfRoom + ". ");
            react();
        }
        if (currRS.equalsIgnoreCase("half-dirty")) {
            assignedRoom.setStateOfRoom("dirty");
            System.out.println("The room is now " + assignedRoom.stateOfRoom + ". ");
            react();
        } else if (currRS.equalsIgnoreCase("dirty")) {
            System.out.println("The room is horrifying; it cannot get dirtier.");
            react();
        }

    }

}
