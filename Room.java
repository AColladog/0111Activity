import java.util.HashMap;
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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> location;;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        location = new HashMap<>();

    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param northeast The northeast exit.
     * @param southwest The southwest exit.
     * @param southeast The southeast exit.
     * @param northwest The northwest exit.
     * public void setExits(Room north, Room east, Room south, Room west, Room northeast, Room southwest, Room southeast, Room northwest) 
    {
    if(north != null)
    location.put("northExit", north);
    if(east != null)
    location.put("eastExit", east);
    if(south != null)
    location.put("southExit", south);
    if(west != null)
    location.put("westExit", west);
    if(northeast != null)
    location.put("northeastExit", northeast);
    if(southwest != null)
    location.put("southwestExit", southwest);
    if(southeast != null)
    location.put("southeastExit", southeast);
    if(northwest != null)
    location.put("northwestExit", northwest);
    }
     */

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){
        location.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Toma como parámetro una cadena que representa una dirección.
     * @return el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String direccion){
        Room out = null;
        for(Room a : location.values()){
            if(direccion.equals(a.getDescription()))
            {
                out = a;
            }
        }
        return out;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String localizacion = "Exits: ";
        for(Room a : location.values()){
            localizacion = localizacion + a.getDescription() + "  ";
        } 
        return localizacion; 
    }
}
