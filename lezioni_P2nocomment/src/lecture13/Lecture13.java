package lecture13;
import lecture13.exceptions.*;
import lecture13.tables.CraftingTable;
import lecture13.tables.EnchantingTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Lecture13 {
    public static void main(String[] args) {
        System.out.println("---------------- Eccezioni ----------------");
        exceptionsExample();
        exceptionsAndConstructors();
        deepenStackExample();
        System.out.println("---------------- Runtime exceptions ----------------");
        runtimeExceptionsExample();
        System.out.println("---------------- Eccezioni e subtyping ----------------");
        exceptionHierarchyExample();
        System.out.println("---------------- Resources ----------------");
        tryWithResourcesExample();
    }
    private static void exceptionsExample() {
        CraftingTable table1 = new CraftingTable();
        CraftingTable table2 = new CraftingTable();
        int result = table1.craft_legacy("Sword");
        if (result == -1) { System.out.println("Inventory Full"); }
        else if (result == -2) { System.out.println("No Materials"); }
        else { System.out.println("Crafted!"); }
        try {
            throw new CraftingException("generic exc");
        }
        catch (RecipeMissingException e) {
            System.out.println("Unknown Recipe: " + e.getMessage());
            System.out.println(">> Opening Recipe Book...");
        }
        catch (InventoryFullException e) {
            System.out.println("Inventory Full: " + e.getMessage());
            System.out.println(">> Please clear a slot.");
        }
        catch (Exception e) {
            System.out.println("Critical Error: " + e.getMessage());
        }
        try {
            table1.craftGeneral("null");
        } catch (CraftingException e) {}
    }
    private static void exceptionsAndConstructors(){
        try {
            CraftingTable c = new CraftingTable(3);
        } catch (CraftingException e) {}
    }
    private static void deepenStackExample(){
        try {
            mid();
        }catch (Exception e){}
    }
    private static void mid() throws IOException{
        last();
    }
    private static void last() throws IOException{
        throw new IOException();
    }
    private static void runtimeExceptionsExample() {
        try { getInventorySlot(99); } catch (IndexOutOfBoundsException e) {
            System.out.println("How did you figure out what exception to catch?");
        }
        try {
            int index = 0;
            if (index < 9) {
                getInventorySlot(index);
            }
        }catch (IndexOutOfBoundsException e){}
        try {
            riskyMethod();
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointer, but we really should have just checked if the object was null first!");
        }
    }
    private static void getInventorySlot(int i) {
        throw new IndexOutOfBoundsException();
    }
    private static void riskyMethod() {
        throw new NullPointerException();
    }
    private static void exceptionHierarchyExample() {
        EnchantingTable enchantingTable = new EnchantingTable();
        try {
            enchantingTable.craftGeneral("Boots");
            enchantingTable.craftAdvanced("Boots");
        }catch (Exception e){}
        CraftingTable ct = new EnchantingTable();
        try {
            ct.craftAdvanced("Boots");
        }catch (Exception e){}
    }
    private static void tryWithResourcesExample(){
        try (FileReader fr = new FileReader("asd");
             BufferedReader br = new BufferedReader(fr)) {
            br.readLine();
        } catch (IOException e) {}
        catch (Exception e) {}
    }
}