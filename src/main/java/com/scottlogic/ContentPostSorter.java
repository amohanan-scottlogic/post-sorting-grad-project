package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class ContentPostSorter implements PostSorter{
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        switch (orderIn) {

            case ASC -> Collections.sort(inputList, Comparator.comparing(UserPost::getContents, (content1, content2) -> {
                if (content1.length() == content2.length()) {
                    return 0;
                }
                if (content1.length() < content2.length()) {
                    return 1;
                }
                else
                    return -1;

            }));//String::length

            case DESC -> Collections.sort(inputList, Collections.reverseOrder(comparing(UserPost::getContents)));
        }

        return inputList;
    }
}
//movies.sort(Comparator.comparing(Movie::getStarred, (star1, star2) -> {
//    if(star1 == star2){
//         return 0;
//    }
//    return star1 ? -1 : 1;
//}));