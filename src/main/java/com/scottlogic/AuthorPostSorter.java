package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class AuthorPostSorter implements PostSorter {


    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> namesNull = new ArrayList<>();
        List<UserPost> remainingPosts = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).getAuthor() == null) {
                namesNull.add(inputList.get(i));
            } else {
                remainingPosts.add(inputList.get(i));
            }
        }


        switch (orderIn) {

            case ASC -> Collections.sort(remainingPosts, Comparator.comparing(UserPost::getAuthor));

            case DESC -> Collections.sort(remainingPosts, Collections.reverseOrder(comparing(UserPost::getAuthor)));
        }

        if (namesNull != null) {
            remainingPosts.addAll(namesNull);
        }

        return remainingPosts;


    }

}
