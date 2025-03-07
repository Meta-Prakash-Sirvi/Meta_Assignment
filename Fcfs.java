import java.util.*; 

class Fcfs{
    private int [][] process ; 
    private int[] ct ;
    private int[] wt ; 
    private int[] tt ; 

    public Fcfs(int[][]p){
         process = p ; 
         int size = process.length ; 
         ct = new int[size] ;
         wt = new int[size] ; 
         tt = new int[size] ; 
    } 

    public void completionTime(){    
         int size = process.length ;
         int compTime = 0 ; 

         for(int i=0 ;i<size; i++){
             int arrivalTime = process[i][0] ; 
             int burstTime = process[i][1] ; 
             if(compTime<arrivalTime){
                 compTime = arrivalTime ; 
             }
             ct[i] = compTime + burstTime ; 
             compTime = ct[i] ; 
         }
    }
     public void turnaroundtime(){     //TAT = CT-AT
         for(int i=0 ; i<process.length ; i++){
            int arrivalTime = process[i][0] ; //arrival time 
             tt[i] = ct[i] - arrivalTime ; 
         }
     }

    public void wating() {     // WT = TAT- BT
         int size = process.length ; 
         for(int i=0 ; i<size ; i++){
            int bursttime = process[i][1] ; 
             wt[i] = tt[i] - bursttime; 
         }
    }


    public double avgtime(){
         int sum = 0 ; 
         int n  = wt.length ; 
         for(int i=0 ; i<n; i++){
             sum+=wt[i] ; 
         }
         return sum/n ; 
    }

    public int maxWating(){
          int maxi = 0 ; 
          for(int i=0 ; i<wt.length ; i++){
             if(maxi<wt[i]){
                 maxi = wt[i] ; 
             }
          }

          return maxi ;  // return maximum wating time 
    }

    public void display(){   // print compeltion time , trunaround time and watingtime 
          System.out.println("CT---TAT---WT") ; 
          for(int i=0 ; i<process.length ; i++){
               System.out.println(ct[i] +"   "+ tt[i] + "   "+  wt[i]) ; 
          }
    }

    public static void main(String[] args){
         int [][]process = {{0,10}, {6,20}, {60,10}, {110,5}} ;

            Fcfs s =new Fcfs(process) ; 
            s.completionTime(); 
            s.turnaroundtime() ; 
            s.wating() ; 
            s.display() ;    
            System.out.println("Avarage Time = " + s.avgtime()) ; 
            System.out.println("Max Wating Time = " + s.maxWating()) ; 
    }
}