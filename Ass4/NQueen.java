import java.util.Scanner;

public class NQueen {

     public boolean isSafe(int [][]board ,int row , int col,  int size){
         // vartical checking 
         int newRow = 0 ;
         while(newRow<row){
             if(board[newRow][col]==1){
                 return false; 
             }
             newRow++ ;
         }

         // upper left diagonal chekcing 
         int newrow1 = row; 
         int newcol1 = col ; 
         while(newrow1>=0 && newcol1>=0){
             if(board[newrow1][newcol1]==1){
                 return false; 
             }
             newrow1-- ; 
             newcol1-- ; 
         }

         //  upper right diagonal checking 
         int newrow2 = row ; 
         int newcol2 = col ; 
         while(newrow2>=0 && newcol2<size){
             if(board[newrow2][newcol2]==1){
                 return false ; 
             }
             newrow2-- ; 
             newcol2++ ; 
         }

         return true ; 

     }

     void display(int [][] board){
        int size = board.length ; 
         for(int i=0 ; i<size ; i++){
             for(int j=0 ; j<size ; j++){
                 System.out.print(board[i][j]+" ");
             }
             System.out.println();
         }
     }

     public boolean nQueen(int [][] board ,int row , int size){
         if(row==size){
            display(board) ; 
             return true ; 
         }

         for(int col = 0 ; col<size ; col++){
             if(isSafe(board ,  row , col , size)){
                 board[row][col] = 1; 
                if(nQueen(board , row+1 , size)){
                     return true ; 
                } 
                     board[row][col] = 0 ; 
                }
             } 
             return false ; 
         }
    
    public static void main(String[] args) {
        
        NQueen s = new NQueen() ; 
        System.out.println("enter the board size");
        Scanner sc = new Scanner(System.in) ; 
        int size=  sc.nextInt() ;

        int board[][] = new int[size][size] ; 

        boolean result = s.nQueen(board, 0, size) ; 
        System.out.println("Result : " + result);

        
    }
}
