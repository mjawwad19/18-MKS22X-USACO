import java.io.*;
import java.util.*;
public class USACO{
  public static int bronze(String fileName) throws FileNotFoundException{
    File text = new File(fileName);
    Scanner inf = new Scanner(text);
    String[] lvls = inf.nextLine().split(" ");
    int R = Integer.parseInt(lvls[0]);
    int C = Integer.parseInt(lvls[1]);
    int SL = Integer.parseInt(lvls[2]);
    int N = Integer.parseInt(lvls[3]);
    int[][] grid = new int[R][C];
    //the first two terms listed are R and C anyway so.

    //fill in the grid
    for (int r = 0; r < R; r++) {
      String[] row = inf.nextLine().split(" ");
      for (int c = 0; c < C; c++) {
        grid[r][c] = Integer.parseInt(row[c]);
      }
    }

    //stomp N times
    for (int i = 0; i < N; i++) { //aka N stomp instructions
      String[] cInp = inf.nextLine().split(" "); //take every input
      stomp(Integer.parseInt(cInp[0]), Integer.parseInt(cInp[1]), Integer.parseInt(cInp[2]), grid); //stomp that current section...
      //System.out.println(showPass(grid));
    }

    //display the final grid
    //System.out.println(checkSea(grid, SL));
    return cVol(grid, SL);
  }

  //at the very end show land and elevation in respect to sea level
  /*private static String checkSea(int[][] g, int E) {
    String out = "";
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        if (g[i][j] >= E) out += "__" + " ";
        else out += " " + (E - g[i][j]) + " ";
      }
      out += '\n';
    }
    return out;
  }
  /*basically a tostring: show each input pass
  private static String showPass(int[][] g) {
    String out = "";
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        out += g[i][j] + " ";
      }
      out += "\n";
    }
    return out;
  }
  */
  private static void stomp(int r, int c, int d, int[][] g) {
    int UL = g[r][c];
    for (int i = r-1; i < r+2; i++) {
      for (int j = c-1; j < c+2; j++) {
        UL = Math.max(UL, g[i][j]); //find the max elevation in the set of 3 by 3;
      }
    }
    int target = UL - d;
    for (int i = r-1; i < r+2; i++) {
      for (int j = c-1; j < c+2; j++) {
        g[i][j] = Math.min(g[i][j], target); //go down to nearest elevation allowed or stomp to target elevation if avail.
      }
    }
  }
  //calculatues volume in cubic inches
  private static int cVol(int[][] g, int E) {
    int agDepth = 0;
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        if (g[i][j] < E) agDepth += E - g[i][j];
      }
    }
    //System.out.println("agDepth = " + agDepth);
    return agDepth * 72 * 72;
  }

  public static int silver(String fileName) throws FileNotFoundException {
    File txt = new File(fileName);
    Scanner inf = new Scanner(txt);
    String[] l = inf.nextLine().split(" ");
    int R = Integer.parseInt(l[0]);
    int C = Integer.parseInt(l[1]);
    int T = Integer.parseInt(l[2]);
    int[][] land = new int[R][C];
    //convert inputted grid to a string[][] array to be used
    for (int r = 0; r < R; r++) {
      String[] row = inf.nextLine().split("");
      for (int c = 0; c < C; c++) {
        System.out.println(Arrays.toString(row));
        if (row[c].equals(".")) land[r][c] = 0;
        else land[r][c] = -1;
      }
    }
    System.out.println(sPrint(land));
    String[] iP = inf.nextLine().split(" "); //the locations!
    int R1 = Integer.parseInt(iP[0]);
    int C1 = Integer.parseInt(iP[1]);
    int R2 = Integer.parseInt(iP[2]);
    int C2 = Integer.parseInt(iP[2]);
    int[] moves = {1, 0, 0, 1, -1, 0, 0, -1}; //up right down left
    int[][] possMoves;
    return -1;
  }

  private static String sPrint(int[][] l) {
    String out = "";
    for (int r = 0; r < l.length; r++) {
      for (int c = 0; c < l[0].length; c++) {
        out += l[r][c] + " ";
      }
      out += "\n";
    }
    return out;
  }
  public static void main(String[] args) {
    try {
      //System.out.println(bronze("makeLake/makeLake.1.in"));
      /*int ans = 0;
      File f = new File("makeLake/makeLake.5.out");
      Scanner i = new Scanner(f);
      ans = Integer.parseInt(i.nextLine());
      //System.out.println(ans);
      if (bronze("makeLake/makeLake.5.in") == ans) {
        System.out.println("Success! Go try the others \n" + ans);
      }
      else System.out.println("failure :/");*/

      silver("ctravel/ctravel.1.in"); //currently just display the board;
    }catch (FileNotFoundException e) {
      System.out.println("enter a file known");
    }
  }
}
