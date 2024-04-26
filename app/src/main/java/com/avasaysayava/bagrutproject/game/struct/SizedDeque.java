package com.avasaysayava.bagrutproject.game.struct;
import androidx.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Collection;

public class SizedDeque<E> extends ArrayDeque<E> {
    private final int capacity;

    public SizedDeque(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    @Override
    public boolean add(E e) {
        if (size() == this.capacity)
            poll();
        return super.add(e);
    }

    @Override
    public void addFirst(E e) {
        if (size() == this.capacity)
            pollLast();
        super.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        if (size() == this.capacity)
            pollFirst();
        super.addLast(e);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        final int needed;
        if ((needed = size() + c.size() + 1 - this.capacity) > 0)
            for (int i = 0; i < needed; i++)
                poll();
        return super.addAll(c);
    }

    @Override
    public boolean offer(E e) {
        if (size() == this.capacity)
            poll();
        return super.offer(e);
    }

    @Override
    public boolean offerFirst(E e) {
        if (size() == this.capacity)
            pollLast();
        return super.offerFirst(e);
    }

    @Override
    public boolean offerLast(E e) {
        if (size() == this.capacity)
            pollFirst();
        return super.offerLast(e);
    }
}
