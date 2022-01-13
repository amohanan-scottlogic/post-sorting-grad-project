package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorPostSorter implements PostSorter {




    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToSort = new ArrayList<>();
        List<UserPost> listSorted;

        if (inputList == null) {
            return listToSort;
        }
        listToSort = NullPostChecker.nullPostCheck(inputList);

        listSorted = listToSort.stream()
                .filter(post -> post.getAuthor() != null)
                .sorted(orderIn.isAscending() ? AuthorName : AuthorName.reversed())
                .collect(Collectors.toList());

        if (listSorted.size() != listToSort.size()) {
            List<UserPost> nullsInList = listToSort.stream().filter(post -> post.getAuthor() == null).toList();
            listSorted.addAll(nullsInList);
        }

        return listSorted;
    }

    public Comparator<UserPost> AuthorName = this::authorSort;

    private int authorSort(UserPost o1, UserPost o2) {

        if (o1.getAuthor().compareTo(o2.getAuthor()) == 0) {
            return 0;
        } else {
            return (o1.getAuthor().compareTo(o2.getAuthor()) > 0) ? 1 : -1;
        }
    }
}
