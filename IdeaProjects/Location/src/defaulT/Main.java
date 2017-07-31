package defaulT;

import herenca.Faculty;
import herenca.Person;
import herenca.Student;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        Person person = new Person();

        Student student = new Student();

        Person p = new Student();

        Person f = new Faculty();

        // But can't do: Student/Faculty l = new Person()
        // Because not every Student/Faculty is a Person!

        // p can't access specific variables and methods from Student

        student.setName("Anderson");

        System.out.printf(student.getName());
    }
}

