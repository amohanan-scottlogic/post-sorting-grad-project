package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorFilter implements PostFilter {

    String name;

    public AuthorFilter(String criteria) {
        name = criteria;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();

        if (name == null || inputList == null) {
            return filteredList;
        }

        filteredList = inputList.stream().filter(Objects::nonNull)
                .filter(post -> StringUtils.containsIgnoreCase(post.getAuthor(), name))
                .collect(Collectors.toList());

        return filteredList;
    }
}
