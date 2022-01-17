package com.scottlogic.filters;

import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FilterOR implements PostFilter{
    PostFilter filterA;
    PostFilter filterB;

    public FilterOR(PostFilter filterA, PostFilter filterB) {
        this.filterA = filterA;
        this.filterB = filterB;
    }

    public List<UserPost> filter(List<UserPost> inputList) {

        List<UserPost> listFiltered = filterA.filter(inputList);
        List<UserPost> listFilteredB = filterB.filter(inputList);

        Set<UserPost> set = new LinkedHashSet<>(listFiltered);
        set.addAll(listFilteredB);

        return new ArrayList<>(set);
    }
}
