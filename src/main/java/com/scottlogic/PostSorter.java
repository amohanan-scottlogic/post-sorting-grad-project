package com.scottlogic;

import java.util.List;

public interface PostSorter {
    //List<UserPost> sort(List<UserPost> inputList);
    List<UserPost> authorSorter(List<UserPost> inputList);
}