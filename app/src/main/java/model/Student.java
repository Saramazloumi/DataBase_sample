package model;

public class Student extends Person {

    private int studentId;
    private String program;

    public Student(String name, int age, int studentId, String program) {
        super(name, age);
        this.studentId = studentId;
        this.program = program;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
