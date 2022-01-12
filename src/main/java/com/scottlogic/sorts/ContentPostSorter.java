package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.*;
import java.util.stream.Collectors;


public class ContentPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> remainingPosts = new ArrayList<>();
        List<UserPost> listToSort;

        if (inputList == null) {
            return remainingPosts;
        }

        listToSort = NullPostChecker.nullPostCheck(inputList);
        List<UserPost> contentNull = listToSort.stream().filter(post -> post.getContents() == null).collect(Collectors.toList());
        List<UserPost> contentNonNull = listToSort.stream().filter(post -> post.getContents() != null).collect(Collectors.toList());


        remainingPosts = contentNonNull.stream()
                .sorted(orderIn.isAscending() ? ContentLength : ContentLength.reversed())
                .collect(Collectors.toList());

        if (!contentNull.isEmpty()) {
            remainingPosts.addAll(contentNull);
        }
        return remainingPosts;
    }

    public Comparator<UserPost> ContentLength = (u1, u2) -> contentSort(u1, u2);

    private int contentSort(UserPost u1, UserPost u2) {
        if (u1.getContents().length() == u2.getContents().length()) {
            return 0;
        } else {
            return u1.getContents().length() > u2.getContents().length() ? 1 : -1;
        }
    }
}
