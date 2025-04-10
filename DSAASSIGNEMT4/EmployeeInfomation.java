package DSAASSIGNEMT4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

class Employee {
    private int empId;
    private String name;
    private String address;

    static private ArrayList<Employee> empList = new ArrayList<>();

    Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    void neturalSort() {
        empList.sort(Comparator.comparingInt(e -> e.empId));
    }

    void sortingByName() {
        empList.sort(Comparator.comparing(e -> e.name));
    }

    boolean CheckEmployee(int id) {
        for (Employee emp : empList) {
            if (id == emp.empId) {
                return false;
            }
        }
        return true;
    }

    void addEmployee(Employee emp) {
        empList.add(emp);
    }

    int getId() {
        return empId;
    }

    String getName() {
        return name;
    }

    String getAddress() {
        return address;
    }

    void dispaly() {
        for (Employee emp : empList) {
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getAddress());
        }
    }
}

public class EmployeeInfomation {
    public static void main(String[] args) {
        Employee emp;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("enter the employee id ");
            int empId = sc.nextInt();
            System.out.println("enter the employee name ");
            String empName = sc.next();
            System.out.println("enter the employee address");
            String empAddress = sc.next();
            emp = new Employee(empId, empName, empAddress);
            if (emp.CheckEmployee(emp.getId())) {
                emp.addEmployee(emp);
            } else {
                System.out.println("duplicate employee id , skipp...");
            }

            System.out.println("Press -1 exit");
            int press = sc.nextInt();
            if (press == -1) {
                break;
            }

        }

        System.out.println("sorting according employee ID ");
        emp.neturalSort();
        emp.dispaly();

        System.out.println("sorting accoding employee name ");
        emp.sortingByName();
        emp.dispaly();
    }

}