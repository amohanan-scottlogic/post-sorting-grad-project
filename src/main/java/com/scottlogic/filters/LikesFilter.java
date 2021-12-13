package com.scottlogic.filters;

import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;

public class LikesFilter implements PostFilter {
    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        for (UserPost userPost : inputList) {
            if (userPost.getLikeCount() >= 1) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
