package DSAASSIGNEMT4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    int empId;
    String EmployeeName;
    String address;

    Employee(int empId, String EmployeeName, String address) {
        this.address = address;
        this.empId = empId;
        this.EmployeeName = EmployeeName;
    }

    String getEmployyeInformation() {
        return empId + " " + EmployeeName + " " + address;
    }

}

public class EmployeeInfomation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Employee> checkEmployee = new HashMap<>();

        System.out.println("enter number of employee ");
        int empnumber = sc.nextInt();
        while (empnumber > 0) {
            System.out.println("enter the employee Id ");
            int empId = sc.nextInt();
            System.out.println("enter the employee name ");
            String empName = sc.next();
           // sc.nextLine();
            System.out.println("enter the employee Address");

            String empAddress = sc.next();

            if (!checkEmployee.containsKey(empId)) {
                checkEmployee.put(empId, new Employee(empId, empName, empAddress));
                empnumber--;
            } else {
                System.out.println("Employee with Id -> " + empId + "  already exixts : skip...");
            }
        }

        ArrayList<Employee> employeeList = new ArrayList<>(checkEmployee.values());
        Collections.sort(employeeList, Comparator.comparing(c -> c.empId));
        System.out.println("Sort according employee Id : ");
        for (Employee e : employeeList) {
            System.out.println(e.getEmployyeInformation());
        }

        Collections.sort(employeeList, Comparator.comparing(c -> c.EmployeeName));
        System.out.println("Sort according employee name :");
        for (Employee e : employeeList) {
            System.out.println(e.getEmployyeInformation());
        }

    }
}
