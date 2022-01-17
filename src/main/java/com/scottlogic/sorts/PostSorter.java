package com.scottlogic.sorts;

import com.scottlogic.UserPost;

import java.util.List;

public interface PostSorter {

    List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn);

}