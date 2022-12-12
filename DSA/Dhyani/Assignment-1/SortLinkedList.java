public class SortLinkedList {

	Node head;
	static int count = 0;

	public static void main(String args[]) {

		SortLinkedList list = new SortLinkedList();

		list.addNode(6);
		list.addNode(4);
		list.addNode(10);
		list.addNode(1);
		list.addNode(8);
		list.addNode(12);
		list.addNode(11);
		list.addNode(3);
		list.addNode(14);
		list.addNode(2);
		list.addNode(24);
		list.addNode(7);
		list.addNode(11);
		list.addNode(5);
		list.addNode(20);
		System.out.println("Before sorting: ");
		list.getNode();
		list.sort();
		System.out.println("\nAfter sorting the list by swapping nodes using bubble sort: ");
		list.getNode();

	}

	public void addNode(int data) {

		Node node = new Node(data);
		if (head == null) {

			head = node;

		} else {

			Node temp = head;

			while (temp.next != null) {

				temp = temp.next;

			}

			temp.next = node;
		}

	}

	public void getNode() {
		Node temp = head;

		while (temp != null) {
			System.out.print(temp.val+" ");
			temp = temp.next;
			count++;

		}
		System.out.println();
	}

	public void sort() {

		boolean listUpdated;

		do {

			Node prev = null;
			Node curr = head;
			Node nxt = head.next;
			Node temp = null;
			listUpdated = false;

			while (nxt != null) {
				
				// swapping occurs when the current value is greater than the next value in the list
				
				if (curr.val > nxt.val) {

					listUpdated = true;

					if (prev == null) {

						// assigning smaller element as the new head
						
						head = nxt;

					} else {

						prev.next = nxt;

					}

					// swapping the links to swap the nodes 
					
					temp = nxt.next;
					nxt.next = curr;
					curr.next = temp;
					prev = nxt;
					
					// moving ahead in the list
					
					nxt = curr.next;

				} else {

					// as there is no need of swapping we will move forward in the list
					
					prev = curr;
					curr = nxt;
					nxt = nxt.next;

				}

			}

		} while (listUpdated);

	}

}

class Node {

	int val;
	Node next;

	Node() {
	};

	Node(int val) {
		
		this.val = val;
	
	}

	Node(int val, Node next) {
		
		this.val = val;
		this.next = next;
	
	}

}
