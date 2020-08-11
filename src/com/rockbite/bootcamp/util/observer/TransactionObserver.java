package com.rockbite.bootcamp.util.observer;

public class TransactionObserver extends Observer {

    public TransactionObserver(Subject subject) {
        this.subject = subject;
        this.subject.subscribe(this);
    }

    @Override
    public void update() {
        System.out.println("_________OBSERVER INFO start_________");
        System.out.println("New Transaction completed. Products's count in the shop is "
                + this.subject.getProductListLength());
        System.out.println("_________OBSERVER INFO end___________");
    }
}
