package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-09-06
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
	// tree is empty
	if (node == null) {
	    return 0;
	}
	int leftHeight = nodeHeight(node.getLeft());
	int rightHeight = nodeHeight(node.getRight());

	return leftHeight - rightHeight;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	if (node == null) {
	    return null;
	}

	int balanceVal = balance(node);

	// Left heavy
	if (balanceVal > 1) {
	    if (balance(node.getLeft()) < 0) {
		// Left-Right Case: Rotate left on the left child, then rotate right
		node.setLeft(rotateLeft(node.getLeft()));
	    }
	    // Left-Left Case: Rotate right
	    return rotateRight(node);
	}

	// Right heavy
	if (balanceVal < -1) {
	    if (balance(node.getRight()) > 0) {
		// Right-Left Case: Rotate right on the right child, then rotate left
		node.setRight(rotateRight(node.getRight()));
	    }
	    // Right-Right Case: Rotate left
	    return rotateLeft(node);
	}

	// Update the height for the current node
	node.updateHeight();

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
	// Rearrange the nodes.
	final TreeNode<T> temp = node.getRight();
	node.setRight(temp.getLeft());
	temp.setLeft(node);
	// Update the node heights.
	node.updateHeight();
	temp.updateHeight();

	return temp;

    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// Rearrange the nodes.
	final TreeNode<T> temp = node.getLeft();
	node.setLeft(temp.getRight());
	temp.setRight(node);
	// Update the node heights.
	node.updateHeight();
	// Return new root.
	return temp;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

	if (node == null) {
	    // Base case - add a new node containing the data.
	    node = new TreeNode<T>(data);
	    node.getdata().incrementCount();
	    this.size++;

	} else {
	    // Compare the node data against the insert data.
	    final int result = node.getdata().compareTo(data);

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.insertAux(node.getLeft(), data));
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.insertAux(node.getRight(), data));
	    } else {
		// Base case - data is already in the tree, increment its count
		node.getdata().incrementCount();
	    }
	}
	node.updateHeight();

	return rebalance(node);

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if (node == null) {
	    return true;
	}
	if (minNode != null && node.getdata().compareTo(minNode.getdata()) <= 0) {
	    System.out.println("BST left value violation at value:" + node.getdata());
	    return false;
	}
	if (maxNode != null && node.getdata().compareTo(maxNode.getdata()) >= 0) {
	    System.out.println("BST right value violation at value:" + node.getdata());
	    return false;
	}
	if (node.getHeight() != Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight())) + 1) {
	    System.out.println("BST height value violation at value:" + node.getdata());
	    return false;
	}
	if (Math.abs(balance(node)) > 1) {
	    System.out.println("BST balance violation at value:" + node.getdata());
	    System.out.println(balance(node));
	    return false;
	}
	// recurse
	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);

    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
