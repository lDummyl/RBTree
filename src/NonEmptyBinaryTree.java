
/**
 * NonEmptyBinaryTree - this is a binary search tree that is either an inner node
 * of the tree or a leaf node.  Leaf nodes will have 2 empty nodes attached to
 * the right and the left subtrees.  Note that the insert and remove operation return
 * the changed tree and they will generally modify existing trees.
 *
 * @author Eric McCreath - GPLv2
 * @author Huy Pham - Modified to remove unused methods for the purpose of the lab exercise.
 * @author dongwookim - add javadocs
 */


/*

---
title: "Lab 3 - Trees"
summary: Trees
layout: page
---

## Task 1 - Binary Search Tree [0.6 marks]

The main objective of this part is to understand how to insert and delete nodes in a Binary Search Tree (BST). The partial code for this part could be found [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/lab3).

 * Go through and read the file `BinaryTree.java`. It is just a simple abstract class for a BST. Go through the implementation of `EmptyBinaryTree.java` and `NonEmptyBinaryTree.java` which inherit `BinaryTree` class.
   * We use a recursive definition of binary search tree in this implementation. A tree consist of 1) root node 2) left sub-tree (which is again a tree), and 3) right sub-tree (which is again a tree).
 * Implement the method `insert()` in `NonEmptyBinaryTree.java`.
 * Implement the method `delete()` in `NonEmptyBinaryTree.java`. If the target node, which we want to delete, has two children, replace the target node its successor in its descendant (See lecture slides for details).
 * Use `BinaryTreeTest.java` to check whether your implementation is correct or not.

Use the provided test code to check the correctness of your implementation. Please be aware that we may use additional test cases to verify of your code. Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.

## Task 2 - Red-Black Tree [0.4 marks]

Red-Black Tree (R-B Tree) is a special Binary Search Tree (BST). It has all the properties of BST and some extras. For each node of the R-B Tree, it stores a **color**, it should only be red or black. Here are some properties of R-B Trees:

 * Each node should be either black or red.
 * The root node should be black.
 * The null leaf node should be black.
 * For each red node, its children nodes must be black.
 * Each node should have the same number of black nodes from itself to all its children.

The main objective of this part is to implement the insert part of the red-black tree. The code for this part could be found [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/lab3). Read the code in 'RedBlackTree.java', implement your `insert()` method and try your code with the 'RedBlackTreeDemo.java'. Note that to complete `insert()` function, you also need to implement `rotateRight()` correctly.

Use the provided test code to check the correctness of your implementation. Please be aware that we may use additional test cases to verify of your code. Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.

## Submission Guideline

* Assignment deadline: Friday, 16 August 2019, 11:55 PM
* Submission mode: Electronic, via Wattle (Lab 3)
* Submission format (IMPORTANT):
  * Upload your final version of NonEmptyBinaryTree.java (for task 1) and RBTree.java (for task 2) to Wattle.
    * Do not change file name.
    * Do not upload any other files.
  * Do not change the structure of the source code including class name, package structure, etc. You are only allowed to edit the part indicated by comments (See source code files for details).
  * Violation of the submission format will have their assignment not evaluated by an autograder and get zero marks.

Reference: [Introduction to Algorithms (Cormen, Leiserson, Rivest, Stein) Chap. 13](https://cs.anu.edu.au/courses/comp2100/lectures/notes/red-black-tree.pdf).



 */


public class NonEmptyBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {

    T data;        // data of the root node of this tree
    BinaryTree<T> left, right;    // left and right sub-trees

    /**
     * Create a NonEmptyBinaryTree tree with root node.
     * The tree will not have sub-trees.
     *
     * @param data the data of the root node.
     */
    public NonEmptyBinaryTree(T data) {
        this.data = data;
        left = new EmptyBinaryTree<T>();
        right = new EmptyBinaryTree<T>();
    }

