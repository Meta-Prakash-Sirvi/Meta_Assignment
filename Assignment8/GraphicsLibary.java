package Assignment8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}

interface Shape {
    double getArea();

    double getParimeter();

    Point getOrigin();

}

enum ShapeType {
    Circle,
    Rectangle,
    Triangle,
    Polygon,
    Square;
}

class Circle implements Shape {
    Point origin;
    List<Integer> dimension;

    Circle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;

    }

    public double getArea() {
        return 3.14 * (dimension.get(0) * dimension.get(0));
    }

    public double getParimeter() {
        return 2 * 3.14 * (dimension.get(0));
    }

    public Point getOrigin() {
        return origin;
    }
}

class Triangle implements Shape {
    Point origin;
    List<Integer> dimension;

    Triangle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;
    }

    public double getArea() {
        return (dimension.get(0) * dimension.get(1)) / 2;
    }

    public double getParimeter() {
        return (dimension.get(0) + dimension.get(1) + dimension.get(2));
    }

    public Point getOrigin() {
        return origin;
    }
}

class Rectangle implements Shape {
    Point origin;
    List<Integer> dimension;

    Rectangle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;

    }

    public double getArea() {
        return dimension.get(0) * dimension.get(1);
    }

    public double getParimeter() {
        return 2 * (dimension.get(0) + dimension.get(1));
    }

    public Point getOrigin() {
        return origin;
    }
}

class Square implements Shape {
    Point origin;
    List<Integer> dimension;

    Square(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;

    }

    public double getArea() {
        return dimension.get(0);
    }

    public double getParimeter() {
        return 4 * dimension.get(0);
    }

    public Point getOrigin() {
        return origin;
    }
}

class Polygon implements Shape{
    Point origin;
    List<Integer> dimension;

    Polygon(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;

    }

    public double getArea() {
        int totalside = dimension.size() ; 
        double side = dimension.get(0) ;
        return (totalside*side*side)/(4*Math.tan(Math.PI/totalside));
    }

    public double getParimeter() {
        double parimeter = 0 ; 
        for(int side : dimension){
             parimeter+=side; 
        }
        return parimeter ; 
    }

    public Point getOrigin() {
        return origin;
    }
}

class Screen {
    List<Shape> listShapes;

    Screen() {
        this.listShapes = new ArrayList<>();
    }

    void addShape(Shape shape) {
        listShapes.add(shape);
    }

    void deleteShape(Shape shape) {
        listShapes.remove(shape);
    }

    void deleteAllShapeType(ShapeType type) {
        listShapes.removeIf(shape -> shape.getClass().getSimpleName().equalsIgnoreCase(type.name()));
    }

    List<Shape> shortShapes(){
        List<Shape> sortshape = new ArrayList<>(listShapes);
        int size = sortshape.size() ;
        for(int i=0 ;i<size-1 ; i++){
             for(int j=0 ; j<size-i-1 ; j++){
                 if(sortshape.get(j).getArea()>sortshape.get(j+1).getArea()){
                     Shape temp = sortshape.get(j) ; 
                     sortshape.set(j,sortshape.get(j+1)) ; 
                     sortshape.set(j+1,temp) ;
                 }
             }
        }
        return sortshape;  
    }

    void display() {
        for (Shape shape : listShapes) {
            System.out.println("Shape Name: " + shape.getClass().getSimpleName() + ", Area: " + shape.getArea()
                    + ", Parimeter: " + shape.getParimeter() + ", Origin Point X: " + shape.getOrigin().getX()
                    + ", Origin Point Y: " + shape.getOrigin().getY());

        }
    }

}

class ShapeFactory {
    public static Shape createShape(ShapeType type, Point origin, List<Integer> dime) {
        switch (type) {
            case Circle:
                return new Circle(origin, dime);

            case Rectangle:
                return new Rectangle(origin, dime);

            case Square:
                return new Square(origin, dime);

            case Triangle:
                return new Triangle(origin, dime);

            case Polygon: 
                return new Polygon(origin, dime); 

            default:
                return null;
        }
    }
}

