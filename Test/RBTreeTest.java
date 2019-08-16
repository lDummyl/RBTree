import org.junit.Assert;
import org.junit.Test;

public class RBTreeTest {

	@Test
	public void test() {
		RBTree<Integer> tree = new RBTree<Integer>();
		for (int i = 0; i < 20; ++i) {
			tree.insert(i);tree.treeshow();
		}

		tree.treeshow();
		Assert.assertEquals("7 3 1 0 2 5 4 6 11 9 8 10 15 13 12 14 17 16 18 19", tree.preOrder());
		Assert.assertTrue(tree.search(17) != null);
		Assert.assertTrue(tree.search(5) != null);
		Assert.assertTrue(tree.search(-3) == null);
		Assert.assertTrue(tree.search(26) == null);
	}

	@Test
	public void testMy4case() {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.insert(0);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.treeshow();

	}@Test
	public void testMy() {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.insert(4);
		tree.insert(8);
		tree.insert(6);
		tree.treeshow();

	}
	@Test
	public void testMyLeft() {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.insert(8);
		tree.insert(6);
		tree.insert(4);
		tree.treeshow();

	}
	@Test
	public void testMyLeftAngle() {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.insert(4);
		tree.insert(6);
		tree.insert(8);
		tree.treeshow();

	}

}
