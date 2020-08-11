package com.rockbite.bootcamp.util.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool ob objects
 * @param <T> every class that implements Poolable interface
 */
public abstract class Pool<T extends Poolable> {

    //free objects in the pool ready to use
    List<T> freeObjects = new ArrayList<>();
    //objects of the pool currently in use
    List<T> usedObjects = new ArrayList<>();

    public abstract T newObject();

    public Pool() {
        T obj = obtain();
        free(obj);
    }

    /**
     * method for obtaining a free object from the pool
     * @return every class that implements Poolable interface
     */
    public T obtain(){
        if(freeObjects.isEmpty()){
            T newObj = this.newObject();
            freeObjects.add(newObj);
        }

        T returningObj = freeObjects.remove(0);

        usedObjects.add(returningObj);

        return returningObj;
    }

    /**
     * method for making free an object that was used and now is free
     * @param object T every class that implements Poolable interface
     */
    public void free(T object){
        freeObjects.add(object);
        usedObjects.remove(object);

        object.reset();
    }
}
