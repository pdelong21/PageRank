/* PATRICK DELONG cs435 2200 mp */
import java.io.*;

public class PageRank_2200 {
    static float d = 0.85f;
    static Float n;
    public static void main(String[] args) {

        int runs = Integer.parseInt(args[0]);
        float initRank =(float)Integer.parseInt(args[1]);
        float fixerrorate = 0.000100f;
        File file = new File(args[2]);

        // Generate the graph from the file
        LL_2200[] AdjList = GenerateAdjList(file);
        n =(float)AdjList.length;

        // CHECK THE INITIAL RANK
        // if initial rank is 0, initialize each list with a rank of 0
        if (initRank == 0){
            for (var list: AdjList
                 ) {
                list.Rank = 0;
            }
        }
        // if initial rank is 1, initialize each list with a rank of 1
        else if (initRank == 1){
            for (var list: AdjList
            ) {
                list.Rank = 1;
            }
        }
        // if initial rank is -1, initialize each list with a rank of 1/n where n is the amount of edges
        else if (initRank == -1) {
            for (var list : AdjList
            ) {
                list.Rank = 1f / (float)AdjList.length;
            }
        }
        else if (initRank == -2) {
            for (var list : AdjList
            ) {
                list.Rank = 1f / (float)Math.sqrt(AdjList.length);
            }
        }

        // If runs is more than 0 i.e fixed then run that many times
        if(runs > 0){
            for (int i = 0; i < runs; i++){
                if(i==0){
                    System.out.print("Base\t:\t" + i + "\t:\t");
                    PrintIteration(AdjList);
                    continue;
                }

                AdjList = Rank(AdjList);
                System.out.print("Iter\t:\t" + i + "\t:\t");
                PrintIteration(AdjList);
            }
        }
        // If runs == 0, then run until EACH vertex hits the fixed errorate
        else if(runs == 0){
            // Run until the difference between the last iteration and the current iteration is less than the errorate
            int iter = 0;
            while(true){
                if(iter==0){
                    System.out.print("Base\t:\t" + iter + "\t:\t");
                    PrintIteration(AdjList);
                }
                iter++;
                int count = 0;
                float[] prev = new float[AdjList.length];
                for (int i = 0; i < AdjList.length; i++) {
                    prev[i] = AdjList[i].Rank;
                }
                AdjList = Rank(AdjList);
                System.out.print("Iter\t:\t" + iter + "\t:\t");
                PrintIteration(AdjList);
                for (int i = 0; i < AdjList.length; i++) {
                    if((prev[i]-AdjList[i].Rank) < fixerrorate){
                        count ++;
                    } else break;
                }
                if (count == AdjList.length){
                    break;
                }
            }
        }
    }
    private static LL_2200[] GenerateAdjList(File file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); // Read line by line
            String[] split_line = line.split(" "); // Split the string by a space,
                                                         // our first occurrence is the size of the NxN graph
            LL_2200[] AdjList = new LL_2200[Integer.parseInt(split_line[0])];
            // Initialize the linked lists
            for (int i = 0; i < AdjList.length; i++){
                AdjList[i] = new LL_2200();
            }
            while((line = reader.readLine()) != null){
                split_line = line.split(" ");
                // We know split line only can have 2 values, index 0 is from-node, index 1 is to-node
                int from = Integer.parseInt(split_line[0]);
                int to = Integer.parseInt(split_line[1]);
                AdjList[from].insert((float)to);

            }
            reader.close();
            return AdjList;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static LL_2200[] Rank(LL_2200[] AdjList){
        float[] listRanks = new float[AdjList.length];
        // For every vertex
        for (int v = 0; v < AdjList.length; v++){
            // See who we points to me
            for (int p = 0; p < AdjList.length; p++){
                // if we are at self, don't PR
                if(p == v){
                    continue;
                }
                listRanks[v] += PR(AdjList[p], (float)v);

            }
        }
        for (int i = 0; i<AdjList.length; i++){
            AdjList[i].Rank = listRanks[i];
        }
        return AdjList;
    }

    private static float PR(LL_2200 list, float v){
        Node_2200 current = list.head;
        float rank = 0f;
        while (current != null){
            if (current.data == v){
                rank = ((1-d)/n)+(d*(list.Rank/list.OutDegree));
                return rank;
            }
            else {
                current = current.next;
            }
        }
        return rank;
    }

    private static void PrintIteration(LL_2200[] AdjList){
        for (int i = 0; i < AdjList.length; i++){
            System.out.print("P[ " + i + " ]=" + String.format("%.6f", AdjList[i].Rank) + "\t");
        }
        System.out.println();

    }

}


