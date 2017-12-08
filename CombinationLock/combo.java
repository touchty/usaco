/*
ID:touchxy1
LANG:JAVA
TASK:combo
*/
import java.io.*;
import java.util.PriorityQueue;

class combo
{
    public static void main (String [] args) throws IOException
    {
        /*int N = 50;
        int[] user = {1, 2, 3};
        int[] master = {5, 6, 7};*/

        int N = 0;
        int[] user = new int[3];
        int[] master = new int[3];

        BufferedReader fin = new BufferedReader(new FileReader("combo.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        String tempStr = fin.readLine();

        N = Integer.parseInt(tempStr);
        if (N <= 3)
        {

            if (N == 1)fout.println(1);
            else if (N == 2)fout.println(8);
            else if (N == 3)fout.println(27);
            fin.close();
            fout.close();
            return;
        }
        // Build user[]
        tempStr = fin.readLine();
        String[] strArray = tempStr.split("\\s");
        assert (strArray.length == 3);
        for (int i = 0; i < 3 ; i++)
        {
            user[i] = Integer.parseInt(strArray[i]);
        }

        // Build master[]
        tempStr = fin.readLine();
        strArray = tempStr.split("\\s");
        assert (strArray.length == 3);
        for (int i = 0; i < 3 ; i++)
        {
            master[i] = Integer.parseInt(strArray[i]);
        }




        int[] number = new int[N + 5];  // 0 1 ... N 1 2 3 4
        int[][] candidateDigits = new int[3][5];
        /*PriorityQueue<String> pq = new PriorityQueue<>();*/
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // construct number
        for (int i = 0; i < N + 5; ++i)
            number[i] = i;
        for (int i = N + 1; i < N + 5; ++i)
            number[i] = i - N;
        /*for (int i = 0; i < number.length; ++i)
            System.out.println(number[i]);*/

        // for user
        for (int i = 0; i< 3;i++)
        {
            int pos = user[i];
            if (user[i] == 1 || user[i] == 2)
            {
               pos = pos + N;
            }

            for (int j = 0; j < 5; j++)
            {
                candidateDigits[i][j] = number[pos - 2 + j];
            }

        }

        //Insert the candidate into pq
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                {
                    /*String tempStr = String.valueOf(candidateDigits[0][i]) +
                                     String.valueOf(candidateDigits[1][j]) +
                                     String.valueOf(candidateDigits[2][k]);
                        if (!pq.contains(tempN))
                        pq.add(tempStr);*/
                    int tempN = candidateDigits[0][i] * 10000 +
                            candidateDigits[1][j] * 100 +
                            candidateDigits[2][k];
                    if (!pq.contains(tempN))
                        pq.add(tempN);
                }

        // for master
        for (int i = 0; i< 3;i++)
        {
            int pos = master[i];
            if (master[i] == 1 || master[i] == 2)
            {
                pos = pos + N;
            }

            for (int j = 0; j < 5; j++)
            {
                candidateDigits[i][j] = number[pos - 2 + j];
            }

        }

        //Insert the candidate into pq
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                {
                    /*String tempStr = String.valueOf(candidateDigits[0][i]) +
                            String.valueOf(candidateDigits[1][j]) +
                            String.valueOf(candidateDigits[2][k]);
                    if (!pq.contains(tempStr))
                        pq.add(tempStr);*/
                    int tempN = candidateDigits[0][i] * 10000 +
                            candidateDigits[1][j] * 100 +
                            candidateDigits[2][k];
                    if (!pq.contains(tempN))
                        pq.add(tempN);
                }

        fout.println(pq.size());
        fin.close();
        fout.close();
    }
}