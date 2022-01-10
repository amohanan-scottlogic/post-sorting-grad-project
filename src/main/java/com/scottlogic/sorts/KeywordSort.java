package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import com.scottlogic.filters.ContentFilter;

import java.util.*;

public class KeywordSort implements PostSorter {
    String keyWord;
    public KeywordSort (String keyWord) {
        this.keyWord = keyWord;
    }

    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        TreeMap<Integer, UserPost> sortedMap = new TreeMap<>();
        List<UserPost> finalList =new ArrayList<>();
        ContentFilter filterByKeyWord = new ContentFilter(keyWord);

        List<UserPost> listToBeSorted = filterByKeyWord.filter(inputList);
        int[] count = new int[listToBeSorted.size()];
        for (int i = 0; i < listToBeSorted.size(); i++) {
            String content = listToBeSorted.get(i).getContents();
            count[i] = 0;
            String[] words = content.split(" ");

            for (int j = 0; j < words.length; j++) {
                if (words[j].compareToIgnoreCase(keyWord) == 0) {
                    count[i]++;
                }
            }
            sortedMap.put(count[i], listToBeSorted.get(i));
        }
        for (Map.Entry<Integer, UserPost> entry : sortedMap.entrySet()) {
           UserPost unsortedList =  entry.getValue();
           finalList.add(unsortedList);
        }
        if(orderIn==SortOrder.DESC) {
            Collections.reverse(finalList);
        }
        return finalList;
    }
}
