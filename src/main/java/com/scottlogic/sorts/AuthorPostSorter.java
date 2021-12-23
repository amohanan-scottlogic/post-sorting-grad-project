package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class AuthorPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToSort = new ArrayList<>();
        List<UserPost> listSorted = new ArrayList<>();

        if (inputList == null) {
            return listToSort;
        }
        listToSort = NullPostChecker.nullPostCheck(inputList);


        switch (orderIn) {

            case ASC -> listSorted = listToSort.stream().sorted(Comparator.nullsLast(Comparator.comparing(UserPost::getAuthor, nullsLast(naturalOrder())))).collect(Collectors.toList());


            case DESC -> listSorted = listToSort.stream().sorted(Collections.reverseOrder(comparing(UserPost::getAuthor, nullsFirst(naturalOrder())))).collect(Collectors.toList());
        }

        return listSorted;
    }
}
