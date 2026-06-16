package lecture13.exceptions;

//@ Questa e` la classe base per la nostra logica
public class CraftingException extends Exception {
    public CraftingException(String msg) {
        super(msg);
    }
}
