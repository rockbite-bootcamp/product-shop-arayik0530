package com.rockbite.bootcamp.util.observer;

public class TransactionObserver extends Observer {

    public TransactionObserver(ObservationSubject observationSubject) {
        this.observationSubject = observationSubject;
        this.observationSubject.subscribe(this);
    }

    @Override
    public void update() {
        System.out.println("_________OBSERVER INFO start_________");//TODO remove
        System.out.println("New Transaction completed. Available products's count in the shop is "
                + this.observationSubject.getProductListLength());//TODO remove
        System.out.println("_________OBSERVER INFO end___________");//TODO remove
    }
}
