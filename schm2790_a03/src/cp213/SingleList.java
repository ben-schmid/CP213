package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author your name, id, email here
 * @version 2023-09-06
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */

    private SingleNode<T> linearSearch(final T key) {

	SingleNode<T> prev = null;
	SingleNode<T> current = this.front;

	// Iterate through the list
	while (current != null) {
	    // Check if the current node's item matches the key
	    if (current.getItem().equals(key)) {
		return prev; // Return the previous node
	    }
	    // Update prev and current for next iteration
	    prev = current;
	    current = current.getNext();
	}

	return null; // Key not found
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The value to append.
     */
    public void append(final T data) {

	SingleNode<T> node = new SingleNode<T>(data, null);

	if (this.front == null) {
	    this.front = this.rear = node;
	} else {
	    this.rear.setNext(node);
	    this.rear = node;
	}

	this.length++;

	return;

    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {

//// key_node = self._front
//
//        while key_node is not None:
//            previous = key_node
//            current = key_node._next
//
//            while current is not None:
//                if current._value == key_node._value:
//                    self._count -= 1
//                    previous._next = current._next
//                else:
//                    previous = current
//                current = current._next
//            self._rear = previous
//            key_node = key_node._next
//
//        return

	SingleNode<T> key = this.front;

	while (key != null) {
	    SingleNode<T> prev = key;
	    SingleNode<T> curr = key.getNext();
	    while (curr != null) {
		if (curr.getItem().equals(key.getItem())) {
		    this.length--;
		    prev.setNext(curr.getNext());

		} else {
		    prev = curr;
		}
		curr = curr.getNext();
	    }
	    // move to the next key node
	    key = key.getNext();

	    // update rear if we are on last node
	    if (key == null) {
		this.rear = prev;
	    }

	}

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while (left.front != null && right.front != null) {
	    this.moveFrontToRear(left);
	    if (left.front != null) { // Check if left list still has elements
		this.moveFrontToRear(right);
	    }
	}

	// If any list still has nodes, move them to this list.
	while (left.front != null) {
	    this.moveFrontToRear(left);
	}
	while (right.front != null) {
	    this.moveFrontToRear(right);
	}
	return;

    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
	SingleNode<T> current = this.front;
	while (current != null) {
	    if (current.getItem().equals(key)) {
		return true;
	    }
	    current = current.getNext();
	}

	return false;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	SingleNode<T> node = this.front;
	int count = 0;

	while (node != null) {
	    if (node.getItem().equals(key)) {
		count++;
	    }
	    node = node.getNext();
	}

	return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {
	SingleNode<T> node = linearSearch(key);
	if (node != null) {
	    return node.getNext().getItem();
	}

	return null;
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	if (n < 0 || n >= this.length) {
	    throw new ArrayIndexOutOfBoundsException("Index" + n + " is out of bounds for legnth" + this.length);
	}

	SingleNode<T> node = this.front;

	for (int i = 0; i < n; i++) {
	    node = node.getNext();

	}

	return node.getItem();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {

	SingleNode<T> thisNode = this.front;
	SingleNode<T> sourceNode = source.front;

	// check if lengths are same
	if (this.length != source.length) {
	    return false;
	}
	while (thisNode != null) {
	    if (!thisNode.getItem().equals(sourceNode.getItem())) {
		return false;
	    }
	    thisNode = thisNode.getNext();
	    sourceNode = sourceNode.getNext();
	}

	return true;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	int index = 0;
	SingleNode<T> node = this.front;

	while (node != null) {
	    if (node.getItem().equals((key))) {
		return index;
	    }
	    index++;
	    node = node.getNext();

	}
	// node is null, therefore index is -1
	return -1;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i    The index to insert the new data at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	SingleNode<T> newNode = new SingleNode<T>(data, null);

	if (this.front == null || i <= 0) {
	    newNode.setNext(this.front);
	    this.front = newNode;
	    if (this.rear == null) {
		this.rear = newNode;
	    }

	} else {
	    SingleNode<T> curr = this.front;
	    for (int j = 1; j < i && curr != null; j++) {
		curr = curr.getNext();
	    }
	    if (curr.getNext() == null) {
		this.rear = newNode;

	    }
	    newNode.setNext(curr.getNext());
	    curr.setNext(newNode);

	}
	this.length++;

	return;

    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	// clear this list
	this.front = null;
	this.rear = null;
	this.length = 0;

	SingleNode<T> leftNode = left.front;
	while (leftNode != null) {
	    if (right.contains(leftNode.getItem())) {
		this.append(leftNode.getItem());
	    }
	    leftNode = leftNode.getNext();

	}
	return;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {

	if (this.front == null) {
	    return null;
	}

	SingleNode<T> curr = this.front.getNext();
	T maxItem = this.front.getItem();

	while (curr != null) {
	    if (curr.getItem().compareTo(maxItem) > 0) {
		maxItem = curr.getItem();
	    }
	    curr = curr.getNext();

	}
	return maxItem;

    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {

	if (this.front == null) {
	    return null;
	}

	SingleNode<T> curr = this.front.getNext();
	T minItem = this.front.getItem();

	while (curr != null) {
	    if (curr.getItem().compareTo(minItem) < 0) {
		minItem = curr.getItem();
	    }
	    curr = curr.getNext();

	}
	return minItem;

    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {

	SingleNode<T> node = new SingleNode<T>(data, null);

	if (this.front == null) {
	    this.front = this.rear = node;
	} else {
	    node.setNext(this.front);
	    this.front = node;
	}

	this.length++;

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {

	if (this.front == null) {
	    // list is empty, nothing to remove
	    return null;
	}
	// front node = key
	if (this.front.getItem().equals(key)) {
	    T item = this.front.getItem();
	    this.front = this.front.getNext();
	    // list is empty, set rear to null
	    if (this.front == null) {
		this.rear = null;
	    }
	    this.length--;
	    return item;
	}
	SingleNode<T> prev = this.linearSearch(key);
	if (prev == null || prev.getNext() == null) {
	    return null; // key not found return null
	}

	// remove node
	SingleNode<T> nodeToRemove = prev.getNext();
	T item = nodeToRemove.getItem();
	prev.setNext(nodeToRemove.getNext());

	if (nodeToRemove == this.rear) {
	    // last node removed, set rear to prev
	    this.rear = prev;
	}
	this.length--;

	return item;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {

	if (this.front == null) {
	    // list is empty, nothing to remove
	    return null;
	}

	T item = this.front.getItem();
	this.front = this.front.getNext();

	if (this.front == null) {
	    // list is empty, set rear to null
	    this.rear = null;
	}
	this.length--;

	return item;

    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {

	// If the list is empty, there's nothing to remove
	if (this.front == null) {
	    return;
	}

	// Remove nodes from the front if they match the key
	while (this.front != null && this.front.getItem().equals(key)) {
	    this.front = this.front.getNext();
	    this.length--;
	}

	// If the list is now empty, update the rear and return
	if (this.front == null) {
	    this.rear = null;
	    return;
	}

	// Traverse the list to remove non-front nodes
	SingleNode<T> current = this.front;
	while (current.getNext() != null) {
	    if (current.getNext().getItem().equals(key)) {
		// Skip the next node as it matches the key
		current.setNext(current.getNext().getNext());
		this.length--;

		// Update rear if it was the last node
		if (current.getNext() == null) {
		    this.rear = current;
		}
	    } else {
		// Move to the next node
		current = current.getNext();
	    }
	}

	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {
	if (this.front == null || this.front.getNext() == null) {
	    // list is null or only has 1 item
	    return;
	}
	SingleNode<T> curr = this.front;
	SingleNode<T> prev = null;
	SingleNode<T> next = null;

	while (curr != null) {
	    next = curr.getNext();
	    curr.setNext(prev);
	    prev = curr;
	    curr = next;
	}
	// swap rear and front pointers
	this.rear = this.front;
	this.front = prev;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	int middle = this.length / 2 + this.length % 2;
	SingleNode<T> curr = this.front;

	// Move middle - 1 nodes to left
	for (int i = 0; i < middle - 1; i++) {
	    curr = curr.getNext();
	}

	// Set up left list
	left.length = middle;
	left.front = this.front;
	left.rear = curr;

	// Set up right list
	right.length = this.length - middle;
	right.front = curr.getNext();
	right.rear = this.rear;

	// Important: Set the next of the last node of left to null
	if (left.rear != null) {
	    left.rear.setNext(null);
	}

	// Clear this list
	this.front = null;
	this.rear = null;

	return;
    }

//	target1 = List()
//        target2 = List()
//        # Split
//        middle = self._count // 2 + self._count % 2
//        prev = None
//        curr = self._front
//
//        for _ in range(middle):
//            prev = curr
//            curr = curr._next
//
//        if prev is not None:
//            # Break the source list between prev and curr
//            prev._next = None
//
//        # Define target1
//        target1._count = middle
//        target1._front = self._front
//        target1._rear = prev
//
//        # Define target2
//        target2._count = self._count - middle
//        target2._front = curr
//
//        if target2._count > 0:
//            target2._rear = self._rear
//
//        # Clean up source
//        self._front = None
//        self._rear = None
//        self._count = 0
//        return target1, target2

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	boolean leftTrigger = true;

	while (this.length > 0) {
	    if (leftTrigger) {
		left.moveFrontToRear(this);
	    } else {
		right.moveFrontToRear(this);
	    }
	    leftTrigger = !leftTrigger;
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	// clear the current list to start fresh
	this.front = null;
	this.rear = null;
	this.length = 0;

	// Transfer nodes from the left list to this list
	while (left.front != null) {
	    this.moveFrontToRear(left);
	}

	// Then, transfer nodes from the right list to this list
	while (right.front != null) {
	    this.moveFrontToRear(right);
	}
	return;
    }

}