public class GraphicsLibary {
    public static void main(String[] args) {
        Screen screen = new Screen();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Cricle ");
            System.out.println("2. Rectangle ");
            System.out.println("3. Triangle ");
            System.out.println("4. Square ");
            System.out.println("5. Polygon");
            System.out.println("choose the option :");
            int choose = sc.nextInt();

            switch (choose) {
                case 1:
                    System.out.println("enter the origin point  x and y");
                    double circleOriginPointX = sc.nextInt();
                    double circleOriginPointY = sc.nextInt();
                    System.out.println("Enter the Radius of the circle ");
                    int radius = sc.nextInt();
                    Shape circle = ShapeFactory.createShape(ShapeType.Circle,
                            new Point(circleOriginPointX, circleOriginPointY), Arrays.asList(radius));
                    screen.addShape(circle);

                    break;

                case 2:
                    System.out.println("enter the origin point  x and y");
                    double rectangleOriginPointX = sc.nextInt();
                    double rectangleOriginPointY = sc.nextInt();
                    System.out.println("Enter the lenght and width ");
                    int rectangleLenght = sc.nextInt();
                    int rectangleWidth = sc.nextInt();
                    Shape rectangle = ShapeFactory.createShape(ShapeType.Rectangle,
                            new Point(rectangleOriginPointX, rectangleOriginPointY),
                            Arrays.asList(rectangleLenght, rectangleWidth));
                    screen.addShape(rectangle);

                    break;

                case 3:
                    System.out.println("enter the origin point  x and y");
                    double triangleOriginPointX = sc.nextInt();
                    double triangleOriginPointY = sc.nextInt();
                    System.out.println("Enter the height , width , length");
                    int triangleWidth = sc.nextInt();
                    int triangleLength = sc.nextInt();
                    int triangleHeight = sc.nextInt();
                    Shape tringle = ShapeFactory.createShape(ShapeType.Triangle,
                            new Point(triangleOriginPointX, triangleOriginPointY),
                            Arrays.asList(triangleHeight, triangleWidth, triangleLength));
                    screen.addShape(tringle);

                    break;

                case 4:
                    System.out.println("enter the origin point  x and y");
                    double SquareOriginPointX = sc.nextInt();
                    double SquareOriginPointY = sc.nextInt();
                    System.out.println("Enter the width ");
                    int SquareWidth = sc.nextInt();
                    Shape Square = ShapeFactory.createShape(ShapeType.Square,
                            new Point(SquareOriginPointX, SquareOriginPointY), Arrays.asList(SquareWidth));
                    screen.addShape(Square);

                    break;

                case 5: System.out.println("enter the origin point  x and y");
                double polygonOriginPointX = sc.nextInt();
                double polygonOriginPointY = sc.nextInt();
                System.out.println("Enter the side ");
                int polygonSide = sc.nextInt();
                Shape polygon = ShapeFactory.createShape(ShapeType.Polygon,
                        new Point(polygonOriginPointX, polygonOriginPointY),
                        Arrays.asList(polygonSide, polygonSide, polygonSide));
                screen.addShape(polygon);

            }
            System.out.println("continue :(-1 for exit)");
            int press = sc.nextInt();

            if (press == -1) {
                break;
            }

        }

        System.out.println("Screen contain shapes are -> ");
        screen.display();
        System.out.println();

        System.out.println("1. if you want to delete a  all same type of Shape");
        int press = sc.nextInt(); 
        if(press==1){
        System.out.println("1. delete all the shape of Circle : ");
        System.out.println("2. delete all the shape of Rectangle : ");
        System.out.println("3. delete all the shape of Triangle : ");
        System.out.println("4. delete all the shape of Square  : ");
        System.out.println("5. delete all the shape of Polygon  : ");
        System.out.println("enter the choice ");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                screen.deleteAllShapeType(ShapeType.Circle);
                System.out.println("After the deletion of all circle type shape");
                screen.display();

                break;

            case 2:
                screen.deleteAllShapeType(ShapeType.Rectangle);
                System.out.println("After the deletion of all Ractangle type shape");
                screen.display();
                break;

            case 3:
                screen.deleteAllShapeType(ShapeType.Triangle);
                System.out.println("After the deletion of all Triangle type shape");
                screen.display();
                break;

            case 4:
                screen.deleteAllShapeType(ShapeType.Square);
                System.out.println("After the deletion of all Square type shape");
                screen.display();
                break;

            case 5:
                screen.deleteAllShapeType(ShapeType.Polygon);
                System.out.println("After the deletion of all Polygon type shape");
                screen.display();
                break;
        }
    }

        System.out.println("------------------------------------");
        System.out.println("Sort the Shape by the Area : ");
        List<Shape> resuList =  screen.shortShapes(); 
        for (Shape shape : resuList) {
            System.out.println("Shape Name: " + shape.getClass().getSimpleName() + ", Area: " + shape.getArea()
                    + ", Parimeter: " + shape.getParimeter() + ", Origin Point X: " + shape.getOrigin().getX()
                    + ", Origin Point Y: " + shape.getOrigin().getY());

        }
    

    }
}

