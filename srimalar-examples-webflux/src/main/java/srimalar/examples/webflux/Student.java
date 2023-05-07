package srimalar.examples.webflux;

public class Student {
    private String id;
    private String name;
    private int level;
    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static Student create(String id, String name, int level) {
        Student student = new Student();
        student.setActive(true);
        student.setLevel(level);
        student.setName(name);
        student.setId(id);
        return student;
    }
}
