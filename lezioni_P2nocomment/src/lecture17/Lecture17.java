package lecture17;
import lecture17.eq.*;
import lecture17.sorting.*;
import java.util.*;
public class Lecture17 {
    public static void main(String[] args) {
        System.out.println("---------------- The Collections Framework ----------------");
        threeExamples();
        implementationsExample();
        programmingToInterfacesExample();
        System.out.println("---------------- Equality ----------------");
        idVSeqExample();
        hashExample();
        System.out.println("---------------- Sorting ----------------");
        comparableExample();
        comparatorExample();
    }
    private static void threeExamples() {
        List<String> hotbar = new ArrayList<>();
        hotbar.add("Sword");
        hotbar.add("Dirt");
        hotbar.add("Dirt");
        System.out.println("List: " + hotbar);
        String second = hotbar.get(1);
        Set<String> biomes = new HashSet<>();
        biomes.add("Forest");
        biomes.add("Desert");
        System.out.println("Set: " + biomes);
        biomes.add("Forest");
        System.out.println("Set: " + biomes);
        Map<String, Integer> durabilityRegistry = new HashMap<>();
        durabilityRegistry.put("Wood_Pickaxe", 59);
        durabilityRegistry.put("Iron_Pickaxe", 250);
        System.out.println("Map Value: " + durabilityRegistry.get("Iron_Pickaxe"));
    }
    private static void implementationsExample() {
        List<String> fastRead  = new ArrayList<>();
        List<String> fastWrite = new LinkedList<>();
        Set<String> hs = new HashSet<>();
        Set<String> ts = new TreeSet<>();
        Map<String, Integer> registry= new HashMap<>();
        Map<String, Integer> reg_sort= new TreeMap<>();
        Queue<String> slowQueue = new LinkedList<>();
        Queue<String> commandQueue = new ArrayDeque<>();
        commandQueue.add("/gamemode creative");
        commandQueue.add("/kill @e[type=bat]");
        commandQueue.add("/say Hello");
        while (!commandQueue.isEmpty()) {
            String cmd = commandQueue.poll();
            System.out.println(" Executing: " + cmd);
        }
    }
    private static void programmingToInterfacesExample() {
        ArrayList<String> myItems = new ArrayList<>();
        myItems.add("Apple");
        printInventory(myItems);
        countItems(myItems);
    }
    public static void printBad(ArrayList<String> items) {
        for (String s : items) System.out.println(s);
    }
    public static void printInventory(List<String> items) {
        System.out.println("Item 1: " + items.get(0));
    }
    public static void countItems(Collection<String> items) {
        System.out.println("Count: " + items.size());
    }
    private static void idVSeqExample() {
        BlockPos posA = new BlockPos(10, 64, 10);
        BlockPos posB = new BlockPos(10, 64, 10);
        System.out.println("posA == posB: " + (posA == posB));
        System.out.println("posA.equals(posB): " + posA.equals(posB));
        BlockPosEq p1 = new BlockPosEq(10, 64, 10);
        BlockPosEq p2 = new BlockPosEq(10, 64, 10);
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println(p1 instanceof Object);
    }
    private static void hashExample() {
        HashSet<BlockPosEq> brokenSet = new HashSet<>();
        BlockPosEq p1 = new BlockPosEq(10, 64, 10);
        BlockPosEq p2 = new BlockPosEq(10, 64, 10);
        brokenSet.add(p1);
        brokenSet.add(p2);
        System.out.println("Set Size: " + brokenSet.size());
        HashSet<BlockPosEqHc> safeSet = new HashSet<>();
        BlockPosEqHc sp1 = new BlockPosEqHc(10, 64, 10);
        BlockPosEqHc sp2 = new BlockPosEqHc(10, 64, 10);
        safeSet.add(sp1);
        safeSet.add(sp2);
        System.out.println("Set Size: " + safeSet.size());
        BlockPosEqHc p3 = new BlockPosEqHc(10, 64, 10);
        boolean found = safeSet.contains(p3);
        System.out.println("Contains p3? " + found);
    }
    private static void comparableExample() {
        List<LootItem> inventory = new ArrayList<>();
        inventory.add(new LootItem("Stick", 1));
        inventory.add(new LootItem("Diamond", 100));
        inventory.add(new LootItem("Iron Ingot", 50));
        Collections.sort(inventory);
        for (LootItem item : inventory) {
            System.out.println(item);
        }
    }
    private static void comparatorExample() {
        List<LootItem> inventory = new ArrayList<>();
        inventory.add(new LootItem("Stick", 1));
        inventory.add(new LootItem("Diamond", 100));
        inventory.add(new LootItem("Coal", 1));
        Comparator<LootItem> nameSorter = new NameSorter();
        inventory.sort(nameSorter);
        for (LootItem item : inventory) {
            System.out.println(item);
        }
        List<LootItem> inv = new ArrayList<>();
        inv.add(new LootItem("Stick", 1));
        inv.add(new LootItem("Coal", 1));
        inv.add(new LootItem("Diamond", 100));
        inv.sort(
                Comparator.comparingInt(LootItem::getValue)
                        .reversed()
                        .thenComparing(LootItem::getName)
        );
        for (LootItem item : inv) {
            System.out.println(item);
        }
    }
}