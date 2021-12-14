package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class LikesPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToBeSorted = new ArrayList<>();
        if (inputList == null) {
            return listToBeSorted;
        }
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);
        switch (orderIn) {

            case ASC -> Collections.sort(listToBeSorted, Comparator.comparingInt(UserPost::getLikeCount));

            case DESC -> Collections.sort(listToBeSorted, Collections.reverseOrder(comparing(UserPost::getLikeCount)));
        }

        return listToBeSorted;
    }
}
