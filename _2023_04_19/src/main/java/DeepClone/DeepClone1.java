package DeepClone;

class Person implements Cloneable {
    private String name;
    private int age;
    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = (Address) address.clone();
        return cloned;
    }
}

class Address implements Cloneable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DeepClone1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Beijing");
        Person person1 = new Person("Tom", 20, address);
        Person person2 = (Person) person1.clone();
        System.out.println(person2 == person1);
    }
}
