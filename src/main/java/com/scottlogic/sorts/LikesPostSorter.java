package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class LikesPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToBeSorted = new ArrayList<>();
        if (inputList == null) {
            return listToBeSorted;
        }
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        List<UserPost> listSorted = listToBeSorted.stream()
                .sorted(orderIn.isAscending() ? Comparator.comparingInt(UserPost::getLikeCount) : Collections.reverseOrder(comparing(UserPost::getLikeCount)))
                .collect(Collectors.toList());


        return listSorted;
    }
}
