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
      stomp(Integer.parseInt(cInp[0]), Integer.parseInt(cInp[1]), Integer.parseInt(cInp[2]), grid);
    }
    return cVol(grid, SL);
  }

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
        if (row[c].equals(".")) land[r][c] = 0;
        else land[r][c] = -1;
      }
    }

    String[] iP = inf.nextLine().split(" "); //the locations!
    int R1 = Integer.parseInt(iP[0]) -1;
    int C1 = Integer.parseInt(iP[1]) - 1;
    land[R1][C1] = 1;
    int R2 = Integer.parseInt(iP[2]) - 1;
    int C2 = Integer.parseInt(iP[3]) - 1;
    for (int t = 0; t < T; t++) {
      land = move(land);
      System.out.println(sPrint(land));
    }
    return land[R2][C2];
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
  private static boolean runOff(int r, int c, int[][] l) {
    return (r< 0 || c < 0 ||
            r >= l.length || c >= l[0].length);

  }
  private static int[][] move(int[][] l) {
    int[] moves = {1, 0, 0, 1, -1, 0, 0, -1}; //up right down left
    int[][] possMoves = new int[l.length][l[0].length];
    for (int i = 0; i < possMoves.length; i++) {
      for (int j = 0; j < possMoves[0].length; j++) {
        if (l[i][j] == -1) possMoves[i][j] = -1;
        else {
          int currMP = 0;
          for (int m = 0; m < 8; m+=2) {
            int nR = i + moves[m];
            int nC = j + moves[m+1];
            if (!runOff(nR, nC, l) && l[nR][nC] != -1) {
              currMP += l[nR][nC]; //this is all the same optimization stuff from Knights as suggested by K
            }
          }
          possMoves[i][j] = currMP;
          //System.out.println(currMP);
        }
      }
    }
    return possMoves;
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

      System.out.println(silver("ctravel/ctravel.5.in"));
    }catch (FileNotFoundException e) {
      System.out.println("enter a file known");
    }
  }
}
