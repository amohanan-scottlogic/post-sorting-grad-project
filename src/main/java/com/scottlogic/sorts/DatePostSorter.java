package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToBeSorted = new ArrayList<>();
        if (inputList == null) {
            return listToBeSorted;
        }
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        switch (orderIn) {

            case ASC -> listToBeSorted = listToBeSorted.stream().sorted(Comparator.nullsLast(Comparator.comparing(UserPost::getDateTime, Comparator.nullsLast(Comparator.naturalOrder())))).collect(Collectors.toList());

            case DESC -> listToBeSorted = listToBeSorted.stream().sorted( Collections.reverseOrder(Comparator.nullsLast(Comparator.comparing(UserPost::getDateTime, Comparator.nullsFirst(Comparator.naturalOrder()))))).collect(Collectors.toList());
        }
        return listToBeSorted;
    }
}
