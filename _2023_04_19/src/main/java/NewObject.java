import java.lang.reflect.Constructor;

public class NewObject {
    public static void main(String[] args) throws Exception {
//        Class<?> personClass = Class.forName("DeepClone.Person");
//        Constructor<?> constructor = personClass.getConstructor();
//        DeepClone.Person person = (DeepClone.Person) constructor.newInstance("Tom",11);
        Person1 person1 = new Person1("Tom", 20);
        Person1 person2 = (Person1) person1.clone();
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person1.equals(person2));

    }
}

class Person{
    private String name;
    private int age;
    public Person(String name , int age){
        this.name = name;
        this.age = age;
    }
}

class Person1 implements Cloneable{
    private String name;
    private int age;
    public Person1(String name , int age){
        this.name = name;
        this.age = age;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
