package lecture10.bindings;

public class Block {

    private String registryName;

    public Block(String name) {
        this.registryName = "minecraft:" + name;
    }

    public void onBreak() {
        System.out.println("Generic Block breaks into pieces.");
    }

    //@ I metodi `getRegistryName()`, `getStaticRegistry()` e `getRegistryCalculations()` sono tutti seguiti tramite static binding perche` definiti con le keyword dello static binding: `final`, `static` e `private`
    public final void getRegistryName() {
        System.out.println("ID: " + this.getRegistryCalculations());
    }
    public static void getStaticRegistry(){
        System.out.println("Static registry value ...");
    }
    private String getRegistryCalculations(){
        return "R" + this.registryName;
    }
}