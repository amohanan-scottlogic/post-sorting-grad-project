package com.scottlogic;

import java.util.ArrayList;
import java.util.List;

public class AuthorFilter implements PostFilter {
    @Override
    public List<UserPost> filter(List<UserPost> inputList, String criteria) {
        List<UserPost> filteredList = new ArrayList<>();
        if(criteria==null) return filteredList;
        for (UserPost userPost : inputList) {
            if (userPost.getAuthor().toLowerCase().contains(criteria.toLowerCase())) filteredList.add(userPost);
        }
        return filteredList;
    }
}
