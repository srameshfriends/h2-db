package srimalar.examples.webflux.controls;

import org.springframework.stereotype.Service;
import srimalar.examples.webflux.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
public class StudentService {
    private static final ConcurrentHashMap<String, Student> studentMap = new ConcurrentHashMap<>();

    public static Map<String, Student> getStudentMap() {
        if(studentMap.isEmpty()) {
            List<Student> list = new ArrayList<>();
            list.add(Student.create("1", "Ramesh", 10));
            list.add(Student.create("2", "Rajeswari", 9));
            list.add(Student.create("3", "Abinesh", 8));
            list.add(Student.create("4", "Kashvika", 7));
            list.add(Student.create("5", "Bala", 6));
            list.add(Student.create("6", "Manikandan", 5));
            list.add(Student.create("7", "Velayutham", 4));
            list.add(Student.create("8", "Panner", 3));
            list.add(Student.create("9", "Kalayani", 2));
            list.add(Student.create("10", "Malliga", 1));
            list.forEach(new Consumer<Student>() {
                @Override
                public void accept(Student student) {
                    studentMap.put(student.getId(), student);
                }
            });
        }
        return studentMap;
    }

    public Student findStudentById(String studentId) {
        if(!getStudentMap().containsKey(studentId)) {
            throw new NullPointerException("Student not found.");
        }
        return getStudentMap().get(studentId);
    }

    public List<Student> getStudentList() {
        return getStudentMap().values().stream().toList();
    }

    public Student addNewStudent(Student student) {
        if(student != null && student.getName() != null && student.getId() != null) {
            getStudentMap().put(student.getId(), student);
        } else {
            throw   new NullPointerException("Student not valid to save.");
        }
        return student;
    }

    public Student updateStudent(String id, Student student) {
        if(student == null || student.getName() == null || id == null || !getStudentMap().containsKey(id)) {
            throw new NullPointerException("Student not found to update.");
        }
        getStudentMap().put(id, student);
        return student;
    }


    public Student deleteStudentById(String id) {
        if(getStudentMap().containsKey(id)) {
            return getStudentMap().remove(id);
        }
        return null;
    }
}
