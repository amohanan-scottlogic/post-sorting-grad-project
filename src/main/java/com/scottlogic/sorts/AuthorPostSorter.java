package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AuthorPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToSort = new ArrayList<>();
        if (inputList == null) {
            return listToSort;
        }
        listToSort = NullPostChecker.nullPostCheck(inputList);

        switch (orderIn) {

            case ASC -> Collections.sort(listToSort, Comparator.nullsLast(Comparator.comparing(UserPost::getAuthor, Comparator.nullsLast(Comparator.naturalOrder()))));

            case DESC -> Collections.sort(listToSort, Collections.reverseOrder(Comparator.nullsLast(Comparator.comparing(UserPost::getAuthor, Comparator.nullsFirst(Comparator.naturalOrder())))));
        }
        return listToSort;
    }
}
