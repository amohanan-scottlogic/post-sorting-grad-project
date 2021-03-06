package com.scottlogic.filters;

import com.scottlogic.UserPost;

import java.util.List;

public class FilterAND implements PostFilter {

    PostFilter filterA;
    PostFilter filterB;

    public FilterAND(PostFilter filterA, PostFilter filterB) {
            this.filterA = filterA;
            this.filterB = filterB;
    }

    public List<UserPost> filter(List<UserPost> inputList) {

        List<UserPost> listToBeFiltered = inputList;

        listToBeFiltered = filterA.filter(listToBeFiltered);
        listToBeFiltered = filterB.filter(listToBeFiltered);

        return listToBeFiltered;
    }
}
