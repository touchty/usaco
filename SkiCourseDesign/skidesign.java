/*
ID:touchxy1
LANG:JAVA
TASK:skidesign
*/

import java.io.*;

public class skidesign
{
    static int MAXN = 1000;
    private int[] hills = new int[MAXN];  // hills' elevation
    private int N;  // number of all the hills

    public skidesign(int len, int[] elevations)
    {
        N = len;
        for (int i = 0; i < N; i++)
        {
            hills[i] = elevations[i];
        }
    }

    /*0-17; 1-18; 2-19; ... ; 83-100*/
    long minCost()
    {
        int LOW = 0;
        int High = 83;
        long min = Long.MAX_VALUE;
        for (int i = LOW; i <= High ; i++)
        {
            long tempCost = 0L;
            for (int j = 0; j < N; j++)
            {
                int x = 0;
                if (hills[j] < i)
                    x = i - hills[j];
                else if (hills[j] > i + 17)
                    x = hills[j] - (i + 17);
                tempCost += x*x;
            }

            min = Long.min(min, tempCost);
        }
        return min;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader fin = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        String tempStr = fin.readLine();
        int N = Integer.parseInt(tempStr);
        int[] elevationArr = new int[skidesign.MAXN];
        for (int i = 0; i < N; i++)
        {
            String elevationStr = fin.readLine();
            elevationArr[i] = Integer.parseInt(elevationStr);
        }

        skidesign sd = new skidesign(N, elevationArr);
        long result = sd.minCost();
        fout.println(result);

        fin.close();
        fout.close();

    }

}
