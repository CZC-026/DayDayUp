package DeepClone;

import java.io.*;

class Person1 implements Serializable {
    private String name;
    private int age;
    private Address address;

    public Person1(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person1 deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Person1) ois.readObject();
    }
}

class Address1 implements Serializable {
    private String city;

    public Address1(String city) {
        this.city = city;
    }
}

public class DeepClone2 {
    public static void main(String[] args) throws Exception {
        Address address = new Address("Beijing");
        Person1 person1 = new Person1("Tom", 20, address);
        Person1 person2 = person1.deepClone();
        System.out.println(person2 == person1);
    }
}
