package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
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

        List<UserPost> listSorted = listToBeSorted.stream()
                .filter(post -> post.getDateTime() != null)
                .sorted(orderIn.isAscending() ? PostDate : PostDate.reversed())
                .collect(Collectors.toList());

        if (listSorted.size() != listToBeSorted.size()) {
            List<UserPost> nullsInList = listToBeSorted.stream().filter(post -> post.getDateTime() == null).toList();
            listSorted.addAll(nullsInList);
        }
        return listSorted;
    }

    public Comparator<UserPost> PostDate = this::dateSort;

    private int dateSort(UserPost u1, UserPost u2) {
        if (u1.getDateTime() == u2.getDateTime()) {
            return 0;
        } else {
            return u1.getDateTime().isAfter(u2.getDateTime()) ? 1 : -1;
        }
    }

}
