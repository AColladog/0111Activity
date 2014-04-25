/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room northeastExit;
    private Room southwestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param west The northeast exit.
     * @param west The southwest exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room northeast, Room southwest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(northeast != null)
            northeastExit = northeast;
        if(southwest != null)
            southwestExit = southwest;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Toma como par�metro una cadena que representa una direcci�n.
     * @return el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String direccion){
        Room out = null;
        if(direccion.equals("north")){
            out = northExit;
        }
        if(direccion.equals("east")){
            out = eastExit;
        }
        if(direccion.equals("south")){
            out = southExit;
        }
        if(direccion.equals("west")){
            out = westExit;
        }
        if(direccion.equals("northeast")){
            out = northeastExit;
        }
        if(direccion.equals("southwest")){
            out = southwestExit;
        }
        return out;
    }

}
