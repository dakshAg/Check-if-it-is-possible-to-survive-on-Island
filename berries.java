import java.util.*;
import java.io.*;
class Main{
    int minus_infinite = -10000;
    public static void main(String[] args) throws IOException{
        Main m = new Main();
        m.start();
    }

    public void start() throws IOException{
        Forest forest = new Forest();
        readInput(forest);
        int ans = best_path_from(0,0,forest);
        if(ans<=minus_infinite){
            System.out.println("NO");
        }else{
            System.out.println("YES");
            System.out.println(String.valueOf(ans));
        }
    }

    int[][] dp;//For DP
    boolean[][] com;
    //Recursive Function....calculates the min path from each point
    public int best_path_from(int x, int y,Forest forest){//O(m * n^2). Lord! Forgive me for not reaching n!m!
        if(com[x][y]!=true){//boolean Reducing 1-2 extra computations where actual path = 0
            int result;
            boolean safe = false;
            for(int i = 0;i<forest.m;i++){//Finds if the spot is safe
                Charm c = forest.charms[i];
                if(c.safe(x,y)){
                    safe = true;
                }
            }
            if(safe){
                if(x==forest.n-1 && y==forest.n-1){//if we reached the destination
                    result = forest.grid[x][y];
                }else if(x==forest.n-1){//If we are in the last row of X-axis
                    result = forest.grid[x][y]+best_path_from(x,y+1,forest);
                }else if(y==forest.n-1){//If we are in the last row of Y-axis
                    result = forest.grid[x][y]+best_path_from(x+1,y,forest);
                }else{
                    result = forest.grid[x][y]+max(best_path_from(x+1,y,forest),best_path_from(x,y+1,forest));
                }
            }else{//If the point is unsafe
                result = minus_infinite;
            }
            dp[x][y] = result;
            com[x][y] = true;
            return result;
        }else{
            return dp[x][y];
        }
    }

    public int max(int x,int y){//Finds the max among 2 values...obvious
        if(x>y){
            return x;
        }else{
            return y;
        }
    }
    public void readInput(Forest forest) throws IOException{//O(n+m) in reading inputs....horrendous
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        forest.n = Integer.parseInt(line1[0]);
        forest.m = Integer.parseInt(line1[1]);
        forest.charms = new Charm[forest.m];
        forest.grid = new int[forest.n][forest.n];
        dp = new int[forest.n][forest.n];
        com = new boolean[forest.n][forest.n];
        for(int i = 0;i<forest.n;i++){
            //Just converting String[] to int[]
            forest.grid[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i = 0;i<forest.m;i++){
            Charm c = new Charm(br.readLine().split(" "));
            forest.charms[i] = c;
        }
    }

    class Forest{
        int[][] grid;
        int n;
        int m;
        Charm[] charms;
    }
    class Charm{
        int i;
        int j;
        int power;

        public boolean safe(int id,int jd){//Safety ensurer
            //|i - i'| + |j - j'| ≤ k
            int a = Math.abs(i-id);
            int b = Math.abs(j-jd);
            if(a+b<=power){
                return true;
            }else{
                return false;
            }
        }

        public Charm(String[] input){
            i = Integer.parseInt(input[0])-1;//ZERO INDEXING
            j = Integer.parseInt(input[1])-1;//ZERO INDEXING
            power = Integer.parseInt(input[2]);
        }
    }
}
/*
Wrong Answer

Sub-Task	Task #	Result
(time)
1	0	AC
(0.120000)
1	1	AC
(0.120000)
1	2	AC
(0.150000)
1	3	WA
(0.200000)
1	4	AC
(0.350000)
Subtask Score: 0.00%	Result - WA
2	5	AC
(1.010000)
2	6	AC
(0.370000)
2	7	TLE
(2.010000)
2	8	TLE
(2.010000)
Subtask Score: 0.00%	Result - TLE
Total Score = 0.00%
*/
