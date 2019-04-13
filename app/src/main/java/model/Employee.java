package model;

public class Employee extends Person {

    private int employeeId;
    private String job;
    private double salary;

    public Employee(String name, int age, int employeeId, String job, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.job = job;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
