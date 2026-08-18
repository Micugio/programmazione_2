package lecture11.exercise;
public class CherryBlock extends AbstractWoodBlock{
    @Override
    protected void dropLoot() {
        System.out.println("Cherry block");
    }
}