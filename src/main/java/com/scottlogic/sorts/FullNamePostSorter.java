package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FullNamePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> listToBeSorted = new ArrayList<>();
        if (inputList == null) {
            return listToBeSorted;
        }
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);
        List<UserPost> namesNull = new ArrayList<>();
        List<UserPost> namesSpace = new ArrayList<>();
        List<UserPost> remainingPosts = new ArrayList<>();

        for (UserPost userPost : listToBeSorted) {
            if (userPost.getAuthor() == null)
                namesNull.add(userPost);
            else if (userPost.getAuthor().isBlank())
                namesSpace.add(userPost);
            else
                remainingPosts.add(userPost);
        }
        switch (orderIn) {

            case ASC -> Collections.sort(remainingPosts, FullName);

            case DESC -> Collections.sort(remainingPosts, FullName.reversed());

        }
        if (namesNull != null) {
            remainingPosts.addAll(namesNull);
        }
        if (namesSpace != null) {
            if (orderIn == SortOrder.ASC) {
                namesSpace.addAll(remainingPosts);
                return namesSpace;
            } else {
                remainingPosts.addAll(namesSpace);
            }

        }

        return remainingPosts;
    }

    public String[] nameSplit(String name) {

        String[] splitted = name.split(" ");
        String[] names = new String[2];
        int numberOfNames = splitted.length;
        names[0] = splitted[0];
        names[1] = numberOfNames > 1 ? splitted[numberOfNames - 1] : " ";

        return names;
    }

    public Comparator<UserPost> FullName = (o1, o2) -> {
        String[] splitName1 = nameSplit(o1.getAuthor());
        String[] splitName2 = nameSplit(o2.getAuthor());
        if (splitName1[1].compareTo(splitName2[1]) == 0) {
            if (splitName1[0].compareTo(splitName2[0]) == 0) {
                return 0;
            } else {
                return (splitName1[0].compareTo(splitName2[0]) > 0) ? 1 : -1;
            }
        } else {
            return (splitName1[1].compareTo(splitName2[1]) > 0) ? 1 : -1;
        }

    };
}