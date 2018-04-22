package d13SingletonPattern;

public enum SingleTonEnum {
    INSTANCE1;

    public void myMethod() {
        System.out.println("i am a function in a singleton with enum type.");
    }
}

/**
 * 使用静态内部类的方式
 */
class SingleInnerClass {

    static {
        System.out.println("i am SingleInnerClass...");
    }

    private SingleInnerClass() {
    }

    private static class SingleInnerHolderClass {
        static {
            System.out.println("i am SingleInnerHolderClass...");
        }

        private static SingleInnerClass innerClass = new SingleInnerClass();
    }

    public static SingleInnerClass getSingleTon() {
        return SingleInnerHolderClass.innerClass;
    }
}

class App {
    public static void main(String[] args) {
        //SingleTonEnum.INSTANCE1.myMethod();
        try {
//            Class.forName("d13SingletonPattern.SingleInnerClass");
            SingleInnerClass.getSingleTon();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}