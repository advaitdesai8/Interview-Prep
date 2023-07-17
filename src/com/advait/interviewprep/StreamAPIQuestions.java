package com.advait.interviewprep;

import java.util.List;

public class StreamAPIQuestions {

    public static void main(String[] args) {
        List<String> list = List.of("", "advait", "messi","is", "the", "goat", "", "");

        System.out.println(list.stream().filter(String::isEmpty).count());
    }
}
