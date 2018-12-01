import java.io.*;
import java.math.RoundingMode;

public class PageRank {
    static float d = 0.85f;
    static Float n;
    public static void main(String[] args) {

        int runs = Integer.parseInt(args[0]);
        float initRank =(float)Integer.parseInt(args[1]);
        float fixerrorate = 0.000100f;
        File file = new File(args[2]);

        // Generate the graph from the file
        LL_pgd22[] AdjList = GenerateAdjList(file);
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
        for (int i = 0; i < runs; i++){
            AdjList = Rank(AdjList);
        }

        /*
        int count = 0;
        for (var list:AdjList
             ) {
            System.out.print(count);
            System.out.print(", ");
            System.out.printf(String.format("%.6f", list.Rank));
            System.out.print(" --> ");
            list.PrintList();
            System.out.println();
            count ++;
        }
        */

    }
    private static LL_pgd22[] GenerateAdjList(File file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); // Read line by line
            String[] split_line = line.split(" "); // Split the string by a space,
                                                         // our first occurrence is the size of the NxN graph
            LL_pgd22[] AdjList = new LL_pgd22[Integer.parseInt(split_line[0])];
            // Initialize the linked lists
            for (int i = 0; i < AdjList.length; i++){
                AdjList[i] = new LL_pgd22();
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

    private static LL_pgd22[] Rank(LL_pgd22[] AdjList){
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

    private static float PR(LL_pgd22 list, float v){
        Node_pgd22 current = list.head;
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

}


