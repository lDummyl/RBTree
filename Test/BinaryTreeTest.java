import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {
	BinaryTree<Integer> tree;
	
	@Before
    public void beforeEachTestMethod() {
	    tree = new NonEmptyBinaryTree<Integer>(7);
	    tree = tree.insert(3)
	    	.insert(1)
	    	.insert(5)
	    	.insert(4)
	    	.insert(11)
	    	.insert(10)
	    	.insert(15);
    }
	
	@Test
	public void testInsert() {
		Assert.assertEquals("7 3 1 5 4 11 10 15", tree.preOrderShow());
	}

	@Test
	public void testRemoveNodeWithNoChild() {
		BinaryTree<Integer> tree = this.tree.delete(15);
		Assert.assertEquals("7 3 1 5 4 11 10", tree.preOrderShow());
	}
	
	@Test
	public void testRemoveNodeWithOneChild() {
        Assert.assertEquals("7 3 1 4 11 10 15", tree.delete(5).preOrderShow());
	}
	
	@Test
	public void testRemoveNodeWithTwoChildren() {
		String result = tree.delete(3).preOrderShow();
		Assert.assertEquals("7 4 1 5 11 10 15", result);
	}
	
	@Test
	public void testRemoveRootNode() {
		String result = tree.delete(7).preOrderShow();
        Assert.assertEquals("10 3 1 5 4 11 15", result);
	}
}
