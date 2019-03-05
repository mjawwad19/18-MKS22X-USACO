import java.io.*;
import java.util.*;
public class USACO{
  public static int bronze(String fileName) throws FileNotFoundException{
    File text = new File(fileName);
    Scanner inf = new Scanner(text);
    String[] lvls = inf.nextLine().split(" ");
    int[][] grid = new int[Integer.parseInt(lvls[0])][Integer.parseInt(lvls[1])];
    //the first two terms listed are R and C anyway so.
    for (int r = 0; r < grid.length; r++) {
      String[] row = inf.nextLine().split(" ");
      for (int c = 0; c < grid[0].length; c++) {
        grid[r][c] = Integer.parseInt(row[c]);
      }
      int rowNum = r + 1;
      System.out.print("row " + rowNum + ": " + Arrays.toString(grid[r]) + "\n");
    }
    //System.out.println(Arrays.toString(grid));
    return -1;
  }
  public static int silver(String fileName) {
    return -1;
  }

  public static void main(String[] args) {
    try {
      bronze("makelake.1.in");
    }catch (FileNotFoundException e) {
      System.out.println("enter a file known");
    }
  }
}
