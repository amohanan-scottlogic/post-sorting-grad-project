package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthorFilter implements PostFilter {

    String name;

    public AuthorFilter(String criteria) {
        name = criteria;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        List<UserPost> nullsRemoved = new ArrayList<>();
        if (name == null) return filteredList;
        for (UserPost userPost : inputList) {
            if (userPost.getAuthor() != null) {
                nullsRemoved.add(userPost);
            }
        }

        for (UserPost userPost : nullsRemoved) {
            if (StringUtils.containsIgnoreCase(userPost.getAuthor(), name)) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
