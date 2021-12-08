package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class LikesPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        switch (orderIn) {

            case ASC -> Collections.sort(inputList, Comparator.comparing(UserPost::getLikeCount));

            case DESC -> Collections.sort(inputList, Collections.reverseOrder(comparing(UserPost::getLikeCount)));
        }

        return inputList;
    }
}
