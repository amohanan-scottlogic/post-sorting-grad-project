package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ContentFilter implements PostFilter {

    String keyWord;

    public ContentFilter(String criteria) {
        keyWord = criteria;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        List<UserPost> nullsRemoved = new ArrayList<>();
        if (keyWord == null) return filteredList;
        for (UserPost userPost : inputList) {
            if (userPost.getContents() != null) nullsRemoved.add(userPost);
        }
        for (UserPost userPost : nullsRemoved) {
            if (StringUtils.containsIgnoreCase(userPost.getContents(), keyWord)) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
