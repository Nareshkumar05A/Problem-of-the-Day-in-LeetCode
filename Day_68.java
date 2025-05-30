/*73. Set Matrix Zeroes */

/*Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

 

Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
  */

  class Solution {
    public void setZeroes(int[][] matrix) {
        int row=matrix.length;
        int col=matrix[0].length;

        int[] rowlen=new int[row];
        int[] collen=new int[col];

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if( matrix[i][j]==0)
                {
                    rowlen[i]=1;
                    collen[j]=1;
                    System.out.println(i+" "+j);
                }
            }
        }
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(rowlen[i]==1||collen[j]==1){
                matrix[i][j]=0;
                }
            }
        }
    }
}