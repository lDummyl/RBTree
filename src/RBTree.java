/**
 * Skeleton code for Red Black Tree
 *
 * @param <T> data type
 * @author dongwookim
 */
public class RBTree<T extends Comparable<T>> {
	private Node<T> root; // The root node of the tree

	/**
	 * Initialize empty RBTree
	 */
	public RBTree() {
		root = null;
	}

	public void treeshow() {
		String treeshow = root.treeshow();
		System.out.println(treeshow);
	}

	/**
	 * Add a new node into the tree with {@code root} node.
	 *
	 * @param root Node<T> The root node of the tree where x is being inserted.
	 * @param x    Node<T> New node being inserted.
	 */
	private void insertRecurse(Node<T> root, Node<T> x) {
		if (root.value.compareTo(x.value) > 0) {
			if (root.l.value == null) {
				root.l = x;
				x.parent = root;
			} else {
				insertRecurse(root.l, x);
			}
		} else if (root.value.compareTo(x.value) < 0) {
			if (root.r.value == null) {
				root.r = x;
				x.parent = root;
			} else {
				insertRecurse(root.r, x);
			}
		}
		// Do nothing if the tree already has a node with the same value.
	}

	/**
	 * Insert node into RBTree.
	 *
	 * @param x Node<T> The new node being inserted into the tree.
	 */
	private void insert(Node<T> x) {
		// TODO: Complete this function
		// You need to finish cases 1, 2 and 3; the rest has been done for you

		// Insert node into tree
		if (root == null) {
			root = x;
		} else {
			insertRecurse(root, x);
		}

		// Fix tree
		while (x.value != root.value && x.parent.colour == Colour.RED) {
			boolean isParentALeftNode = x.parent == x.parent.parent.l; // Is parent a left node
			Node<T> uncle = isParentALeftNode ? x.parent.parent.r : x.parent.parent.l; // Get opposite "uncle" node to parent

			if (uncle.colour == Colour.RED) {
				// Case 1 / 4: Recolour
				// TODO: Implement this part
				// ########## YOUR CODE STARTS HERE ##########
				x.parent.colour = Colour.BLACK;
				uncle.colour = Colour.BLACK;
				x.parent.parent.colour = Colour.RED;
				// ########## YOUR CODE ENDS HERE ##########
				// Check if violated further up the tree
				x = x.parent.parent;
			} else {
				if (x.value == (isParentALeftNode ? x.parent.r.value : x.parent.l.value)) {
					// Case 2 / 5 : Left (uncle is left node) / Right (uncle is right node) Rotation
					x = x.parent;
					if (isParentALeftNode) {
						// Perform left rotation
						if (x.value == root.value) root = x.r; // Update root
						rotateLeft(x);
					} else {
						// This is part of the "then" clause where left and right are swapped
						// Perform right rotation
						// TODO: Implement this part
						// ########## YOUR CODE STARTS HERE ##########
						rotateRight(x);
						root =  x.parent;
						// ########## YOUR CODE ENDS HERE ##########
					}
				}
				// Adjust colours to ensure correctness after rotation
				x.parent.colour = Colour.BLACK;
				x.parent.parent.colour = Colour.RED;
				// Case 3 / 6 : Right (uncle is left node) / Left (uncle is right node) Rotation
				// TODO: Complete this part
				if (isParentALeftNode) {
					// Perform right rotation
					// ########## YOUR CODE STARTS HERE ##########
                    rotateRight(x.parent.parent);
                    root =  x.parent;
					// ########## YOUR CODE ENDS HERE ##########
				} else {
					// This is part of the "then" clause where left and right are swapped
					// Perform left rotation
					// ########## YOUR CODE STARTS HERE ##########
					rotateLeft(x.parent.parent);
                    if (x.parent.l.value == root.value) root = x.parent; // Update root
					// ########## YOUR CODE ENDS HERE ##########
				}
			}
		}

		// Ensure property 2 (root and leaves are black) holds
		root.colour = Colour.BLACK;
//        treeshow();
	}


	/**
	 * Rotate the node so it becomes the child of its right branch
	 * /*
	 * e.g.
	 * [x]                    b
	 * /   \                 /   \
	 * a       b     == >   [x]     f
	 * / \     / \           /  \
	 * c  d    e   f         a    e
	 * / \
	 * c   d
	 */
	public void rotateLeft(Node<T> x) {
		// Make parent (if it exists) and right branch point to each other
		if (x.parent != null) {
			// Determine whether this node is the left or right child of its parent
			if (x.parent.l.value == x.value) {
				x.parent.l = x.r;
			} else {
				x.parent.r = x.r;
			}
		}
		x.r.parent = x.parent;

		x.parent = x.r;
		// Take right node's left branch
		x.r = x.parent.l;
		x.r.parent = x;
		// Take the place of the right node's left branch
		x.parent.l = x;
	}

