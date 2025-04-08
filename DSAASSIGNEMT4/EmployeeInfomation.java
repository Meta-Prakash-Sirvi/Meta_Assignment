package DSAASSIGNEMT4 ; 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

class Employee{
    private int empId;
    private String name;
    private String address;

    Employee(int empId, String name, String address){
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    int getId(){
         return empId ; 
    }

    String getName(){
         return name ;
    }

    String getAddress(){
        return address ;
   }
}

class Operation{
    private ArrayList<Employee> empList;
    private HashSet<Integer> empIdSet;
    Operation(){
        empIdSet = new HashSet<>();
        empList = new ArrayList<>();
    }

    void neturalSort(){
        empList.sort(Comparator.comparingInt(e -> e.getId()));
    }

    void sortingByName(){
        empList.sort(Comparator.comparing(e -> e.getName()));
    }

    boolean CheckEmployee(int id){
        if(!empIdSet.contains(id)){
            return true ; 
        }
        return false;
    }
    
    void addEmployee(Employee emp){
         empList.add(emp) ; 
         empIdSet.add(emp.getId()) ; 
    }

    void dispaly(){
        for(Employee emp : empList){
            System.out.println(emp.getId()+" "+emp.getName()+" "+emp.getAddress());
        }
    }
}

public class EmployeeInfomation {
    public static void main(String[] args) {
        Operation operation = new Operation();

        Scanner sc = new Scanner(System.in) ; 
        while(true){
             System.out.println("enter the employee id ");
             int empId = sc.nextInt() ; 
             System.out.println("enter the employee name ");
             String empName = sc.next() ; 
             System.out.println("enter the employee address");
                String empAddress = sc.next() ; 
                Employee emp = new Employee(empId, empName, empAddress) ; 
            if(operation.CheckEmployee(emp.getId())){
                 operation.addEmployee(emp);
            }else{
                 System.out.println("duplicate employee id , skipp...");
            }

            System.out.println("Press -1 exit");
            int press = sc.nextInt() ; 
            if(press==-1){
                 break ; 
            }


        }
        
        System.out.println("sorting according employee ID ");
        operation.neturalSort();
        operation.dispaly();

        System.out.println("sorting accoding employee name ");
        operation.sortingByName();
        operation.dispaly();
    }
}