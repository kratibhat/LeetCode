package leetcode.matrixTraversal;
//Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
//
//A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
//
//Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
//
//Return true if any cycle of the same value exists in grid, otherwise, return false.
//Example 1:
//
//
//
//Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
//Output: true
//Explanation: There are two valid cycles shown in different colors in the image below:
//
//Example 2:
//
//
//
//Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
//Output: true
//Explanation: There is only one valid cycle highlighted in the image below:

//Out of Bounds: If i or j are outside the grid, it's not a cycle (return false).
//
//Character Mismatch: If grid[i][j] != init, the path is broken (return false).
//
//The "Aha!" Moment: if(visited[i][j]) return true;. If we reach a cell that
// is already marked true in our
// current character path, we have successfully looped back. This confirms a cycle.
public class DetectCyclesin2DGrid {
    //15ms
    public boolean dfs(char[][] grid,int i,int j,int pari,int parj,char init,boolean[][] visited){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length)   return false;
        if(grid[i][j]!=init) return false;
        if(visited[i][j])   return true;
        visited[i][j]=true;
        if(i+1!=pari || j!=parj) //to
            if(dfs(grid,i+1,j,i,j,init,visited)) return true;
        if(i-1!=pari || j!=parj)
            if(dfs(grid,i-1,j,i,j,init,visited)) return true;
        if(i!=pari || j+1!=parj)
            if(dfs(grid,i,j+1,i,j,init,visited)) return true;
        if(i!=pari || j-1!=parj)
            if(dfs(grid,i,j-1,i,j,init,visited)) return true;
        return false;
    }
    public boolean containsCycle(char[][] grid) {
        int n=grid.length,m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j])
                    if(dfs(grid,i,j,i,j,grid[i][j],visited)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCyclesin2DGrid solution = new DetectCyclesin2DGrid();
        char[][] grid1 = {{'a', 'a', 'a', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'a', 'a', 'a'}};
        char[][] grid2 = {{'c', 'c', 'c', 'a'}, {'c', 'd', 'c', 'c'}, {'c', 'c', 'e', 'c'}, {'f', 'c', 'c', 'c'}};

        System.out.println("Does the first grid contain a cycle? " + solution.containsCycle(grid1));
        System.out.println();

}
}
//1. The "Immediate Backtrack" ProblemIn a 2D grid, every cell
// is connected to its neighbors in both directions.
// If you move from Cell A to Cell B, Cell B will look at its own neighbors.
// One of Cell B's neighbors is Cell A (the one you just came from).
// Since you just visited Cell A, visited[A] is already true.
// Without the parent check:The code would look back at A, see it is visited,
// and immediately return true. It would think it found a cycle of length 2 (A $\leftrightarrow$ B),
// which isn't a real cycle in this problem.2. How the Parent Check Fixes
// ThisThe parent check (i+1 != pari || j != parj) acts like a "One-Way Sign."
// It tells the DFS:"You can explore any neighbor you want,
// EXCEPT the one that just sent you here."By blocking the immediate path back,
// you force the DFS to find a different way back to an already visited cell.
// If it manages to find a visited cell through a different route,
// it must have traveled in a loop of at least 4 cells (e.g., $A \rightarrow B \rightarrow C \rightarrow D \rightarrow A$).3. Visualizing the Logic