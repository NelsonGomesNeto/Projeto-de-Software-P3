package herenca;

/**
 * Created by alunoic on 24/07/17.
 */
public abstract class Person {

    private String name;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printSomething() {
        System.out.println("Person");
    }

    public abstract void display();

    @Override
    public String toString() {
        return super.toString();
    }
}
