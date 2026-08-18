package lecture05;
public class DiamondProblem {
    class GameObject {
        void update() {
            System.out.println("Generic Object Update");
        }
    }
    class Block extends GameObject {
        @Override
        void update() {
            System.out.println(">> Calculating Physics (Gravity, Collision)");
        }
    }
    class LightSource extends GameObject {
        @Override
        void update() {
            System.out.println(">> Calculating Light Levels (Raytracing)");
        }
    }
}