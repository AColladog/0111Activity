import java.util.Scanner;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room[] habitaciones = new Room[10];
        habitaciones[0] =  new Room("conserjeria"); //outside
        habitaciones[1] =  new Room("comisaria");
        habitaciones[2] =  new Room("profesores");
        habitaciones[3] =  new Room("fp2");
        habitaciones[4] =  new Room("fp1");
        habitaciones[5] =  new Room("primero");
        habitaciones[6] =  new Room("segundo");
        habitaciones[7] =  new Room("bachiller1");
        habitaciones[8] =  new Room("bachiller2");
        habitaciones[9] =  new Room("patio");

        Scanner teclado = new Scanner(System.in);

        for(int i = 0; i<habitaciones.length;i++){
            String respuesta = "";

            do{
                String salidas = "Habitaciones disponibles: ";
                //Posibles habitaciones
                for(int k = 0; k<habitaciones.length;k++){                
                    if(!(habitaciones[i].getDescription().equals(habitaciones[k].getDescription()))){
                        salidas = salidas + habitaciones[k].getDescription() + " ";
                    }
                }
                System.out.println("** " + salidas);                
                System.out.println("Deme las salidas para " + habitaciones[i].getDescription()); 
                String salida = teclado.nextLine();
                Boolean existe = false;
                Room roomExit = null;
                //Buscamos la habitacion introducida
                for(int j = 0; j<habitaciones.length;j++){                
                    if(habitaciones[j].getDescription().equals(salida)){
                        existe = true;
                        roomExit = habitaciones[j];
                    }
                }    
                
                if (!existe){
                    System.out.println("La salida introducida no existe");
                }else{
                    habitaciones[i].setExit(salida, roomExit);
                }    
                System.out.println("¿Quiere introducir otra salida? (si/no)");
                respuesta = teclado.nextLine();
            }while(respuesta.equals("si"));
        }        
        
        currentRoom = habitaciones[1]; // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();        
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        Room nextRoom = currentRoom.getExit(command.getSecondWord());

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }

    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo(){
        System.out.println("You are " + currentRoom.getDescription());

        System.out.println(currentRoom.getExitString());
    }
}
