package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.*;

public class AuthorPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToSort = new ArrayList<>();
        if (inputList == null) {
            return listToSort;
        }
        listToSort = NullPostChecker.nullPostCheck(inputList);

        switch (orderIn) {

            case ASC -> Collections.sort(listToSort, comparing(UserPost::getAuthor, nullsLast(naturalOrder())));

            case DESC -> Collections.sort(listToSort, Collections.reverseOrder(comparing(UserPost::getAuthor, nullsFirst(naturalOrder()))));
        }
        return listToSort;
    }
}
