package d5StragetyPattern;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.*;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App3 {

    public static void main(String[] args) {
        Person[] persons = new Person[5];
        persons[0] = new Person(1, "yzk", 18, 180);
        persons[1] = new Person(8, "秦岚", 25, 130);
        persons[2] = new Person(6, "贾玲", 22, 160);
        persons[3] = new Person(7, "冯提莫", 21, 150);
        persons[4] = new Person(3, "金莲", 38, 155);

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getHeight() - o2.getHeight();
            }
        });

        //按身高排序
//        Arrays.sort(persons,(p1,p2)->p1.getHeight()-p2.getHeight());
//        Arrays.sort(persons,comparing(Person::getHeight));

        //按年龄排序
//        Arrays.sort(persons,comparing(Person::getAge));

        //按姓名排序
        Arrays.sort(persons, comparing(Person::getName));

        Arrays.stream(persons).forEach(System.out::println);
    }

}

class Person {
    private int id;
    private String name;
    private int age;
    private int height;


    public Person(int id, String name, int age, int height) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + ", height=" + height + "]";
    }
}
