package DsaAssigment3;

import java.util.Scanner;

interface PriorityQueue {
    void add(int data);

    void delete();

    boolean isEmpty();

    boolean isFull();

    void peekElement();

}

class PriorityQueueImplements implements PriorityQueue {
    private int[] input;
    private int size;
    int rear;
    int front;

    PriorityQueueImplements(int size) {
        this.size = size;
        this.input = new int[size];
        this.front = -1;
        this.rear = -1;

    }

    public void add(int data) {
        if (isFull()) {
            System.out.println("queue is alreay full ");
            return;
        }
        if (rear == -1) {
            front = rear = 0;
            input[rear] = data;
            rear++;
        } else {
            int index = 0;
            for (index = rear - 1; index >= 0; index--) {
                if (data > input[index]) {
                    input[index + 1] = input[index];
                } else {
                    break;
                }
            }
            input[index + 1] = data;
            rear++;

        }
    }

    public void delete() {
        if (isEmpty()) {
            System.out.println("Queue is alredy empty ");
            return;
        }
        if (front + 1 == rear) {
            front = rear = -1;

        } else {
            front++;
        }
    }

    public boolean isEmpty() {
        if (front == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (rear == size) {
            return true;
        } else {
            return false;
        }
    }

    public void peekElement() {

        if (isEmpty()) {
            System.out.println("Queue is empty : ");
        } else {
            System.out.println("peek element is : " + input[front]);
        }
    }

    void display() {
        if (isEmpty()) {
            System.out.println("queue is empty ");
        } else {
            for (int i = front; i < rear; i++) {
                System.out.print(input[i] + " ");
            }
        }
    }
}

public class ImplementsPriorityQueue {
    public static void main(String[] args) {
        PriorityQueueImplements pq = new PriorityQueueImplements(5);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the choice");
        
        
        while (true) {
         System.out.println("1. add element in priority Queue : ");
        System.out.println("2. Delete  element in priority Queue : ");
        System.out.println("3. Peek element  element in priority Queue : ");
        System.out.println("4. IS Empty  priority Queue : ");
        System.out.println("5. IS Full priority Queue : ");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter the data you want to insert : ");
                    int data = sc.nextInt();
                    pq.add(data);
                    break;

                case 2:
                    pq.delete();
                    break;

                case 3:
                    pq.peekElement();
                    break;

                case 4:
                    if (pq.isEmpty()) {
                        System.out.println("queue is empty ");
                    } else {
                        System.out.println("queue is not empty ");
                    }
                    break;

                case 5:
                    if (pq.isFull()) {
                        System.out.println("queue is empty ");
                    } else {
                        System.out.println("queue is not empty ");
                    }
                    break;

            }
            System.out.println("press -1 to exit ");
            int press = sc.nextInt(); 

            if(press == -1){
                 break ;
            }

        }
        
        pq.display();
    }
}
