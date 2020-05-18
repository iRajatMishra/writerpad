package com.xebia.writerpad.exception;

public class WriterpadNotFoundException extends RuntimeException{
    public WriterpadNotFoundException(){
        super("Writerpad Not Found");
    }
}
