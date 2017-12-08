/*
ID:touchxy1
LANG:JAVA
TASK:wormhole
*/
import java.io.*;
import java.util.PriorityQueue;


public class wormhole
{
	private static int MAX_N = 12;

	int N;
	int[] X = new int[MAX_N + 1];
	int[] Y = new int[MAX_N + 1];
	int[] partner = new int[MAX_N + 1];
	int[] next_on_right = new int[MAX_N + 1];

	public wormhole(int NIn, int[] XIn, int[] YIn)
	{
		N = NIn;

		for (int i = 0; i < N && i < MAX_N + 1; i++)
		{
			X[i + 1] = XIn[i];
			Y[i + 1] = YIn[i];
		}
		//System.out.println(N);

		// find right point
		for (int i=1; i<=N; i++) // set next_on_right[i]...  
        	for (int j=1; j<=N; j++)  
	            if (X[j] > X[i] && Y[i] == Y[j]) // j right of i...  
	                if (next_on_right[i] == 0 ||  
	                    X[j]-X[i] < X[next_on_right[i]]-X[i])  
	                    next_on_right[i] = j;
	}

	boolean cycle_exists()
	{
		for (int start = 1; start <= N; start++) 
		{
			int pos = start;
			for (int count = 0; count < N; ++count) 
			{
				pos = next_on_right[partner[pos]];	
			}
			if (pos != 0) return true;
		}
		return false;
	}

	// count all solutions
	int solve()
	{
		// find first unpaired wormhole
		int i = 0;
		int total = 0;
		for (i = 1; i <= N; ++i)
		{
			if (partner[i] == 0)
				break;
		}

		// everyone paired?
		if (i > N)
		{
			if (cycle_exists())
				return 1;
			else
				return 0;
		}

		// try pairing i with all possible other wormholes
		for (int j = i + 1; j <= N; ++j) 
		{
			if (partner[j] == 0)
			{
				// try pairing i & j
				partner[i] = j;
				partner[j] = i;
				total += solve();
				partner[i] = 0;
				partner[j] = 0;
			}	
		}
		return total;
	}
	public static void main(String[] args) throws IOException
	{
		int N = 4;
		int MAX_N = 12;
		int[] X = new int[MAX_N + 1];
		int[] Y = new int[MAX_N + 1];

		BufferedReader fin = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		String tempStr = fin.readLine();

		N = Integer.parseInt(tempStr);
		for (int i = 0; i < N; ++i)
		{
			String tempStr1 = fin.readLine();
			String[] arr = tempStr1.split("\\s");
			int x = Integer.parseInt(arr[0]);
			int y = Integer.parseInt(arr[1]);	
			X[i] = x;
			Y[i] = y;
		}

		wormhole wh = new wormhole(N, X, Y);
		int result = wh.solve();
		//System.out.println(result);
		fout.println(result);
		fin.close();
		fout.close();
	}
}