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
    for (int r = 0; r < R; r++) {
      String[] row = inf.nextLine().split(" ");
      for (int c = 0; c < C; c++) {
        grid[r][c] = Integer.parseInt(row[c]);
      }
    }

    for (int i = 0; i < N; i++) { //aka N stomp instructions
      String[] cInp = inf.nextLine().split(" "); //take every input
      stomp(Integer.parseInt(cInp[0]), Integer.parseInt(cInp[1]), Integer.parseInt(cInp[2]), grid); //stomp that current section...
      System.out.println(checkSea(grid, SL));
    }
    //System.out.println(checkSea(grid, Integer.parseInt(lvls[2]))); //lvl[2] is E
    return -1;
  }

  private static String checkSea(int[][] g, int E) {
    String out = "";
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        /*if (g[i][j] >= E) out += "__" + " ";
        else out += " " + Math.abs(E - g[i][j]) + " ";*/
        out += g[i][j] + " ";;
      }
      out += '\n';
    }
    return out;
  }
  private static void stomp(int r, int c, int d, int[][] g) {
    int max = 0;
    int target = 0;
    ArrayList<Integer> e = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int nR = r + i;
        int nC = c + i;
        if (nR < g.length && nC < g[0].length) {
          e.add(g[nR][nC]);
        }
      }
    }
    target = Collections.max(e) - d;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int nR = r + i;
        int nC = c + i;
        if (nR < g.length && nC < g[0].length) {
        g[nR][nC] = Math.min(g[nR][nC], target);
        //go to the nearest low... or target
      }
      }
    }
  }

  public static int silver(String fileName) {
    return -1;
  }

  public static void main(String[] args) {
    try {
      bronze("makeLake/makeLake.1.in");
    }catch (FileNotFoundException e) {
      System.out.println("enter a file known");
    }
  }
}
