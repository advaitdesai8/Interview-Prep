package com.advait.interviewprep.singleton;

public class Singelton {
    private String name;

    public String getName() {
        return name;
    }

    private static volatile Singelton instance = getInstance();

    private Singelton(String name) {
        this.name = name;
    }

    public static Singelton getInstance() {
        if (instance == null) {
            synchronized (Singelton.class) {
                if (instance == null) {
                    return new Singelton("Advait");
                }
            }
        }
        return instance;
    }
}