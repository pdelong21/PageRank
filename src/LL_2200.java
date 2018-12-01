/* PATRICK DELONG cs435 2200 mp */

// LinkedList implementation for holding data like node, next node, rank, and outdegree of the vertex
public class LL_2200 {
    NodePR_2200 head; // our head node
    float Rank; // Our rank
    int OutDegree = 0; // Our out degree which is counted whenever a node is inserted

    public void insert(float data){
        NodePR_2200 current = new NodePR_2200(); // Allocate()
        current.data = data; // Data is what was passed through
        current.next = null; // Initialize next to null;

        // Case of first object being inserted into the list
        if (head == null){
            head = current;
        }
        // Case where it would not be the first node and we are appending to the end of the list
        else{
            NodePR_2200 temp = head; // This is our temp node for traversing
            // Goto the end of the linked list to insert node, we know if node.next is null then we have reached the end
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = current; // We have reached the end of the linked list and now we insert
        }
        OutDegree++; // Keep track of the out degree every time we insert a node
    }
    // Used for debugging purposes to see if it inserted correctly
    public void PrintList(){
        NodePR_2200 current = head;
        if(current != null) {
            while (current.next != null) {
                System.out.print(current.data);
                System.out.print(" ");
                current = current.next;
            }
            System.out.println(current.data);
        }
    }
}
