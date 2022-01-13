package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.*;
import java.util.stream.Collectors;


public class ContentPostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> listSorted = new ArrayList<>();
        List<UserPost> listToSort;

        if (inputList == null) {
            return listSorted;
        }

        listToSort = NullPostChecker.nullPostCheck(inputList);

        listSorted = listToSort.stream()
                .filter(post -> post.getContents() != null)
                .sorted(orderIn.isAscending() ? ContentLength : ContentLength.reversed())
                .collect(Collectors.toList());

        if (listSorted.size() != listToSort.size()) {

            List<UserPost> nullsInList = listToSort.stream().filter(post -> post.getContents() == null).toList();
            listSorted.addAll(nullsInList);
        }
        return listSorted;
    }

    public Comparator<UserPost> ContentLength = this::contentSort;

    private int contentSort(UserPost u1, UserPost u2) {
        if (u1.getContents().length() == u2.getContents().length()) {
            return 0;
        } else {
            return u1.getContents().length() > u2.getContents().length() ? 1 : -1;
        }
    }
}
