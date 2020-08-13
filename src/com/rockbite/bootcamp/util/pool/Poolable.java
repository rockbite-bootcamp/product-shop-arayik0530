package com.rockbite.bootcamp.util.pool;

/**
 * represents Poolable types that are going to be in the pool
 */
public interface Poolable {
    /**
     * reset poolable object's state when making free
     */
    void reset();
}
