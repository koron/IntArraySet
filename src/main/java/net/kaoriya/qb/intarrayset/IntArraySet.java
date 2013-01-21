package net.kaoriya.qb.intarrayset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.SortedSet;

public class IntArraySet implements Iterable<Integer>
{
    private int[] buffer;
    private int length = 0;

    private IntArraySet(int[] array, int length) {
        this.buffer = array;
        this.length = length;
    }

    public IntArraySet(int size) {
        this(new int[size], 0);
    }

    public IntArraySet() {
        this(16);
    }

    public int size() {
        return this.length;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof IntArraySet)) {
            return false;
        } else if (o == this) {
            return true;
        }

        IntArraySet that = (IntArraySet)o;
        if (this.length != that.length) {
            return false;
        }
        for (int i = 0, I = this.length; i < I; ++i) {
            if (this.buffer[i] != that.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{")
            .append(" length=").append(this.length)
            .append(" buffer=[");
        for (int i = 0; i < this.length; ++i) {
            s.append(" ").append(this.buffer[i]);
        }
        s.append(" ]").append(" }");
        return s.toString();
    }

    public void add(int value) {
        if (value < acceptable()) {
            throw new IllegalArgumentException();
        }
        while (length >= this.buffer.length) {
            extend();
        }
        this.buffer[length] = value;
        ++length;
    }

    private void add(int[] array, int pos, int len) {
        for (int i = pos, L = Math.min(pos + len, array.length); i < L; ++i) {
            add(array[i]);
        }
    }

    public int acceptable() {
        if (this.length <= 0) {
            return Integer.MIN_VALUE;
        } else {
            return this.buffer[length - 1] + 1;
        }
    }

    private void extend() {
        // FIXME: check buffer overflow.
        int[] newBuf = new int[this.buffer.length * 2];
        System.arraycopy(this.buffer, 0, newBuf, 0, this.buffer.length);
        this.buffer = newBuf;
    }

    public boolean find(int value) {
        return (Arrays.binarySearch(this.buffer, 0, this.length - 1, value)
                >= 0) ? true : false;
    }

    private IntArrayIterator newIterator()
    {
        return new IntArrayIterator(this.buffer, 0, this.length);
    }

    public Iterator<Integer> iterator()
    {
        return newIterator();
    }

    ////////////////////////////////////////////////////////////////////////
    // Static methods.

    public static IntArraySet union(IntArraySet a, IntArraySet b)
    {
        IntArraySet result = new IntArraySet(a.length + b.length);

        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            int v = a.buffer[i];
            int w = b.buffer[j];
            if (v == w) {
                result.add(v);
                ++i;
                ++j;
            } else if (v < w) {
                result.add(v);
                ++i;
            } else {
                result.add(w);
                ++j;
            }
        }

        if (i < a.length) {
            result.add(a.buffer, i, a.length - i);
        } else if (j < b.length) {
            result.add(b.buffer, j, b.length - j);
        }

        return result;
    }

    public static IntArraySet intersect(IntArraySet a, IntArraySet b)
    {
        IntArraySet result = new IntArraySet(Math.min(a.length, b.length));

        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            int v = a.buffer[i];
            int w = b.buffer[j];
            if (v == w) {
                result.add(v);
                ++i;
                ++j;
            } else if (v < w) {
                ++i;
            } else {
                ++j;
            }
        }

        return result;
    }

    private static int[] toArray(SortedSet<Integer> src) {
        int[] dst = new int[src.size()];
        int i = 0;
        for (Integer n : src) {
            dst[i++] = n;
        }
        return dst;
    }

    private static int[] regulate(int[] src)
    {
        TreeSet<Integer> tmpSet = new TreeSet<Integer>();
        for (int n : src) {
            tmpSet.add(n);
        }
        return toArray(tmpSet);
    }

    public static IntArraySet newInstance(int... src) {
        int[] tmp = regulate(src);
        return new IntArraySet(tmp, tmp.length);
    }

    public static IntArraySet newInstance(SortedSet<Integer> src) {
        int[] tmp = toArray(src);
        return new IntArraySet(tmp, tmp.length);
    }
}
