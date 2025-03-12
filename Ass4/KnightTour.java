import java.util.Scanner;

public class KnightTour {

    public void print(int [][] input , int size){
         for(int i=0 ; i<size ; i++){ 
            for(int j=0 ; j<size ; j++){
                 System.out.print(input[i][j]+" ");
            }
            System.out.println();
         }
    }

    public boolean safe(int row ,int col , int[][]input , int size){
         if(row<0 || row>=size || col<0 || col>=size || input[row][col]!=-1){
             return false ; 
         }

         return true ; 
    }
   
     public boolean KnightToursolve(int row , int col , int [][]input , int size , int move){
         if(move==size*size){
            print(input, size); 
             return true ; 
         }

         int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
         int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

         for(int k=0; k<8 ; k++){
             int nextx = row+xMove[k] ; 
             int nexty = col+yMove[k] ; 

             if(safe(nextx ,  nexty, input , size)){
                 input[nextx][nexty] = move ; 
                 if(KnightToursolve(nextx, nexty, input, size, move+1 )){
                     return true ; 
                 }
                 else{
                     input[nextx][nexty] = -1 ; 
                 }
             }
         }
         return false ; 
     }

    public static void main(String[] args) {
        
        KnightTour obj = new KnightTour() ; 
        Scanner sc = new Scanner(System.in) ; 

        System.out.println("enter the size : ");
        int size = sc.nextInt() ; 

        int [][]input = new int[size][size] ; 

        for(int i=0 ; i<size ; i++){
             for(int j=0 ; j<size ; j++){
                 input[i][j]=-1 ; 
             }
        }

        input[0][0] = 0 ; // first move 

        if(obj.KnightToursolve(0, 0, input , size , 1)){
             System.out.println("Answer is exist");
        }else{
             System.out.println("Answer is not exist");
        }

    }
}
