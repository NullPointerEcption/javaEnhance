package d16PrototypePattern;

public class App1 {

    public static void main(String[] args) {
        Dog dog=new Dog("heishao",30);
        Person person=new Person("wyf",dog);

        try {
            Person clone = (Person) person.clone();
            System.out.println(person==clone);//false 因为新拷贝的对象
            System.out.println( clone.getDog()==person.getDog());//默认是true 因为clone方法默认是浅拷贝 想要实现深拷贝 得自己重写clone()
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浅拷贝
     * @param person
     * @return
     */
    public Person shadowCopy(Person person){
        Person p=new Person();
        p.setName(person.getName());
        p.setDog(person.getDog());
        return p;
    }

    /**
     * 深拷贝
     * @param person
     * @return
     */
    public Person deepyCopy(Person person){
        Person p=new Person();
        p.setName(person.getName());
        Dog dog=new Dog();
        dog.setHeight(person.getDog().getHeight());
        dog.setName(person.getDog().getName());
        p.setDog(dog);
        return p;
    }


}
class Dog implements Cloneable{
    private  String name;
    private Integer height;

    public Dog() {
    }

    public Dog(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Person implements  Cloneable{
    private String name;
    private Dog dog;

    public Person() {
    }

    public Person(String name, Dog dog) {
        this.name = name;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();//如果调用this.clone就会死循环了
        person.setDog((Dog) this.dog.clone());
        return person;
    }
}
