package com.scottlogic.filters;

import com.scottlogic.UserPost;

import java.util.List;

public interface PostFilter {
    List<UserPost> filter(List<UserPost> inputList);
}
