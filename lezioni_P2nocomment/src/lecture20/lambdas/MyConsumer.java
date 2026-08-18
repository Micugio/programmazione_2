package lecture20.lambdas;
public class MyConsumer {
    public void consumeFI(MyFIInterface m){
        m.singlemethod().length();
        System.out.println(m.toString());
    }
    public void consumeNotFI(MyNonFIInterface m){
        m.m1();
        m.m2();
    }
}