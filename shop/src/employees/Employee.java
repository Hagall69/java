package employees;

public class Employee{

    private String name;
    private String position;
    private int salary;
    private int id;

    public Employee(int id, String name, String position, int salary){
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void copyEmployee(Employee employee){
        this.id = employee.id;
        this.name = employee.name;
        this.position = employee.position;
        this.salary = employee.salary;
    }

    public Employee(){}

    // Выводим информацию по сотруднику
    @Override
    public String toString() {
        return String.format("ID: %10s | Имя: %10s | Должность: %10s | Зарплата: %10s",
                this.id, this.name, this.position, this.salary);
    }

//    int getID(){
//        return id;
//    }
//
//    String getName(){
//        return name;
//    }
//
//    String getPosition(){
//        return position;
//    }
//
//    int getSalary(){
//        return salary;
//    }
//
    public void setName(String name){
        this.name = name;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }
}
