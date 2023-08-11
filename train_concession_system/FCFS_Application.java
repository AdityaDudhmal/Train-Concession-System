package train_concession_system;

// Class implements First Come First Serve principle
public class FCFS_Application {
    Node front, rear;
    static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Function to push an element into the queue.
    void add_application_to_queue(int registration_number_of_applicant) {

        Node newNode = new Node(registration_number_of_applicant);

        // if queue is empty
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }

        this.rear.next = newNode;
        this.rear = newNode;

    }

    // Function to pop front element from the queue.
    int remove_first_application_from_queue() {
        // if queue is empty
        if (this.front == null) {
            return -1;
        }

        Node currHead = front;

        front = front.next;

        // Check if front has become null again
        if (front == null) {
            this.rear = null;
        }

        currHead.next = null;

        return currHead.data;
    }

}
