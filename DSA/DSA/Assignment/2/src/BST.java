import java.util.Arrays;

public class BST {

    Node root;

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int value){
        left = null;
        right = null;
        data = value;
    }
    }

    public BST() {
        root = null;
    }

    public static int getPredecessor(Node root){

        int predecessor = root.left.data;

        Node temp = root.left;
        // Check if right node is null
        if(temp.right!=null){
            Node parentPredecessor = temp;

            while(temp.right.right != null) {
                parentPredecessor = temp.right;
                temp = temp.right;
            }
            //Found predecessor, replace the value with deleteNode
            predecessor = parentPredecessor.right.data;
            //Delete the predecessor from tree
            parentPredecessor.right = parentPredecessor.right.left;

        } else {
            //Delete the predecessor from tree
            root.left = temp.left;
        }
        return predecessor;

    }
    public static Node deleteNode(Node root, int input){
        if(root == null)
            return null;

        if(root.data > input){
            root.left = deleteNode(root.left, input);
        } else if(root.data < input){
            root.right = deleteNode(root.right, input);
        } else {
            if(root.left == null)
                return root.right;
            if(root.right == null)
                return root.left;
            // If both child are not null, find the predecessor and delete the predecessor node
            root.data = getPredecessor(root);
        }
        return root;
    }


    public static Node insertNode(Node root, int input){
        if(root == null){
            root = new Node(input);
            return root;
        }

        if(root.data > input){
            if(root.left != null){
                insertNode(root.left, input);
            } else {
                root.left = new Node(input);
            }
        } else {
            if(root.right != null){
                insertNode(root.right, input);
            } else {
                root.right = new Node(input);
            }
        }

        return root;

    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static void main(String args[]){
        BST tree = new BST();
        int[] input = {40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46};
        //Insert nodes to tree
        for (int j : input) {
            tree.root = insertNode(tree.root, j);
        }
        System.out.println("Input array to form binary search tree(BST): "+Arrays.toString(input));

        //Inorder call, send root node
        System.out.print("Inorder traversal of the BST: ");
        inorder(tree.root);
        System.out.println();
        System.out.println("Delete 40 from BST");
        tree.root = deleteNode(tree.root, 40);
        System.out.print("Inorder traversal of the BST: ");
        inorder(tree.root);
        System.out.println();
        System.out.println("Delete 20 from BST");
        tree.root = deleteNode(tree.root, 20);
        System.out.print("Inorder traversal of the BST: ");
        inorder(tree.root);
        System.out.println();
    }
}
