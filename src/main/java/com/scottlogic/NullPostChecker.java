package com.scottlogic;

import java.util.ArrayList;
import java.util.List;

public  class NullPostChecker {

    public static List<UserPost> nullPostCheck(List<UserPost> inputList) {

        List<UserPost> postNotNull = new ArrayList<>();
        for (UserPost userPost : inputList) {
            if (userPost != null) {
                postNotNull.add(userPost);
            }
        }
        return postNotNull;
    }
}
