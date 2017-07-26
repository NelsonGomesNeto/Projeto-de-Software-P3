package herenca;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        Department department = new Department() {
            @Override
            public void addDepartment() {
                super.addDepartment();
            }
        };

        Student student = new Student("Nelson","1234");
        Person person = new Person();
        Person studentPerson = new Student();

        List<Person> listPerson = new ArrayList<Person>();
        listPerson.add(new Student());
        listPerson.add(new Faculty());
        listPerson.add(new Admin());

        for (Person p : listPerson) {

            p.addPerson("");
        }

        /*
        System.out.println(student);

        student.addPerson("Lucas");
        person.addPerson("");
        studentPerson.addPerson(""); // Doesn't have the "addStudent" functions, because Polymorphism only works with Override!
        // If you override, the function can go up to (superClass - subClass) instantiations
        // Polymorphism == multiple forms! (Muitas formas!)

        // Not recomended! Cast is dangerous and ugly!
        ((Student)studentPerson).addStudent("");
        // (Cast) <-- Onyl allowed between (subClass) (superClass - subClass), if it where (subClass2) it wouldn't cast!
        if (studentPerson instanceof Student) {
            Student student1 = (Student) studentPerson;
            student.addStudent("1234");
        } else {
            person.addPerson("Leo");
        }

        //Person person1 = new Person();
        //Student s = (Student) person1; // This isn't allowed, you will be able to compile, but not to execute!
        //System.out.println(s.getName());
        */
    }
}