    /**
     * Create a NonEmptyBinaryTree with left and right sub-trees
     *
     * @param data  value of the root node
     * @param left  left sub-tree of the new NonEmptyBinaryTree tree
     * @param right right sub-tree of the new NonEmptyBinaryTree tree
     */
    public NonEmptyBinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }


    /**
     * Insert a new node whose value is d into the existing tree.
     * This function should return the binary tree with d inserted.
     * If the tree has already node with d, do not create a new node
     * and return the original tree.
     * <p>
     * Hint: You can implement insert function recursively.
     * (Each subtree (left or right) is a tree which has insert function)
     *
     * @param d data of the new node
     * @return BinaryTree<T>
     */
    public BinaryTree<T> insert(T d) {
        // TODO: Add your implementation here
        // ########## YOUR CODE STARTS HERE ##########
        int i = d.compareTo(data);
        if (i < 0) {
            if (left instanceof EmptyBinaryTree) {
                left = new NonEmptyBinaryTree<>(d);

            } else if (left instanceof NonEmptyBinaryTree) {
                left.insert(d);
            }
        }
        if (i > 0) {
            if (right instanceof EmptyBinaryTree) {
                right = new NonEmptyBinaryTree<>(d);
            } else if (right instanceof NonEmptyBinaryTree) {
                right.insert(d);
            }
        }
        // ########## YOUR CODE ENDS HERE ##########
        return this;
    }

    /**
     * Returns the size of the tree (number of nodes)
     */
    public int size() {
        return 1 + left.size() + right.size();
    }

    /**
     * Compute the height of {@code this} tree
     */
    @Override
    public int height() {
        return 1 + Math.max(left.height(), right.height());
    }

    /**
     * Preorder traversing
     */
    @Override
    public String preOrderShow() {
        if (left.isEmpty() && right.isEmpty()) return data + "";
        else {
            String leftStr = left.preOrderShow();
            String rightStr = right.preOrderShow();
            return data + (leftStr.isEmpty() ? leftStr : " " + leftStr) + (rightStr.isEmpty() ? rightStr : " " + rightStr);
        }
    }


    public T inOrderSuccessor(NonEmptyBinaryTree<T> root) {
        T minimum = root.data;
        while (root.left instanceof NonEmptyBinaryTree) {
            NonEmptyBinaryTree<T> leftNonEmpt = (NonEmptyBinaryTree<T>)root.left;
            minimum = leftNonEmpt.data;
            root = (NonEmptyBinaryTree<T>)root.left;
        }
        return minimum;
    }

    /**
     * Remove node with data {@code d}
     * This function should return the binary tree with d removed.
     * If the tree don't have a node with d, return the original tree.
     * <p>
     * Hint: You can implement insert function recursively.
     * (Each subtree (left or right) is a tree which has delete function)
     *
     * @param d value of the node that should be removed
     * @return BinaryTree<T> The binary tree without d.
     */
    @Override
    public BinaryTree<T> delete(T d) {
        // TODO: Add your implementation here
        // ########## YOUR CODE STARTS HERE ##########

        if (data.compareTo(d) == 0) {
            //remove element
            if (right instanceof EmptyBinaryTree && left instanceof EmptyBinaryTree) {
                return new EmptyBinaryTree<>();
                //do stuff
            }
            if (left instanceof EmptyBinaryTree) {
                return right;
            }
            if (right instanceof EmptyBinaryTree) {
                return left;
            }
            if (right instanceof NonEmptyBinaryTree && left instanceof NonEmptyBinaryTree) {
                data = inOrderSuccessor((NonEmptyBinaryTree<T>) right);
                right = right.delete(data);
            }
        }
        if (left.find(d)) {
            left = left.delete(d);
        }
        if (right.find(d)) {
            right = right.delete(d);
        }

////        int i = d.compareTo(data);
////        if(i < 0){
////            if(left instanceof NonEmptyBinaryTree){
////                left.delete(d);
////            }
////        } else if(i>0){
////            if(right instanceof NonEmptyBinaryTree){
////                right.delete(d);
////            }
////
////        } else {
////
////        }
//
//
//        /*
//1. If x has no children ¨ case 0
//– then remove x
//2. If x has one child ¨ case 1
//– then make p[x] point to child
//3. If x has two children (subtrees) ¨ case 2
//– then replace x with its successor
//– Delete successor in subtree
//
//Successor:
//if right[x] ¹ null
//2. then return Tree-Minimum(right[x])
//3. y ¬ p[x]
//4. while y ¹ null and x = right[y]
//5. do x ¬ y
//6. y ¬ p[y]
//7. return y
//         */
//
//    int rheight = right.height();
//    int lheight = left.height();
//    int sizee = size(data);
//        if(rheight==0&&lheight ==0)
//
//    {
//        return new EmptyBinaryTree<T>();
//    } else if(rheight==1)
//
//    {
//        return right;
//    } else if(i==2)
//
//    {
//        T successor = right.smallest();
//        NonEmptyBinaryTree<T> tree = new NonEmptyBinaryTree<T>(successor, left, right);
//        delete(right.smallest());
//
//
//    } else if(i==0)
//
//    {
//
//    }
//
//
//        if(
//
//    find(d)==true)
//
//    {
//        NonEmptyBinaryTree tree = new NonEmptyBinaryTree(d);
//
//        System.out.println(tree.treeshow() + "123");
//    }


        // ########## YOUR CODE ENDS HERE ##########
        return this;
    }


    /**
     * NonEmptyBinaryTree is by definition non-empty. It will return false always.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * This function will find the biggest node in a tree.
     */
    @Override
    public T biggest() {
        if (right.isEmpty()) return data;
        else return right.biggest();
    }

    /**
     * This function will find the smallest node in a tree.
     */
    @Override
    public T smallest() {
        if (left.isEmpty()) return data;
        else return left.smallest();
    }

    /**
     * Helper functions for visualizing tree.
     */
    @Override
    public String treeshow() {
        if (left.isEmpty() && right.isEmpty()) return " " + data + " ";
        String stl = left.treeshow();
        String str = right.treeshow();
        String stal[] = stl.split("\n");
        String star[] = str.split("\n");
        int lenl = stal[0].length();
        int lenr = star[0].length();
        StringBuffer res = new StringBuffer();
        String strdata = data + "";
        int fullsize = lenl + strdata.length() + lenr;


        res.append(repeat(" ", (lenl)) + strdata + repeat(" ", lenr) + "\n");
        int lcentre = (left.isEmpty() ? 0 : centre(stal[0]));
        int rcentre = (right.isEmpty() ? 0 : centre(star[0]));

        res.append(repeat(" ", lcentre) + "+" + repeat("-", lenl - lcentre - 1) + "+" + repeat("-", rcentre - 1 + strdata.length()) + "+" + repeat(" ", lenr - rcentre - 1) + "\n");
        res.append(repeat(" ", lcentre) + (left.isEmpty() ? " " : "|") + repeat(" ", lenl - lcentre - 1) + repeat(" ", rcentre + strdata.length()) + (right.isEmpty() ? " " : "|") + repeat(" ", lenr - rcentre - 1) + "\n");

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

    /**
     * Find whether a specific data is in the tree or not.
     */
    @Override
    public boolean find(T d) {
        if (data == d) return true;
        else if (d.compareTo(data) < 0) return left.find(d);
        else return right.find(d);
    }
}
