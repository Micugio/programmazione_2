package lecture14.examples;
public class RawSpawner {
    private Object entity;
    public void setEntity(Object e) {
        this.entity = e;
    }
    public Object spawn() {
        return entity;
    }
}