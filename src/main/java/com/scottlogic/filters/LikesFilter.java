package com.scottlogic.filters;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;

public class LikesFilter implements PostFilter {
    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        List<UserPost> listToBeFiltered = new ArrayList<>();
        List<UserPost> filteredList = new ArrayList<>();
        if(inputList==null) {
            return filteredList;
        }
        listToBeFiltered = NullPostChecker.nullPostCheck(inputList);
        for (UserPost userPost : listToBeFiltered) {
            if (userPost.getLikeCount() >= 1) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
