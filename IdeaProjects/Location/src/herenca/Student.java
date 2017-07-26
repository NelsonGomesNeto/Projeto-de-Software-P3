package herenca;

/**
 * Created by alunoic on 24/07/17.
 */
public class Student extends Person {

    private String mat;

    // this(parameters) <-- calls the own class constructor!
    // super(parameters) <-- calls the super class constructor!

    public Student() {

    }

    public Student(String name, String mat) {

        super(name);
        this.mat = mat;
        System.out.println("Mat: " + this.mat);

    }

    @Override
    public void addPerson(String person) {
        super.addPerson(person);

        System.out.println("Sysout Student");
    }

    public void addStudent(String mat) {
        System.out.println("Sysout Student mat");
    }

    public void study() {

    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {

        return super.toString() + " Mat: " + this.mat;
    }
}
