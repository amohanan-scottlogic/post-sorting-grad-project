package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FullNamePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToBeSorted = new ArrayList<>();

        if (inputList == null) {
            return listToBeSorted;
        }

        listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        List<UserPost> listSorted = listToBeSorted.stream()
                .filter(post -> post.getAuthor() != null)
                .filter(post -> !post.getAuthor().isBlank())
                .sorted(orderIn.isAscending() ? FullName : FullName.reversed())
                .collect(Collectors.toList());

        if (listSorted.size() != listToBeSorted.size()) {
            List<UserPost> namesNull = listToBeSorted.stream().filter(post -> post.getAuthor() == null).toList();
            listSorted.addAll(namesNull);

            if (listSorted.size() != listToBeSorted.size()) {
                List<UserPost> namesBlank = listToBeSorted.stream().filter(post -> post.getAuthor().isBlank()).collect(Collectors.toList());

                if (orderIn.isAscending()) {
                    namesBlank.addAll(listSorted);
                    return namesBlank;
                } else {
                    listSorted.addAll(namesBlank);
                    return listSorted;
                }
            }
        }

        return listSorted;
    }

    public Comparator<UserPost> FullName = this::fullNameSort;

    private int fullNameSort(UserPost u1, UserPost u2) {
        String[] splitName1 = nameSplit(u1.getAuthor());
        String[] splitName2 = nameSplit(u2.getAuthor());
        if (splitName1[1].compareTo(splitName2[1]) == 0) {
            if (splitName1[0].compareTo(splitName2[0]) == 0) {
                return 0;
            } else {
                return (splitName1[0].compareTo(splitName2[0]) > 0) ? 1 : -1;
            }
        } else {
            return (splitName1[1].compareTo(splitName2[1]) > 0) ? 1 : -1;
        }
    }

    public String[] nameSplit(String name) {

        String[] splitted = name.split(" ");
        String[] names = new String[2];
        int numberOfNames = splitted.length;
        names[0] = splitted[0];
        names[1] = numberOfNames > 1 ? splitted[numberOfNames - 1] : " ";

        return names;
    }
}