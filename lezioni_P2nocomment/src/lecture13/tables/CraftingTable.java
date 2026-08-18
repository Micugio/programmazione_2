package lecture13.tables;
import lecture10.bindings.Block;
import lecture13.exceptions.*;
public class CraftingTable {
    public Block craftAdvanced(String item) throws RecipeMissingException, InventoryFullException {
        if (item.equals("UnknownThing")) {
            throw new RecipeMissingException("What is a " + item + "?");
        }
        boolean hasSpace = false;
        if (!hasSpace) {
            throw new InventoryFullException("No empty slots available.");
        }
        return new Block("Boat");
    }
    public Block craftGeneral(String item) throws CraftingException{
        return this.craftAdvanced(item);
    }
    public int craft_legacy(String item) {
        if (item.equals("Bedrock")) return -1;
        return 0;
    }
    public CraftingTable(){}
    public CraftingTable(int n) throws CraftingException {
        throw new CraftingException("Inside a constructor");
    }
}