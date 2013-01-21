package net.kaoriya.qb.intarrayset;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public final class IntArraySetTest
{

    @Test
    public void empty()
    {
        IntArraySet set = new IntArraySet();
        Assert.assertEquals(0, set.size());
        Iterator<Integer> iter = set.iterator();
        Assert.assertFalse(iter.hasNext());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void basic1()
    {
        IntArraySet set = new IntArraySet();
        set.add(10);
        set.add(100);
        set.add(200);
        Assert.assertEquals(3, set.size());

        Iterator<Integer> iter = set.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(10), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(100), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(200), iter.next());
        Assert.assertFalse(iter.hasNext());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void basic2()
    {
        IntArraySet set = new IntArraySet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        Assert.assertEquals(9, set.size());

        Iterator<Integer> iter = set.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(4), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(5), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(6), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(7), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(8), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(9), iter.next());
        Assert.assertFalse(iter.hasNext());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void union1()
    {
        IntArraySet a = IntArraySet.newInstance(1, 4, 7, 10);
        IntArraySet b = IntArraySet.newInstance(2, 4, 8, 10);
        IntArraySet c = IntArraySet.newInstance(1, 2, 4, 7, 8, 10);
        IntArraySet d1 = IntArraySet.union(a, b);
        Assert.assertEquals(c, d1);
        IntArraySet d2 = IntArraySet.union(b, a);
        Assert.assertEquals(c, d2);
    }

    @Test
    public void union2()
    {
        IntArraySet a = IntArraySet.newInstance(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        IntArraySet b = IntArraySet.newInstance(0, 2, 4, 6, 8);
        IntArraySet c = IntArraySet.newInstance(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        IntArraySet d1 = IntArraySet.union(a, b);
        Assert.assertEquals(c, d1);
        IntArraySet d2 = IntArraySet.union(b, a);
        Assert.assertEquals(c, d2);
    }

    @Test
    public void intersect1()
    {
        IntArraySet a = IntArraySet.newInstance(1, 4, 7, 10);
        IntArraySet b = IntArraySet.newInstance(2, 4, 8, 10);
        IntArraySet c = IntArraySet.newInstance(4, 10);
        IntArraySet d1 = IntArraySet.intersect(a, b);
        Assert.assertEquals(c, d1);
        IntArraySet d2 = IntArraySet.intersect(b, a);
        Assert.assertEquals(c, d2);
    }

    @Test
    public void intersect2()
    {
        IntArraySet a = IntArraySet.newInstance(1, 3, 5, 7, 9);
        IntArraySet b = IntArraySet.newInstance(0, 2, 4, 6, 8);
        IntArraySet c = new IntArraySet();
        IntArraySet d1 = IntArraySet.intersect(a, b);
        Assert.assertEquals(c, d1);
        IntArraySet d2 = IntArraySet.intersect(b, a);
        Assert.assertEquals(c, d2);
    }
}
