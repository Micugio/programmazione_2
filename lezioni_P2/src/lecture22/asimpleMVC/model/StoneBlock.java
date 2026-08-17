package lecture22.asimpleMVC.model;

public class StoneBlock {
    private int qty;
    private String name;

    public StoneBlock(int a, String n){
        this.qty = a;
        this.setName(n);
    }

    //@ Ha piu` senso usare getter e setter invece che fare un attributo pubblico
    //@ Il client (UI) potrebbe mandare info non giuste e noi vogliamo per. es. metter tutti i nomi capitalised
    public int getQty() { return qty; }
    public String getName() { return name; }
    public void setName(String n) { this.name = n.toUpperCase(); }

    public void use() { this.qty--; }
}
