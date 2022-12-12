package com.function.sort;

public class LinkedList {

    Node head;

    static class Node {
        int data;
        Node next = null;

        Node(int d){
            data = d;
        }
    }

    public LinkedList insert(LinkedList list, int data)
    {
        Node newNode = new Node(data);
        Node temp = list.head;

        if(temp == null){
            list.head = newNode;
            return list;
        }

        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = newNode;

        return list;
    }

    public static LinkedList selectionSort(LinkedList list)
    {
        System.out.println("Sorting linked list using selection sort(by swapping nodes)....");

        Node temp, present, presentP, small, smallP;

        present = list.head;

        presentP = smallP = null;

        // while present is not the last node
        while(present.next!=null){
            temp = present;
            small = present;
            // while temp is not the last node
            while(temp.next != null){
                // Get the smallest number in the list
                if(small.data > temp.next.data) {
                    // Get the previous node of the smallest number
                    smallP = temp;
                    small = temp.next;
                }
                temp = temp.next;
            }
            // If the smallest is not same as the present node
            if(small != present){
                // Check if the present is not the head
                if(presentP != null){
                    // Link the small node to present's previous
                    presentP.next = small;
                } else {
                    // If present node is head then change head
                    list.head = small;
                }
                // If the small node is adjacent to present node
                if(present.next == small){
                    present.next = small.next;
                    small.next = present;
                } else {
                    // If the nodes are not adjacent
                    temp = present.next;
                    present.next = small.next;;
                    small.next = temp;
                    smallP.next = present;
                }
            }

            presentP = small;
            present = presentP.next;
//            print(list);

        }
        return list;
    }

    public static void print(LinkedList list)
    {
        Node temp = list.head;

        if(temp == null){
            System.out.print("Empty list");
            return;
        }

        while(temp.next != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println(temp.data);

    }

    public static void main(String args[]){
        LinkedList input = new LinkedList();

        input = input.insert(input, 3);
        input = input.insert(input, 11);
        input = input.insert(input, 20);
        input = input.insert(input, 4);
        input = input.insert(input, 2);
        input = input.insert(input, 7);
        input = input.insert(input, 8);
        input = input.insert(input, 5);
        input = input.insert(input, 18);
        input = input.insert(input, 17);
        input = input.insert(input, 10);
        input = input.insert(input, 9);
        input = input.insert(input, 14);
        input = input.insert(input, 16);
        input = input.insert(input, 1);
        System.out.println("Given list:");
        print(input);
        input = selectionSort(input);
        System.out.println("Sorted list:");
        print(input);
    }
}
