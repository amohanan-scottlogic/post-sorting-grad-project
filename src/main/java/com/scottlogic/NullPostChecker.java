package com.scottlogic;

import java.util.ArrayList;
import java.util.List;

public class NullPostChecker {

    public List<UserPost> nullPostCheck(List<UserPost> inputList) {

        List<UserPost> postNotNull = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) != null) {
                postNotNull.add(inputList.get(i));
            }
        }
        return postNotNull;
    }
}
