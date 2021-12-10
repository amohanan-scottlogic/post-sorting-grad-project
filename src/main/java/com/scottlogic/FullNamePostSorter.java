package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FullNamePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        NullPostChecker checkForNullPost = new NullPostChecker();
        inputList = checkForNullPost.nullPostCheck(inputList);
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

                String[] splitName1 = nameSplit(name1);
                String[] splitName2 = nameSplit(name2);
                if (splitName1[1].compareTo(splitName2[1]) == 0) {
                    if (splitName1[0].compareTo(splitName2[0]) == 0)
                        return 0;
                    else return  (splitName1[0].compareTo(splitName2[0]) > 0) ? 1 : -1;
                }
                else return  (splitName1[1].compareTo(splitName2[1]) > 0) ? 1 : -1;
            }));

            case DESC -> Collections.sort(remainingPosts, Comparator.comparing(UserPost::getAuthor, (name1, name2) -> {

                String[] splitName1 = nameSplit(name1);
                String[] splitName2 = nameSplit(name2);
                if (splitName1[1].compareTo(splitName2[1]) == 0) {
                    if (splitName1[0].compareTo(splitName2[0]) == 0)
                        return 0;
                    else return  (splitName1[0].compareTo(splitName2[0]) > 0) ? -1 : 1;
                }
                else return  (splitName1[1].compareTo(splitName2[1]) > 0) ? -1 : 1;
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

    private String[] nameSplit(String name) {

        String[] splitted = name.split(" ");
        String[] names = new String[2];
        int numberOfNames = splitted.length;
        names[0] = splitted[0];
        names[1] = numberOfNames > 1 ? splitted[numberOfNames - 1] : " ";

        return names;
    }
}
