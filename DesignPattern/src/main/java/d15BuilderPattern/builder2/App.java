package d15BuilderPattern.builder2;

public class App {
    public static void main(String[] args) {
        User build = new User.UserBuilder("wang", "yufei").age(19).build();
        System.out.println(build);
    }
}
