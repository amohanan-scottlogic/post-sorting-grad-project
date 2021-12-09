package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        switch (orderIn) {

            case ASC -> Collections.sort(inputList, Comparator.nullsLast(Comparator.comparing(UserPost::getDateTime, Comparator.nullsLast(Comparator.naturalOrder()))));

            case DESC -> Collections.sort(inputList, Collections.reverseOrder(Comparator.nullsLast(Comparator.comparing(UserPost::getDateTime, Comparator.nullsFirst(Comparator.naturalOrder())))));
        }
        return inputList;
    }
}
