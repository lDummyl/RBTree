import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class RBTreeTest {

	@Test
	public void test() {
        RBTree<Integer> tree = new RBTree<Integer>();
        for(int i=0; i<20; ++i) {
            tree.insert(i);
        }

        Assert.assertEquals("7 3 1 0 2 5 4 6 11 9 8 10 15 13 12 14 17 16 18 19", tree.preOrder());
        Assert.assertTrue(tree.search(17) != null);
        Assert.assertTrue(tree.search(5) != null);
        Assert.assertTrue(tree.search(-3) == null);
        Assert.assertTrue(tree.search(26) == null);
	}

}
