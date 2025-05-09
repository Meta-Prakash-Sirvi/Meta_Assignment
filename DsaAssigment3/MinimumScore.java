package DsaAssigment3;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Bowler {
    public String name;
    public int bowl;

    Bowler(String name, int bowl) {
        this.name = name;
        this.bowl = bowl;
    }
}

class Score {

    private PriorityQueue<Bowler> maxHeap = new PriorityQueue<>(
            (firstBolwer, secondBolwer) -> Integer.compare(secondBolwer.bowl, firstBolwer.bowl));

    ArrayList<String> minimumScore(ArrayList<Bowler> bowl, int play) {

        ArrayList<String> bolwerName = new ArrayList<>();

        maxHeap.addAll(bowl);

        while (play > 0 && !maxHeap.isEmpty()) {
            Bowler bowler = maxHeap.poll();
            bolwerName.add(bowler.name);
            bowler.bowl--;
            if (bowler.bowl > 0) {
                maxHeap.add(bowler);
            }
            play--;
        }

        return bolwerName;
    }

}

public class MinimumScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Score score = new Score();
        ArrayList<Bowler> listOfBowlers = new ArrayList<>();

        System.out.println("Enter the numbers of Bowler ");

        int numbersOfBowlers = sc.nextInt();

        for (int i = 0; i < numbersOfBowlers; i++) {
            System.out.println("Enter the name of bolwer");
            String name = sc.next();
            System.out.println("Enter the bowl of bowler : ");
            int bowl = sc.nextInt();

            listOfBowlers.add(new Bowler(name, bowl));
        }

        System.out.println("Enter the numbers of bowl virat play : ");
        int viratPlay = sc.nextInt();

        ArrayList<String> bowlerNameList = new ArrayList<>();
        bowlerNameList = score.minimumScore(listOfBowlers, viratPlay);

        System.out.println("Squeuence of the bowler is : ");
        for (int index = 0; index < bowlerNameList.size(); index++) {
            System.out.print(bowlerNameList.get(index) + ", ");
        }

    }
}