package com.scottlogic.filters;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContentFilter implements PostFilter {

    String keyWord;

    public ContentFilter(String criteria) {
        keyWord = criteria;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        List<UserPost> nullsRemoved = new ArrayList<>();
        if (keyWord == null) {
            return filteredList;
        }
        if(inputList==null) {
            return filteredList;
        }
        List<UserPost> listToBeFiltered = NullPostChecker.nullPostCheck(inputList);
        for (UserPost userPost : listToBeFiltered) {
            if (userPost.getContents() != null) nullsRemoved.add(userPost);
        }
        filteredList = nullsRemoved.stream().filter(post -> StringUtils.containsIgnoreCase(post.getContents(), keyWord)).collect(Collectors.toList());

        return filteredList;
    }
}
