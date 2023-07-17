package com.advait.interviewprep.immutable;

import java.util.ArrayList;
import java.util.List;

/**
 * Create immutable class with string and list of string
 */
public final class Persons {

    private final String name = "Advait";

    private List<String> nameList = List.of("advait", "john", "jack"); //unmodifiable list


    public String getName() {
        return name;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public static void main(String[] args) {
        Persons persons = new Persons();
        System.out.println(persons.getName());
        System.out.println(persons.getNameList());
    }
}
