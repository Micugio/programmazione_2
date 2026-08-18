package lecture13.exceptions;
public class InventoryFullException extends CraftingException {
    public InventoryFullException(String msg) {
        super(msg);
    }
}