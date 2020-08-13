package com.rockbite.bootcamp.util.observer;

/**
 * Observer class
 */
public abstract class Observer {

    //a subject to which's state this class must subscribe
    public ObservationSubject observationSubject;

    /**
     * Observer's state updating method as a result of changing subject's state
     */
    public abstract void update();
}
