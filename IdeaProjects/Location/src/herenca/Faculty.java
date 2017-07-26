package herenca;

/**
 * Created by alunoic on 24/07/17.
 */
public class Faculty extends Person {

    public Faculty() {
        super();
    }

    public Faculty(String name) {
        super(name);
    }

    @Override
    public void addPerson(String person) {
        super.addPerson(person);

        System.out.println("Sysout Faculty");
    }
}
