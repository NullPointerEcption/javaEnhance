package d15BuilderPattern.builder;

public class App {
    public static void main(String[] args) {
        UserBuilder userBuilder = new UserBuilder("wang", "yf");
        User user = userBuilder.age(23).build();
        System.out.println(user);
    }
}
