package com.scottlogic;

import java.util.ArrayList;
import java.util.List;

public class AuthorFilter implements PostFilter {

    String name;

    public AuthorFilter (String criteria){
        name = criteria;
    }
    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        if(name==null) return filteredList;

        for (UserPost userPost : inputList) {
            String postName = userPost.getAuthor();
            if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(postName, name)) {filteredList.add(userPost);}
        }
        return filteredList;
    }
}
