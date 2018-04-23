import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class App11 {

    public static void main(String[] args) {
        try {
            Method testMethod = Person.class.getDeclaredMethod("test", String.class, String.class);
            Parameter[] parameters = testMethod.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(parameters[i].getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}

class Person{

    public void test(String param1,String param2) throws Exception {
    }
}
