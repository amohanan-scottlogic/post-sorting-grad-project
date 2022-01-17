package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ContentPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> contentNull = new ArrayList<>();
        List<UserPost> remainingPosts = new ArrayList<>();
        List<UserPost> listToBeSorted;
        if (inputList == null) {
            return remainingPosts;
        }
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        for (UserPost userPost : listToBeSorted) {
            if (userPost.getContents() == null)
                contentNull.add(userPost);
            else
                remainingPosts.add(userPost);
        }
        switch (orderIn) {
            case ASC -> remainingPosts.sort(Comparator.nullsFirst(Comparator.comparing(post -> post.getContents().length())));
            case DESC -> remainingPosts.sort(Collections.reverseOrder(Comparator.comparing(post -> post.getContents().length())));
        }
        if (!contentNull.isEmpty())
            remainingPosts.addAll(contentNull);
        return remainingPosts;
    }
}
