package herenca;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        //Person p1 = new Person();
        //p1.printSomething();
        Person p2 = new Student();
        p2.printSomething();

        //Person person = new Person();
        //person.setName("Nelson");

        //System.out.println(person.toString());

        Student student = new Student();

        //Person p = new Student();

        //Person f = new Faculty();

        // But can't do: Student/Faculty l = new Person()
        // Because not every Student/Faculty is a Person!

        // p can't access specific variables and methods from Student

        student.setName("Anderson");

        System.out.printf(student.getName());
    }
}

