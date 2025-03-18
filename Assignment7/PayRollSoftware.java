package Assignment7;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    protected int employeeId;
    protected String employeeName;
    protected double salary;
    protected String depName ; 
    
    Employee(int employeeId, String employeeName, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        
       
    }

    abstract double getBasicSalary();

    abstract double getBonus();

    abstract double getCompensation();

    int getId() {
        return employeeId;
    }

    String getName() {
        return employeeName;
    }
    String getDepName(){
        return depName ; 
   }
}

class Developer extends Employee {
    protected double bonus;
    Developer(int id, String name, double salary) {
        super(id, name, salary);
        this.bonus = (salary*10)/100 ; 
        this.depName = "Technical" ; 
    }

    double getBasicSalary() {
        return salary-bonus;
    }

    double getBonus() {
        return bonus;
    }

    double getCompensation() {
        return salary ; 
        }

}

class SaleManagar extends Employee {
        private double bonus;
    SaleManagar(int id, String name, double salaray) {
        super(id, name, salaray);
        this.bonus  = (salary*10)/100 ;
        this.depName = "Marketing"; 
    }

    double getBasicSalary() {
        return salary-bonus;
    }

    double getBonus() {
        return bonus;
    }

    double getCompensation() {
        return salary;
    }

    
}

class Department {
    private ArrayList<Employee> employee = new ArrayList<>();
    

    boolean join(Employee emp) {
        return employee.add(emp);
    }

    boolean relieve(Employee emp) {
        return employee.remove(emp);
    }

    ArrayList<Employee> getEmployees() {
        return employee;
    }

}

class Organization {

    private ArrayList<Department> organization = new ArrayList<>();

    public boolean addDepartment(Department department) {

        return organization.add(department);
    }

    ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> emp = new ArrayList<>();
        for (Department dept : organization) {
            emp.addAll(dept.getEmployees());
        }

        return emp;
    }

   
}

class PayRoll {
    double tax(double salary) {
        if (salary >= 50000 && salary <= 99999) {
            salary -= (salary * 10) / 100;
        } else if (salary >= 100000 && salary <= 250000) {
            salary -= (salary * 20) / 100;
        } else if (salary > 250000) {
            salary -= (salary * 25) / 100;
        }

        return salary;
    }

    void printSlip(Organization org) {

        System.out.println("----Pay Slip ---- ");
        for (Employee emp : org.getAllEmployees()) {
            System.out.println("Employee  id    : " + emp.getId());
            System.out.println("Employee name : " + emp.getName());
            System.out.println("Employee department : "+emp.getDepName());
            System.out.println("Employee base  salary : $ " + emp.getBasicSalary());
            System.out.println("Employee bonus : $ " + emp.getBonus());
            System.out.println("Employee Compensation : $" + emp.getCompensation());
            double netSalary = tax(emp.getCompensation());
            System.out.println("Net Salary after deducted tax : " + netSalary);
            System.out.println("----------------------");
        }
    }
}

public class PayRollSoftware {
    public static void main(String[] args) {

         Organization organization = new Organization() ;
         PayRoll payRoll = new PayRoll();
         Scanner sc = new Scanner(System.in) ;

         
         while(true){
             System.out.println("Enter the employee details : ");
             System.out.println("Enter the employee name ");
             String name  = sc.next() ; 
             System.out.println("Enter the employee id");
             int employeeId = sc.nextInt() ; 
             System.out.println("Enter the employee CTC  ");
             double salary = sc.nextDouble() ; 
             System.out.println("1- Technical department");
             System.out.println("2- Marketing department ");
             System.out.println("choose the department");
             int choose = sc.nextInt() ;

             switch (choose) {
                case 1:   Developer employee = new Developer(employeeId, name, salary);
                            Department tech = new Department();
                            tech.join(employee) ; 
                            organization.addDepartment(tech);
                    break;
             
                case 2: SaleManagar saleManager = new SaleManagar(employeeId, name,salary);
                        Department nonTech = new Department();
                        nonTech.join(saleManager) ; 
                        organization.addDepartment(nonTech);
                    break ; 
             }
             System.out.println(" press any for add employee (-1 for exit)");
             int press = sc.nextInt(); 
             if(press==-1){
                 break ; 
             }
        }


        payRoll.printSlip(organization);

        

    }
}
