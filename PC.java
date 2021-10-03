import java.util.*;

public class PC extends Creature{

    int respect;

    public PC(String creatureName, String creatureDescription){
       super(creatureName, creatureDescription);
       respect = 40;
       
    }

    public void play(){
        String cmdInp = "";
        Scanner s = new Scanner(System.in);
        System.out.println("What would you like to do?");
        do {
            cmdInp = s.nextLine();
            String [] parts = cmdInp.split(":");
            if (parts.length == 1) {
                if (cmdInp.equalsIgnoreCase("help")) {
                helpCommand();
                }
                else if (cmdInp.equalsIgnoreCase("look")) {
                lookAround();
                }
                else if (cmdInp.equalsIgnoreCase("clean")) {
                makeClean();
                respect = respect + assignedRoom.stateChange();
                System.out.println("Your respect is now " + respect + ".");
                }
                else if (cmdInp.equalsIgnoreCase("dirty")) {
                makeDirty();
                respect = respect + assignedRoom.stateChange();
                System.out.println("Your respect is now " + respect + ".");
                }
                else if (cmdInp.equalsIgnoreCase("north")) {
                this.leaveRoom("north");
                System.out.println("You are now in " + assignedRoom.roomName);
                }
                else if (cmdInp.equalsIgnoreCase("east")) {
                this.leaveRoom("east");
                System.out.println("You are now in " + assignedRoom.roomName);
                }
                else if (cmdInp.equalsIgnoreCase("west")) {
                this.leaveRoom("west");
                System.out.println("You are now in " + assignedRoom.roomName);
                }
                else if (cmdInp.equalsIgnoreCase("south")) {
                this.leaveRoom("south");
                System.out.println("You are now in " + assignedRoom.roomName);
                }
                else if (cmdInp.equalsIgnoreCase("respect")) {
                System.out.println("Your respect is " + respect + ".");
                }
                else if (cmdInp.equalsIgnoreCase("quit")) {
                break;
                }
                else {
                    System.out.println("That made no sense, please try again.");   
                }
            }
            else if (parts.length == 2) {
                boolean isInRoom = false;
                Creature c = assignedRoom.creatures.get(parts[0]);
                    if (parts[0].equalsIgnoreCase(c.creatureName)){
                        isInRoom = true;
                        if (parts[1].equalsIgnoreCase("clean")) {
                            makeClean();
                            int roomRespectChange = assignedRoom.stateChange();
                            int creatureRespectChange = c.react();
                            respect = respect + creatureRespectChange + roomRespectChange;
                            System.out.println("Your respect is now " +respect + ".");
                        }
                        else if (parts[1].equalsIgnoreCase("dirty")) {
                            makeDirty();
                            int roomRespectChange = assignedRoom.stateChange();
                            int creatureRespectChange = c.react();
                            respect = respect + creatureRespectChange + roomRespectChange;
                            System.out.println("Your respect is now " +respect + ".");
                            
                        }
                        else if (parts[1].equalsIgnoreCase("north")) {
                            c.leaveRoom("north");
                        }
                        else if (parts[1].equalsIgnoreCase("east")) {
                            c.leaveRoom("east");
                        }
                        else if (parts[1].equalsIgnoreCase("west")) {
                            c.leaveRoom("west");
                        }
                        else if (parts[1].equalsIgnoreCase("south")) {
                            c.leaveRoom("south");
            
                        }
                    }
                } 
            
         } while (!cmdInp.equalsIgnoreCase("quit"));
    }

    public void helpCommand(){
        //prints out all available commands to the output stream
        System.out.println("Here is a list of action you can execute:");
        System.out.println("look - Shows the complete information of the room you are in, as well as the creatures also in the room");
        System.out.println("clean - changes the state of the room to be more clean(note: some creatures do not like this)");
        System.out.println("dirty - changes the state of the room to be more dirty(note: some creatures do not like this)");
        System.out.println("north, south, east, west - leave the room you are in currently and go to an adjacement room, as long as the room is not fully occupied");
        System.out.println("quit - end the game");
    }

    public void lookAround(){
        //allows PC to look around room and see room info and animals inside
        System.out.println(assignedRoom.toString());
        
    }

    public int react(){
        //when the state of the room changes, the PC is notified and can act accordingly.
        return 0;
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
        System.out.println("The room is currently " + assignedRoom.getStateOfRoom());
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

    public void changeRespect(int roomRespectChange, int creatureRespectChange){
        System.out.println(roomRespectChange);
        System.out.println(creatureRespectChange);
        respect = respect + (roomRespectChange) + (creatureRespectChange);
        if (respect == 0) {
            System.out.println("You lose! Shame on you!");
            System.exit(0);
        }
        else if (respect == 80) {
            System.out.println("You win! Congratulations!");
            System.exit(0);
        }
    }
    
}