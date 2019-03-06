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
      //int rowNum = r + 1;
      //System.out.print("row " + rowNum + ": " + Arrays.toString(grid[r]) + "\n");
    }
    for (int i = 0; i < Integer.parseInt(lvls[3]); i++) { //aka N stomp instructions
      String[] cInp = inf.nextLine().split(" "); //take every input
      stomp(Integer.parseInt(cInp[0]), Integer.parseInt(cInp[1]), Integer.parseInt(cInp[2])); //stomp that current section...
    }
    System.out.println(checkSea(grid, Integer.parseInt(lvls[2]))); //lvl[2] is E
    return -1;
  }

  private static String checkSea(int[][] g, int E) {
    String out = "";
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        if (g[i][j] >= E) out += ".." + " ";
        else out += " " + Math.abs(E - g[i][j]) + " ";
      }
      out += '\n';
    }
    return out;
  }
  private static void stomp(int r, int c, int ele) {


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
