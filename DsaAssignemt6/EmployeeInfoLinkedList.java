package DsaAssignemt6;

import java.util.Scanner;

class Node {
    Employee data;
    Node next;

    Node(Employee data) {
        this.data = data;
        this.next = null;
    }

    String getInformation() {
        return "Employee ID :" + data.getEmpId() + " Employee Name : " + data.getempName() + " Employee Age : " + data.getAge()
                + " Employee Salary : " + data.getSalary();
    }
}

class Employee {
    private int empId;
    private String empName;
    private int salary;
    private int age;

    Employee(int empId, String empName, int salary, int age) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.age = age;
    }

    int getSalary() {
        return salary;
    }

    int getAge() {
        return age;
    }

    int getEmpId(){
         return empId ; 
    }
    String getempName(){
         return empName ;
    }

}

class Operation {

    Node insert(Node head, Employee data) {
        Node newNode = new Node(data);
        if (head == null) {
            return newNode;
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }

        tempNode.next = newNode;

        return head;

    }

    void display(Node head) {
        Node tempNode = head;
        while (tempNode != null) {
            System.out.println(tempNode.getInformation());
            tempNode = tempNode.next;
        }

    }

   public Node sortList(Node head) {
        if(head == null || head.next == null) return head;
        Node end = head;
        while(end.next != null) {
            end = end.next;
        }
        quickSort(head, end);
        return head;
    }
    
    public void quickSort(Node start, Node end) {
        if(start!=end) {
            Node pivot = partition(start, end);
            if(pivot == null) {
              quickSort(start.next, end);
            } else if(pivot.next == end) {
                quickSort(start, pivot);
            } else {
                quickSort(start, pivot);
                quickSort(pivot.next.next, end);
            }   
        }
    }
    
    public Node partition(Node start, Node end) {
        Node ptr = start;
        Node pivot = start;
        start = start.next;
        Node prev = null;
        while(start!= end) {
            if((pivot.data.getSalary() < start.data.getSalary()) ||(pivot.data.getSalary()==start.data.getSalary() && pivot.data.getAge() > start.data.getAge())) {
                prev = ptr;
                ptr = ptr.next;
                swap(ptr, start);
            }
            
            start = start.next;
        }
        if(start == end) {
            if((pivot.data.getSalary()<start.data.getSalary()) ||(pivot.data.getSalary()==start.data.getSalary() && pivot.data.getAge() > start.data.getAge())) {
                prev = ptr;
                ptr = ptr.next;
                swap(ptr, start); 
            }
        }
        
        swap(ptr, pivot);
        return prev;
    }
    
    public void swap(Node node1, Node node2) {
        Employee temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}

public class EmployeeInfoLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Operation op = new Operation();
        Node head = null;

        while (true) {
            System.out.println("enter the employee id");
            int empId = sc.nextInt();
            System.out.println("enter the employee name");
            String empName = sc.next();
            System.out.println("enter the employee age");
            int empAge = sc.nextInt();
            System.out.println("enter the employee salary");
            int empSalary = sc.nextInt();

            head = op.insert(head, new Employee(empId, empName, empSalary, empAge));
            System.out.println("Exit press -1 ");
            int press = sc.nextInt();
            if (press == -1) {
                break;
            }

        }

        head = op.sortList(head);

        op.display(head);

    }
}
