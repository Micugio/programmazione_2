package lecture22.asimpleMVC.model;
public class StoneBlock {
    private int qty;
    private String name;
    public StoneBlock(int a, String n){
        this.qty = a;
        this.setName(n);
    }
    public int getQty() { return qty; }
    public String getName() { return name; }
    public void setName(String n) { this.name = n.toUpperCase(); }
    public void use() { this.qty--; }
}