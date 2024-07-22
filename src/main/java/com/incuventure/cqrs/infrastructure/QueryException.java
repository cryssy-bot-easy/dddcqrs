package com.incuventure.cqrs.infrastructure;

/**
 */
public class QueryException extends Exception {


    public QueryException(){

    }

    public QueryException(Throwable e){
        super(e);
    }

    public QueryException(String message, Throwable e){
        super(message,e);
    }


}
