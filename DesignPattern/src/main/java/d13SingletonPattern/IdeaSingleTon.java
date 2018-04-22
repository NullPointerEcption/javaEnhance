package d13SingletonPattern;

public class IdeaSingleTon {
    private static IdeaSingleTon ourInstance = new IdeaSingleTon();

    public static IdeaSingleTon getInstance() {
        return ourInstance;
    }

    private IdeaSingleTon() {
    }
}
