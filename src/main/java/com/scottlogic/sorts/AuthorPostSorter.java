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

        listSorted = (orderIn.compareTo(SortOrder.ASC) == 0) ?
                listToSort.stream().sorted(AuthorName).collect(Collectors.toList()) :

                listToSort.stream().sorted(AuthorName.reversed()).collect(Collectors.toList());


        return listSorted;
    }

    public Comparator<UserPost> AuthorName = (o1, o2) -> {
        if (o1.getAuthor() == null)
            return 1;
        if (o2.getAuthor() == null)
            return -1;
        if (o1.getAuthor().compareTo(o2.getAuthor()) == 0) {
            return 0;
        } else {
            return (o1.getAuthor().compareTo(o2.getAuthor()) > 0) ? 1 : -1;
        }
    };
}
