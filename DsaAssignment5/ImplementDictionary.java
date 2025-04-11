package DsaAssignment5;
import java.util.ArrayList;

import java.util.Scanner;

class Pair {
    private int key;
    private String value;

    Pair(int key, String value) {
        this.key = key;
        this.value = value;
    }

    void setValue(String val) {
        value = val;
    }

    int getKey() {
        return key;
    }

    String getValue() {
        return value;
    }
}

class Node {
    Pair pair;
    Node left;
    Node right;

    Node(Pair pair) {
        this.pair = pair;
        this.left = null;
        this.right = null;

    }
}

interface Dictionary {
    void add(Pair p);

    void deleteNode(int key);

    ArrayList<Node> sortedList();

    String getValue(int key);

    ArrayList<Node> sortedListWithRange(int keyfirst, int keySecond);

}

class BST implements Dictionary {
    private Node root;

    BST(ArrayList<Pair> iniArrayList) {
        this.root = null;
        for (Pair p : iniArrayList) {
            add(p);
        }
    }

    public void add(Pair pair) {
        if (root == null) {
            root = new Node(pair);

        } else {
            Node temp = root;
            while (true) {
                if (temp.pair.getKey() < pair.getKey()) {
                    if (temp.right != null) {
                        temp = temp.right;
                    } else {
                        temp.right = new Node(pair);
                        break;
                    }
                } else if (temp.pair.getKey() > pair.getKey()) {
                    if (temp.left != null) {
                        temp = temp.left;
                    } else {
                        temp.left = new Node(pair);
                        break;

                    }
                } else if (temp.pair.getKey() == pair.getKey()) {
                    temp.pair.setValue(pair.getValue());
                    break;
                }
            }
        }

    }

    void inordertravasal(Node root, int key1, int key2, ArrayList<Node> result) {
        if (root == null) {
            return;
        }

        inorder(root.left, result);

        if (root.pair.getKey() >= key1 && root.pair.getKey() <= key2) {
            result.add(root);
        }

        inorder(root.right, result);

    }

    public ArrayList<Node> sortedListWithRange(int keyFirst, int keySecond) {
        ArrayList<Node> result = new ArrayList<>();
        inordertravasal(root, keyFirst, keySecond, result);
        return result;
    }

    public String getValue(int key) {
        String string = "key is not present in this Dictionary";

        Node temp = root;
        while (temp != null) {
            if (temp.pair.getKey() == key) {
                return temp.pair.getValue();
            } else if (temp.pair.getKey() < key) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        return string;

    }

    public Node helper(Node root) {

        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {

            return root.left;
        }

        Node rightChild = root.right;
        Node lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }

    public Node findLastRight(Node root) {

        if (root.right == null) {
            return root;
        }
        return findLastRight(root.right);
    }

    public void deleteNode(int key) {
        if (root == null) {
            System.out.println("Dictionary is empty : ");

        } else {
            if (key == root.pair.getKey()) {
                root = helper(root);
            } else {
                Node dummy = root;
                while (dummy != null) {
                    if (root.pair.getKey() > key) {
                        if (root.left != null && root.left.pair.getKey() == key) {
                            root.left = helper(root.left);
                            break;
                        } else {
                            root = root.left;
                        }
                    } else {
                        if (root.right != null && root.right.pair.getKey() == key) {
                            root.right = helper(root.right);
                            break;
                        } else {
                            root = root.right;
                        }
                    }

                }
            }
        }

    }

    void inorder(Node root, ArrayList<Node> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root);
        inorder(root.right, result);
    }

    public ArrayList<Node> sortedList() {
        ArrayList<Node> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    void display(ArrayList<Node> result) {
        if (result.size() == 0) {
            System.out.println("No key - value (empty) ");
        }
        for (Node node : result) {
            System.out.println(node.pair.getKey() + " " + node.pair.getValue());
        }
    }

}

public class ImplementDictionary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pair> list = new ArrayList<>();

        while (true) {
            System.out.println("enter the key ");
            int key = sc.nextInt();
            System.out.println("enter the value ");
            String value = sc.next();

            list.add(new Pair(key, value));
            System.out.println("press -1 to exit");
            int press = sc.nextInt();
            if (press == -1) {
                break;
            }
        }

        BST operation = new BST(list);

        while (true) {
            System.out.println("1 : add a key-value pair to dictionary ");
            System.out.println("2 : delete a key value pair from the dictionary");
            System.out.println("3 : get the value corresponding to a specified key");
            System.out.println("4 : return sorted list of key value pairs");
            System.out.println("5 : return the sorted list of key value pairs for all the keys >=K1 and <=K2");

            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("enter the key ");
                    int key = sc.nextInt();
                    System.out.println("enter the value");
                    String value = sc.next();
                    operation.add(new Pair(key, value));
                    break;

                case 2:
                    System.out.println("enter the key you want to delete ");
                    int keyInput = sc.nextInt();
                    operation.deleteNode(keyInput);
                    break;

                case 3:
                    System.out.println("enter the key you want to get value  ");
                    int keyGet = sc.nextInt();
                    System.out.println(operation.getValue(keyGet));
                    break;

                case 4:
                    ArrayList<Node> resultList = operation.sortedList();
                    operation.display(resultList);
                    break;

                case 5:
                    System.out.println("enter the key first  ");
                    int keyFirst = sc.nextInt();
                    System.out.println("enter the key second  ");
                    int keySecond = sc.nextInt();
                    ArrayList<Node> result = operation.sortedListWithRange(keyFirst, keySecond);
                    operation.display(result);

                default:

                    break;
            }

            System.out.println("press -1 exit");
            int press = sc.nextInt();
            if (press == -1) {
                break;
            }
        }

    }
}
