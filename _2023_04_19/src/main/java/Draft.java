public class Draft {
    //静态

    public static void main(String args[ ]){
        Dog dog = new Dog("meat");
        dog.print();
        dog.action("running");

    }


}

class Animal{
    String food = "";
    String likes = "unknown";
    Animal(String food){
        this.food = food;
        System.out.println("1"+food);
    }
    void action(String doing){
        System.out.println(this + "" +doing);
    }
}

class Dog extends Animal{
    String food = "fish";
    Dog(String food) {
        super(food);
    }
    void print(){
        System.out.println("2"+food);
        System.out.println("3"+super.food);
        System.out.println(likes);
    }


}
