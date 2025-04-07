package DsaAssigment3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    String name;
    Node left;
    Node right;
    Node parent;

    Node(String name) {
        this.name = name;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

class CommandPrompt {

    public Node mkdir(Node root, String s1) {
        Node newNode = new Node(s1);
        newNode.parent = root;

        if (root.left == null) {
            root.left = newNode;
        } else if (root.right == null) {
            root.right = newNode;
        } else {
            System.out.println("This directory already has two subfolders.");
        }

        return root;
    }

    Node find(Node root, String s1) {
        if (root == null)
            return null;

        if (root.name.equals(s1))
            return root;

        Node left = find(root.left, s1);
        if (left != null)
            return left;

        return find(root.right, s1);
    }

    void ls(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            System.out.println("No directories found");
        } else {
            System.out.println("Folders inside: " + root.name);
            if (root.left != null) {
                System.out.println(root.left.name);
            }
            if (root.right != null) {
                System.out.println(root.right.name);
            }
        }
    }

    Node cd(Node root, String s1) {
        Node temp = find(root, s1);
        if (temp == null) {
            System.out.println("Error: Directory not found");
            return root;
        }
        return temp;
    }

    Node bk(Node current) {
        if (current.parent != null) {
            return current.parent;
        } else {
            System.out.println("Already at root directory");
            return current;
        }
    }

    void tree(Node root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "|__ ") + root.name);
            tree(root.left, prefix + (isLeft ? "| " : " "), true);
            tree(root.right, prefix + (isLeft ? "| " : " "), false);
        }
    }

    String getFullPath(Node current) {
        StringBuilder path = new StringBuilder();
        while (current != null) {
            path.insert(0, "\\" + current.name);
            current = current.parent;
        }
        return "R:" + path + ">";
    }
}

public class VirtualCommandPrompt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CommandPrompt commandPrompt = new CommandPrompt();
        Node rootNode = new Node("root");
        Node root = rootNode;

       

        while (true) {
            System.out.print(commandPrompt.getFullPath(root));
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            String choose = parts[0];

            switch (choose) {
                case "mkdir":
                    if (parts.length < 2) {
                        System.out.println("The syntax of the command is incorrect : write folder name also :");
                        break;
                    }
                    String name = parts[1];
                    root = commandPrompt.mkdir(root, name);
                    break;

                case "tree":
                    commandPrompt.tree(rootNode, "", true);
                    break;

                case "cd":
                    if (parts.length < 2) {
                        System.out.println("The syntax of the command is incorrect : write folder name also :");
                        break;
                    }
                    String folder = parts[1];
                    root = commandPrompt.cd(root, folder);
                    break;

                case "bk":
                    root = commandPrompt.bk(root);
                    break;

                case "find":
                    if (parts.length < 2) {
                        System.out.println("The syntax of the command is incorrect : write folder name also :");
                        break;
                    }
                    String folderName = parts[1];
                    Node temp = commandPrompt.find(rootNode, folderName);
                    if (temp != null) {
                        System.out.println("Directory found: " + temp.name);
                    } else {
                        System.out.println("Directory not found.");
                    }
                    break;

                case "ls":
                    commandPrompt.ls(root);
                    break;

                case "exit":
                    System.out.println("Exit......");
                    return;

                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }
}