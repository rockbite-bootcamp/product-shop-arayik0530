package com.rockbite.bootcamp.util.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject to which observers are subscribed
 */
public class ObservationSubject {

    //observers
    private final List<Observer> observers;

    //subjects state
    private int productListLength;

    public ObservationSubject(final int productListLength) {
        this.observers = new ArrayList<>();
        this.productListLength = productListLength;
    }

    /**
     * state changing method
     *
     * @param length Products's count in the shop
     */
    public void setState(int length) {
        this.productListLength = length;

        notifyAllObservers();
    }

    /**
     * observers notifying method
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * subscribing method to subject
     *
     * @param observer Observer
     */
    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * unsubscribing method to subject
     *
     * @param observer Observer
     */
    public void unsubscribe(Observer observer) {
        this.observers.remove(observer);
    }

    //Getter
    public int getProductListLength() {
        return productListLength;
    }
}
