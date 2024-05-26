package com.avasaysayava.bagrutproject.util.struct;

import androidx.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Collection;

public class SizedDeque<E> extends ArrayDeque<E> {
    // The maximum capacity of the deque
    private final int CAPACITY;

    // Constructor that initializes the deque with a given capacity
    public SizedDeque(int capacity) {
        // Call the parent constructor with the given capacity
        super(capacity);
        // Store the capacity for later use
        this.CAPACITY = capacity;
    }

    // Override the add() method to ensure that the deque does not exceed its capacity
    @Override
    public boolean add(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the oldest element from the deque
            poll();
        }
        // Add the new element to the deque
        return super.add(e);
    }

    // Override the addFirst() method to ensure that the deque does not exceed its capacity
    @Override
    public void addFirst(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the newest element from the deque
            pollLast();
        }
        // Add the new element to the front of the deque
        super.addFirst(e);
    }

    // Override the addLast() method to ensure that the deque does not exceed its capacity
    @Override
    public void addLast(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the oldest element from the deque
            pollFirst();
        }
        // Add the new element to the end of the deque
        super.addLast(e);
    }

    // Override the addAll() method to ensure that the deque does not exceed its capacity
    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        // Calculate the number of elements that need to be removed from the deque to make room for the new elements
        final int needed = size() + c.size() + 1 - this.CAPACITY;
        // If there are elements that need to be removed, remove them
        if (needed > 0) {
            for (int i = 0; i < needed; i++) {
                poll();
            }
        }
        // Add the new elements to the deque
        return super.addAll(c);
    }

    // Override the offer() method to ensure that the deque does not exceed its capacity
    @Override
    public boolean offer(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the oldest element from the deque
            poll();
        }
        // Add the new element to the deque
        return super.offer(e);
    }

    // Override the offerFirst() method to ensure that the deque does not exceed its capacity
    @Override
    public boolean offerFirst(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the newest element from the deque
            pollLast();
        }
        // Add the new element to the front of the deque
        return super.offerFirst(e);
    }

    // Override the offerLast() method to ensure that the deque does not exceed its capacity
    @Override
    public boolean offerLast(E e) {
        // Check if the deque is full
        if (size() == this.CAPACITY) {
            // If it is, remove the oldest element from the deque
            pollFirst();
        }
        // Add the new element to the end of the deque
        return super.offerLast(e);
    }
}
