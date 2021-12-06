package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AuthorPostSorter implements PostSorter {

        @Override
    public List<UserPost> authorSorter(List<UserPost> inputList) {

            for (int i=0;i<inputList.size(); i++) {
                if(inputList.get(i).getAuthor()==null){
                    System.out.println ("Sort cannot be done");
                    return null;
                }
            }

            Collections.sort(inputList, Comparator.comparing(UserPost::getAuthor) );

            return inputList;


    }
}
