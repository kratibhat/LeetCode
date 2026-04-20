package guessOutput;
class Parent {
    public void display() {
        System.out.println("Parent class method");
    }
}
class Child extends Parent {
    @Override
    public void display() {
        System.out.println("Child class method");
    }
}
public class InheritancePredictOutput {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent parentChild = new Child();

        parent.display(); // Output: Parent class method
        child.display();  // Output: Child class method
        parentChild.display(); // Output: Child class method
    }
}
