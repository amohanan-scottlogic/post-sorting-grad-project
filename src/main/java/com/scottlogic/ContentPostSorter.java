package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContentPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        List<UserPost> contentNull = new ArrayList<>();

        List<UserPost> remainingPosts = new ArrayList<>();

        for (UserPost userPost : inputList) {
            if (userPost.getContents() == null)
                contentNull.add(userPost);
            else
                remainingPosts.add(userPost);
        }
        switch (orderIn) {
            case ASC -> Collections.sort(remainingPosts, Comparator.nullsFirst(Comparator.comparing(post -> post.getContents().length())));
            case DESC -> Collections.sort(remainingPosts, Collections.reverseOrder(Comparator.comparing(post -> post.getContents().length())));
        }
        if (!contentNull.isEmpty())
            remainingPosts.addAll(contentNull);
        return remainingPosts;
    }
}
