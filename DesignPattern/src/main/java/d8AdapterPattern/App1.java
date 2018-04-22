package d8AdapterPattern;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class App1 {

    @Test
    public void test() throws Exception {
        Student s=new Student();
        s.setId(1);
        s.setFirstName("wang");
        s.setLastName("yufei");
        Calendar birthDay  = Calendar.getInstance();
        birthDay.set(1995,6,17);

        s.setBirthDate(birthDay.getTime());
        System.out.println(toPerson(s));

    }

    private String printPerson(Person p){return p.toString();}

    private Person toPerson(Student student){
        Person p=new Person();
        p.setId(student.getId());
        p.setName(student.getFirstName().concat(student.getLastName()));

        //计算年龄
        Calendar cBirthday=Calendar.getInstance();
        cBirthday.setTime(student.getBirthDate());
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));
        int age= now.get(Calendar.YEAR)-cBirthday.get(Calendar.YEAR);
        p.setAge(age);

        return p;
    }
}
class Person{

    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Student{
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}