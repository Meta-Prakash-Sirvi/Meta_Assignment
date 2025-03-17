import java.util.Scanner;

final class  SparseMatricesOperation {
    private final int [][]sparse  ; 
    private final  int row ; 
    private final int col ; 
        public SparseMatricesOperation(int [][]input){
            this.row = input.length; 
           this.col = input[0].length ; 
           this.sparse = copy(input) ; 
        }


        private int [][] copy(int [][]input){
             int row = input.length ; 
             int col = input[0].length ; 
             int [][] copyarray = new int[row][col] ;
             for(int rowIndex=0 ; rowIndex<row; rowIndex++){
                for(int colIndex=0 ; colIndex<col ; colIndex++){
                    copyarray[rowIndex][colIndex] = input[rowIndex][colIndex] ; 
                }
            } 
            return copyarray ; 
        }


        public int[][] transposeMatrix() {  // Time complexity -> O(row*col)
            int [][] result = new int[col][row] ;
             for(int rowIndex=0 ; rowIndex<row; rowIndex++){
                 for(int colIndex = 0 ; colIndex<col ; colIndex++){
                     result[colIndex][rowIndex] = sparse[rowIndex][colIndex] ; 
                 }
             }

             return result ; 
        }
        

        public boolean symmetricalMatrix() {     // Time complexity -> O(row*col)
             if(row!=col){
                 return false ; 
             }else{
                 for(int rowIndex=0 ; rowIndex<row ; rowIndex++){
                     for(int colIndex=0 ; colIndex<rowIndex ; colIndex++){
                         if(sparse[rowIndex][colIndex]!=sparse[colIndex][rowIndex]){
                             return false ; 
                         }
                     }
                 }
             }
             return true ; 
        }


        public int[][] addMartix(SparseMatricesOperation s) {        // Time complexity -> O(row*col)
             if(row!=s.row || col!=s.col){
                 throw new IllegalArgumentException("Matrix dimension must match for addition ") ; 
             }
            int [][] result = new int [row][col] ; 
            for(int rowIndex=0 ; rowIndex<row ; rowIndex++){
                 for(int colIndex=0 ; colIndex<col; colIndex++){
                     result[rowIndex][colIndex] = sparse[rowIndex][colIndex] + s.sparse[rowIndex][colIndex] ; 
                 }
            }

            return result ; 

        }

        public int[][] multiplyMatrix(SparseMatricesOperation s) {        // Time complexity -> O(row*col_length*col)
             if(col!=s.row){
                 throw new IllegalArgumentException("numbers of colums in first matrix and numbers of rows in second matrix are must be same") ; 
             }

             int [][]result = new int[row][s.col] ; 
             for(int rowIndex=0 ;rowIndex<row ; rowIndex++){
                 for(int colIndex=0 ; colIndex<s.col ; colIndex++){
                     int total = 0 ; 
                     for(int k=0 ; k<s.row ; k++){
                         total+=sparse[rowIndex][k]*s.sparse[k][colIndex] ; 
                     }
                     result[rowIndex][colIndex] = total ; 
                 }
             }
             return result ; 
        }
    
}

public class SparseMatrices{

    static void print(int [][]result){           // Time complexity -> O(row*col)
        System.out.println("Result matrix is : ");
            for(int rowIndex=0 ; rowIndex<result.length ; rowIndex++){
                for(int colIndex=0 ; colIndex<result[0].length ; colIndex++){
                    System.out.print(result[rowIndex][colIndex]+" ");
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

    

         
            System.out.println("Enter matrix row and col");
            int rowinput1 = sc.nextInt() ;
            int colinput1 = sc.nextInt() ; 
            System.out.println("Enter matrix elements");
            int [][]matrix1 = new int[rowinput1][colinput1] ; 
            for(int rowIndex=0 ;rowIndex<rowinput1 ; rowIndex++){
              for(int colIndex=0 ; colIndex<colinput1 ; colIndex++){
                matrix1[rowIndex][colIndex] = sc.nextInt() ; 
             }
           }
    
        SparseMatricesOperation obj1 = new SparseMatricesOperation(matrix1) ; 
       

        try{
        switch (choose) {
            case 1: int [][]result =  obj1.transposeMatrix() ; 
                    print(result)  ;
                    break;
            
            case 2: System.out.println("IS martix  Symmetrucal ? ->  " + obj1.symmetricalMatrix()) ; 
                    break ; 
                
            case 3: 
                     System.out.println("Enter matrix row and col");
                     int rowinput2 = sc.nextInt() ;
                     int colinput2 = sc.nextInt() ; 
                     System.out.println("Enter matrix elements");
                     int [][]matrix2 = new int[rowinput2][colinput2] ; 
                     for(int rowIndex=0 ;rowIndex<rowinput2 ; rowIndex++){
                       for(int colIndex=0 ; colIndex<colinput2 ; colIndex++){
                         matrix2[rowIndex][colIndex] = sc.nextInt() ; 
                      }
                    }
                       SparseMatricesOperation obj2 = new SparseMatricesOperation(matrix2) ; 

                    int [][] answer = obj1.addMartix(obj2) ;
                    print(answer); 
                    break ; 

            case 4: System.out.println("Enter matrix row and col");
                      int rowinput3 = sc.nextInt() ;
                      int colinput3 = sc.nextInt() ; 
                      System.out.println("Enter matrix elements");
                      int [][]matrix3 = new int[rowinput3][colinput3] ; 
                       for(int rowIndex=0 ; rowIndex<rowinput3 ; rowIndex++){
                       for(int colIndex=0 ;colIndex<colinput3 ; colIndex++){
                          matrix3[rowIndex][colIndex] = sc.nextInt() ; 
                     }
                   }

                    SparseMatricesOperation obj3 = new SparseMatricesOperation(matrix3) ;
            
                    int [][]answer1 = obj1.multiplyMatrix(obj3) ; 

                    print(answer1);
                    break ; 

        }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }  

     }
}