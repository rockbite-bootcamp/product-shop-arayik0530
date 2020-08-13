package com.rockbite.bootcamp;

/**
 * custom exception to describe transaction failure situations
 * it extends RuntimeException to avoid headache of handling checked exceptions
 */
public class TransactionFailedException extends RuntimeException {

    public TransactionFailedException(final String s) {
        super(s);
    }
}
