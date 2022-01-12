package com.scottlogic.filters;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikesFilter implements PostFilter {
    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        List<UserPost> listToBeFiltered;
        List<UserPost> filteredList = new ArrayList<>();
        if (inputList == null) {
            return filteredList;
        }
        listToBeFiltered = NullPostChecker.nullPostCheck(inputList);

        filteredList = listToBeFiltered.stream()
                .filter(post -> (post.getLikeCount() > 0))
                .collect(Collectors.toList());

        return filteredList;
    }
}
