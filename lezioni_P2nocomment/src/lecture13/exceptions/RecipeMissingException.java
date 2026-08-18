package lecture13.exceptions;
public class RecipeMissingException extends CraftingException {
    public RecipeMissingException(String msg) {
        super(msg);
    }
}