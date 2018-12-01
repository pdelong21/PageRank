/* PATRICK DELONG cs435 2200 mp */

public class LL_2200 {
    Node_2200 head; // our head node
    float Rank;
    int OutDegree = 0;

    public void insert(float data){
        Node_2200 current = new Node_2200(); // Allocate()
        current.data = data; // Data is what was passed through
        current.next = null; // Initialize next to null;

        // This would be our first object being inserted into the list
        if (head == null){
            head = current;
        }
        // This would not be the first node and we are appending to the end of the list
        else{
            Node_2200 temp = head; // This is our temp node for traversing
            // Goto the end of the linked list to insert node
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = current; // We have reached the end of the linked list and now we insert
        }
        OutDegree++; // Keep track of the out degree every time we insert a node
    }

    public void PrintList(){
        Node_2200 current = head;
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
