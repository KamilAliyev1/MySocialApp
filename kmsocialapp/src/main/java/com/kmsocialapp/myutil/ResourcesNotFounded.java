package com.kmsocialapp.myutil;

public class ResourcesNotFounded extends RuntimeException{

    public ResourcesNotFounded() {
    }

    public ResourcesNotFounded(String message) {
        super(message);
    }
}
