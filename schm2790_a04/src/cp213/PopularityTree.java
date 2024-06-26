package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-09-06
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {

	this.root = this.retrieveAux(this.root, key);

	if (key.getCount() == 0) {
	    key = null;
	} else {
	    key = new CountedItem<T>(key);
	}
	return key;

    }

    /**
     * Auxiliary method for retrieve. May force node rotation if the retrieval count
     * of the located node item is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The item to search for. Count is updated to count in matching
     *             node item if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

	if (node == null) {

	} else {
	    // Compare the node data against the retrieve data.
	    final int result = node.getdata().compareTo(key);
	    this.comparisons++;

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.retrieveAux(node.getLeft(), key));

		if (node.getLeft() != null && node.getdata().getCount() < node.getLeft().getdata().getCount()) {
		    node = this.rotateRight(node);
		}
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.retrieveAux(node.getRight(), key));

		if (node.getRight() != null && node.getdata().getCount() < node.getRight().getdata().getCount()) {
		    node = this.rotateLeft(node);
		}
	    } else {
		// Base case - data is in the Popularity Tree.
		node.getdata().incrementCount();
		key.setCount(node.getdata().getCount());
	    }
	}
	node.updateHeight();
	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	// Rearrange the nodes.
	final TreeNode<T> temp = parent.getRight();
	parent.setRight(temp.getLeft());
	temp.setLeft(parent);
	// Update the node heights.
	parent.updateHeight();
	temp.updateHeight();
	// Return new root.
	return temp;

    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	// Rearrange the nodes.
	final TreeNode<T> temp = parent.getLeft();

	// complete code here
	parent.setLeft(temp.getRight());
	temp.setRight(parent);

	parent.updateHeight();
	temp.updateHeight();
	return temp;

    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

	if (node == null) {
	    // Base case - add a new node containing the data.

	    // complete code here
	    node = new TreeNode<T>(data);

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
	    }
	}
	node.updateHeight();
	return node;

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
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
	if (node.getLeft() != null && node.getdata().getCount() < node.getLeft().getdata().getCount()
		|| node.getRight() != null && node.getdata().getCount() < node.getRight().getdata().getCount()) {
	    System.out.println("BST count violation:" + node.getdata());

	    return false;
	}
	// recurse
	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);

    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, item, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

}
