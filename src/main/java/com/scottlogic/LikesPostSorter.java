package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class LikesPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        NullPostChecker checkForNullPost = new NullPostChecker();
        inputList = checkForNullPost.nullPostCheck(inputList);
        switch (orderIn) {

            case ASC -> Collections.sort(inputList, Comparator.comparingInt(UserPost::getLikeCount));

            case DESC -> Collections.sort(inputList, Collections.reverseOrder(comparing(UserPost::getLikeCount)));
        }

        return inputList;
    }
}
