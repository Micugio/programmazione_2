package lecture13.exceptions;

//@ Qui estendiamo CraftingException, non Exception!
public class RecipeMissingException extends CraftingException {
    public RecipeMissingException(String msg) {
        super(msg);
    }
}
