package net.kaoriya.qb.intarrayset;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class IntArrayIterator implements Iterator<Integer>
{
    private final int[] array;
    private final int end;
    private int pos;

    public IntArrayIterator(int[] array, int pos, int len)
    {
        this.array = array;
        this.end = Math.min(pos + len, this.array.length);
        this.pos = pos;
    }

    public boolean hasNext()
    {
        return this.pos < this.end;
    }

    public Integer next()
    {
        if (this.pos < this.end) {
            return this.array[this.pos++];
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