	/**
	 * Rotate the node so it becomes the child of its left branch
	 * /*
	 * e.g.
	 * [x]                    a
	 * /   \                 /   \
	 * a       b     == >     c     [x]
	 * / \     / \                   /  \
	 * c  d    e   f                 d    b
	 * / \
	 * e   f
	 */
	public void rotateRight(Node<T> x) {
		// TODO: Implement this function
		// HINT: It is the mirrored version of rotateLeft()
		// ########## YOUR CODE STARTS HERE ##########
		if (x.parent != null) {
			// Determine whether this node is the left or right child of its parent
			if (x.parent.r.value == x.value) {
				x.parent.r = x.l;
			} else {
				x.parent.l = x.l;
			}
		}
		x.l.parent = x.parent;

		x.parent = x.l;
		// Take right node's left branch
		x.l = x.parent.r;
		x.l.parent = x;
		// Take the place of the right node's left branch
		x.parent.r = x;
		// ########## YOUR CODE ENDS HERE ##########
	}

	/**
	 * Demo functions
	 * (Safely) insert a value into the tree
	 *
	 * @param value T The value of the new node being inserted.
	 */
	public void insert(T value) {
		Node<T> node = new Node<T>(value);
		if (node != null) {
			insert(node);
		}
	}

	/**
	 * Return the result of a pre-order traversal of the tree
	 *
	 * @param tree Tree we want to pre-order traverse
	 * @return pre-order traversed tree
	 */
	private String preOrder(Node<T> tree) {
		if (tree != null && tree.value != null) {
			String leftStr = preOrder(tree.l);
			String rightStr = preOrder(tree.r);
			return tree.value + (leftStr.isEmpty() ? leftStr : " " + leftStr) + (rightStr.isEmpty() ? rightStr : " " + rightStr);
		}

		return "";
	}

	public String preOrder() {
		return preOrder(root);
	}

	/**
	 * Return the corresponding node of a value, if it exists in the tree
	 *
	 * @param x Node<T> The root node of the tree we search for the value {@code v}
	 * @param v Node<T> The node that we are looking for
	 * @return
	 */
	private Node<T> find(Node<T> x, T v) {
		if (x.value == null) {
			return null;
		}

		int cmp = v.compareTo(x.value);
		if (cmp < 0) {
			return find(x.l, v);
		} else if (cmp > 0) {
			return find(x.r, v);
		} else {
			return x;
		}
	}

	/**
	 * Returns a node if the value of the node is {@code key}.
	 *
	 * @param key T The value we are looking for
	 * @return
	 */
	public Node<T> search(T key) {
		return find(root, key);
	}

	public enum Colour {
		RED,
		BLACK;
	}

	/**
	 * Base class for node
	 *
	 * @param <T> data type
	 */


	public class Node<T extends Comparable<T>> {
		Colour colour;       // Node colour
		T value;             // Node value
		Node<T> parent;      // Parent node
		Node<T> l, r;        // Children nodes

		public Node(T value) {
			this.value = value;
			this.colour = Colour.RED;
			this.parent = null;

			// Initialise children leaf nodes
			this.l = new Node<T>();
			this.r = new Node<T>();
			this.l.parent = this;
			this.r.parent = this;
		}

		// Leaf node
		public Node() {
			this.value = null;
			this.colour = Colour.BLACK;
		}

		public String treeshow() {
			if (l == null && r == null) return " " + value + " ";
			String stl = l.treeshow();
			String str = r.treeshow();
			String stal[] = stl.split("\n");
			String star[] = str.split("\n");
			int lenl = stal[0].length();
			int lenr = star[0].length();
			StringBuffer res = new StringBuffer();
			String strdata = value + "";
			int fullsize = lenl + strdata.length() + lenr;
			res.append(repeat(" ", (lenl)) + strdata + repeat(" ", lenr) + "\n");
			int lcentre = (l == null ? 0 : centre(stal[0]));
			int rcentre = (r == null ? 0 : centre(star[0]));

			res.append(repeat(" ", lcentre) + "+" + repeat("-", lenl - lcentre - 1) + "+" + repeat("-", rcentre - 1 + strdata.length()) + "+" + repeat(" ", lenr - rcentre - 1) + "\n");
			res.append(repeat(" ", lcentre) + (l == null ? " " : "|") + repeat(" ", lenl - lcentre - 1) + repeat(" ", rcentre + strdata.length()) + (r == null ? " " : "|") + repeat(" ", lenr - rcentre - 1) + "\n");

			for (int i = 0; i < Math.max(stal.length, star.length); i++) {
				res.append((i < stal.length ? stal[i] : repeat(" ", lenl)) + repeat(" ", strdata.length()) + (i < star.length ? star[i] : repeat(" ", lenr)) + "\n");
			}
			return res.toString();
		}

		protected int centre(String string) {
			int i = 0;
			while (i < string.length() && string.charAt(i) == ' ') i++;
			return i;
		}

		protected String repeat(String string, int n) {
			String res = "";
			for (int i = 0; i < n; i++) res += string;
			return res;
		}
	}
}