package com.scottlogic.sorts;

import com.scottlogic.UserPost;

import java.util.List;

public class SorterCombo implements PostSorter{
    PostSorter sorterA;
    PostSorter sorterB;

    public SorterCombo(PostSorter sorterA, PostSorter sorterB){
        this.sorterA = sorterA;
        this.sorterB = sorterB;
    }


    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        List<UserPost> sortedBySortB = sorterB.sort(inputList, orderIn);
        List<UserPost> sortedBySortA = sorterA.sort(sortedBySortB, orderIn);

        return sortedBySortA;
    }
}
