import java.util.Scanner;

final class  SM {
    private final int [][]sparse  ; 
    private final  int row ; 
    private final int col ; 
        public SM(int [][]input ,int row ,int col){
             this.col = col ; 
             this.row = row ; 
             sparse = new int [row][col] ; 
             for(int i=0 ; i<row; i++){
                 for(int j=0 ; j<col ; j++){
                     sparse[i][j] = input[i][j] ; 
                 }
             }
        }

        public int[][] transposeMatrix() {  // Time complexity -> O(row*col)
            int [][] result = new int[col][row] ;
             for(int i=0 ; i<row; i++){
                 for(int j=0 ; j<col ; j++){
                     result[j][i] = sparse[i][j] ; 
                 }
             }

             return result ; 
        }
        

        public boolean symmetricalMatrix() {     // Time complexity -> O(row*col)
             if(row!=col){
                 return false ; 
             }else{
                 for(int i=0 ; i<row ; i++){
                     for(int j=0 ; j<i ; j++){
                         if(sparse[i][j]!=sparse[j][i]){
                             return false ; 
                         }
                     }
                 }
             }
             return true ; 
        }


        public int[][] addMartix(SM s) {        // Time complexity -> O(row*col)
             if(row!=s.row || col!=s.col){
                 throw new IllegalArgumentException("Matrix dimension must match for addition ") ; 
             }
            int [][] result = new int [row][col] ; 
            for(int i=0 ; i<row ; i++){
                 for(int j=0 ; j<col; j++){
                     result[i][j] = sparse[i][j] + s.sparse[i][j] ; 
                 }
            }

            return result ; 

        }

        public int[][] multiplyMatrix(SM s) {        // Time complexity -> O(row*col_legth*col)
             if(col!=s.row){
                 throw new IllegalArgumentException("numbers of colums in first matrix and numbers of rows in second matrix are must be same") ; 
             }

             int [][]result = new int[row][s.col] ; 
             for(int i=0 ;i<row ; i++){
                 for(int j=0 ; j<s.col ; j++){
                     int total = 0 ; 
                     for(int k=0 ; k<col ; k++){
                         total+=sparse[i][k]*s.sparse[k][j] ; 
                     }
                     result[i][j] = total ; 
                 }
             }
             return result ; 
        }




        /*void print(){
             for(int i=0 ; i<row ; i++){
                 for(int j=0 ; j<col ; j++){
                     System.out.print(sparse[i][j]+" ");
                 }
                 System.out.println();
             }
        }*/
    
}

public class SparseMatrices{

    static void print(int [][]result){           // Time complexity -> O(row*col)
            for(int i=0 ; i<result.length ; i++){
                for(int j=0 ; j<result[0].length ; j++){
                    System.out.print(result[i][j]+" ");
                }
                System.out.println();
              }
        }
         public static void main(String[] args) {

            Scanner sc = new Scanner(System.in) ; 
            System.out.println("1. Transpose of Matrix");
            System.out.println("2. Symmetrical Matrix ");
            System.out.println("3. Addition of Matrix ");
            System.out.println("4. Multiplication of Matrix ");
            System.out.println("choose the number ");
            int choose = sc.nextInt() ; 

    
            int [][]input1 = {
                {0,0,0,0,9,0},
                {0 , 8 , 0 , 0 , 0, 0 },
                {4 , 0 , 0 , 2 , 0, 0 },
                {0 , 0 , 0 , 0 , 0, 5 },
                {0 , 0 , 2 , 0 , 0, 0 }
            };

            int [][]input2 = {
                {0,0,0,1},
                {0,0,2,0},
                {1,0,0,0},
                {3,0,0,0},
                {0,0,8,0},
                {0,9,0,0}
            };

           /*  int [][]input1 = {{0,0,5}, {1,0,0}};
        int [][]input2 = {{0,5,0},{4,0,1}}; */

    
           SM obj1 = new SM(input1 , 5,6) ; 
           SM obj2 = new SM(input2 , 6,  4) ; 



        try{
        switch (choose) {
            case 1: int [][]result =  obj1.transposeMatrix() ; 
                    print(result)  ;
                    break;
            
            case 2: System.out.println("IS martix  Symmetrucal ? ->  " + obj1.symmetricalMatrix()) ; 
                    break ; 
                
            case 3: int [][] answer = obj1.addMartix(obj2) ;
                    print(answer); 
                    break ; 

            case 4: int [][]answer1 = obj1.multiplyMatrix(obj2) ; 
                    print(answer1);
                    break ; 

        }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }  

     }
}