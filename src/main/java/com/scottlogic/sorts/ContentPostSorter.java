package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.*;
import java.util.stream.Collectors;


public class ContentPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> contentNull = new ArrayList<>();
        List<UserPost> remainingPosts = new ArrayList<>();
        List<UserPost> listToBeSorted = new ArrayList<>();
        if(inputList==null) {return remainingPosts;}
        listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        for (UserPost userPost : listToBeSorted) {
            if (userPost.getContents() == null)
                contentNull.add(userPost);
            else
                remainingPosts.add(userPost);
        }
        remainingPosts = (orderIn.compareTo(SortOrder.ASC)==0) ?
            remainingPosts.stream().sorted(Comparator.nullsFirst(Comparator.comparing(post -> post.getContents().length()))).collect(Collectors.toList()) :
            remainingPosts.stream().sorted((Collections.reverseOrder(Comparator.comparing(post -> post.getContents().length())))).collect(Collectors.toList());

        if (!contentNull.isEmpty())
            remainingPosts.addAll(contentNull);
        return remainingPosts;
    }
}
