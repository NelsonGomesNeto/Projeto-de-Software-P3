package Herança;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        Student student = new Student();

        Person p = new Student();

        Person f = new Faculty();

        // But can't do: Student/Faculty l = new Person()
        // Because not every Student/Faculty is as person!

        student.setName("Anderson");

        System.out.printf(student.getName());
    }
}

