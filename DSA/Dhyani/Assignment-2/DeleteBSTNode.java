import java.util.Scanner;

public class DeleteBSTNode {

	static BSTNode root;

	public static void main(String[] args) {

		int[] array = new int[] { 40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46 };
		Scanner sc = new Scanner(System.in);
		DeleteBSTNode tree = new DeleteBSTNode();
		for (int num : array) {

			tree.insertNode(root, num);

		}
		System.out.println("Inorder traversal after inserting all nodes: ");
		tree.getNodeInorder(root);
		char ans = ' ';
		do {

			System.out.println("\nEnter node to be deleted: ");
			int num = sc.nextInt();

			System.out.println("Inorder traversal after deleting " + num + ":");
			root = tree.deleteNode(root, num);
			tree.getNodeInorder(root);

			System.out.println("\nDo you want to continue deleting? (y/n): ");
			ans = sc.next().charAt(0);
		} while (ans == 'y');

	}

	public BSTNode getPredecessor(BSTNode tmp, int val) {

		if (tmp == null) {

			return tmp;

		}

		if (tmp.val == val) {

			if (tmp.ltChild == null || tmp.rtChild == null) {

				return null;

			} else {

				BSTNode tmpLeft = tmp.ltChild;
				BSTNode tmpRight = tmpLeft.rtChild;

				if (tmpRight != null) {

					while (tmpRight.rtChild != null) {

						tmpRight = tmpRight.rtChild;
					}

					return tmpRight;

				} else {

					// if there is no right child then the left child is returned as the predecessor

					return tmpLeft;

				}

			}

		} else {

			// search for the node to be deleted

			BSTNode node = findNode(tmp, val);
			return getPredecessor(node, val);
		}

	}

	public BSTNode deleteNode(BSTNode tmp, int val) {

		if (tmp == null) {

			return null;

		}

		BSTNode pred = getPredecessor(tmp, val);

		if (val < tmp.val) {

			tmp.ltChild = deleteNode(tmp.ltChild, val);

		} else if (val > tmp.val) {

			tmp.rtChild = deleteNode(tmp.rtChild, val);

		} else {

			// if there are no children for the node that means it is a leaf node and we
			// assign null to its parent node from the recursive call

			if (tmp.ltChild == null && tmp.rtChild == null) {

				return null;

			} else if (tmp.ltChild == null) {

				return tmp.rtChild;

			} else if (tmp.rtChild == null) {

				return tmp.ltChild;

			}

			// replace the deleted node value with predecessor node value

			tmp.val = pred.val;

			// delete predecessor from the tree

			tmp.ltChild = deleteNode(tmp.ltChild, pred.val);

		}

		return tmp;

	}

	public BSTNode findNode(BSTNode tmp, int val) {

		if (tmp != null && tmp.val == val) {
			return tmp;
		}

		return val < tmp.val ? findNode(tmp.ltChild, val) : findNode(tmp.rtChild, val);

	}

	public void insertNode(BSTNode rootNode, int newVal) {

		if (rootNode == null) {

			rootNode = new BSTNode(newVal);
			root = rootNode;
			return;
		}

		while (true) {

			if (rootNode.val > newVal) {

				if (rootNode.ltChild == null) {

					rootNode.ltChild = new BSTNode(newVal);

					return;

				} else {

					rootNode = rootNode.ltChild;

				}
			} else if (rootNode.val < newVal) {

				if (rootNode.rtChild == null) {

					rootNode.rtChild = new BSTNode(newVal);
					return;

				} else {

					rootNode = rootNode.rtChild;

				}
			}
		}
	}

	public void getNodeInorder(BSTNode tmp) {

		if (tmp == null) {
			return;
		}

		getNodeInorder(tmp.ltChild);
		System.out.print(tmp.val + " ");
		getNodeInorder(tmp.rtChild);

	}

}

class BSTNode {
	int val;
	BSTNode ltChild;
	BSTNode rtChild;

	BSTNode() {
	}

	BSTNode(int val) {

		this.val = val;

	}

	BSTNode(int val, BSTNode ltChild, BSTNode rtChild, BSTNode parent) {

		this.val = val;
		this.ltChild = ltChild;
		this.rtChild = rtChild;

	}

}
