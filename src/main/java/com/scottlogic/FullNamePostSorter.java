package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FullNamePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> namesNull = new ArrayList<>();
        List<UserPost> namesSpace = new ArrayList<>();
        List<UserPost> remainingPosts = new ArrayList<>();

        for (UserPost userPost : inputList) {
            if (userPost.getAuthor() == null)
                namesNull.add(userPost);
            else if (userPost.getAuthor().isBlank())
                namesSpace.add(userPost);
            else
                remainingPosts.add(userPost);
        }
        switch (orderIn) {

            case ASC -> Collections.sort(remainingPosts, Comparator.comparing(UserPost::getAuthor, (name1, name2) -> {
                String firstName1;
                String firstName2;
                String lastName1;
                String lastName2;

                String[] split1 = name1.split(" ");
                firstName1 = split1[0];
                if (split1.length == 1) lastName1 = " ";
                else lastName1 = split1[1];
                String[] split2 = name2.split(" ");
                firstName2 = split2[0];
                if (split2.length == 1) lastName2 = " ";
                else lastName2 = split2[1];

                if (lastName1.compareTo(lastName2) == 0) {

                    if (firstName1.compareTo(firstName2) == 0)
                        return 0;
                    else if (firstName1.compareTo(firstName2) > 0)
                        return 1;
                    else return -1;

                } else if (lastName1.compareTo(lastName2) > 0)
                    return 1;
                else return -1;
            }));

            case DESC -> Collections.sort(remainingPosts, Comparator.comparing(UserPost::getAuthor, (name1, name2) -> {
                String firstName1;
                String firstName2;
                String lastName1;
                String lastName2;

                String[] split1 = name1.split(" ");
                firstName1 = split1[0];
                if (split1.length == 1) lastName1 = " ";
                else lastName1 = split1[1];
                String[] split2 = name2.split(" ");
                firstName2 = split2[0];
                if (split2.length == 1) lastName2 = " ";
                else lastName2 = split2[1];

                if (lastName1.compareTo(lastName2) == 0) {

                    if (firstName1.compareTo(firstName2) == 0)
                        return 0;
                    else if (firstName1.compareTo(firstName2) > 0)
                        return -1;
                    else return 1;

                } else if (lastName1.compareTo(lastName2) > 0)
                    return -1;
                else return 1;
            }));
        }
        if (namesSpace != null) {
            remainingPosts.addAll(namesSpace);
        }
        if (namesNull != null) {
            remainingPosts.addAll(namesNull);
        }

        return remainingPosts;
    }
}
