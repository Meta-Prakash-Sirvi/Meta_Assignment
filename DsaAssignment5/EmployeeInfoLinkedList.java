package DsaAssignment5;

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

    Node sortSalary(Node head) {
        if (head == null) {
            return head;
        }
        Employee emp = new Employee(-1, null, 0, 0);
        Node helper = new Node(emp);
        Node cur = head;
        Node pre = helper;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            while (pre.next != null && (pre.next.data.getSalary() > cur.data.getSalary()
                    || (pre.next.data.getSalary() == cur.data.getSalary()
                            && pre.next.data.getAge() < cur.data.getAge()))) {
                pre = pre.next;
            }

            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }

        return helper.next;
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

        head = op.sortSalary(head);

        op.display(head);

    }
}
