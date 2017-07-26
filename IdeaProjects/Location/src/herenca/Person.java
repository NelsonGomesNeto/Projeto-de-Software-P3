package herenca;

/**
 * Created by alunoic on 24/07/17.
 */
public class Person {

    private String name;

    public Person() {

    }

    public Person(String name) {
        super();

        this.name = name;
        System.out.println("Name: " + name);
    }

    public void addPerson(String person) {

        System.out.println("Sysout Person");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
