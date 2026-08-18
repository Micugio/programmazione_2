package lecture14.examples;
public class Spawner<T> {
    private T entity;
    public void setEntity(T e) { this.entity = e; }
    public T spawn() { return entity; }
}