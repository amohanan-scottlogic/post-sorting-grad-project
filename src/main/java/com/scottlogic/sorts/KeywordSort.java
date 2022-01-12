package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import com.scottlogic.filters.ContentFilter;

import java.util.*;

public class KeywordSort implements PostSorter {
    private String keyWord;

    public KeywordSort (String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        TreeMap<Integer, UserPost> sortedMap = new TreeMap<>();
        List<UserPost> finalList =new ArrayList<>();
        ContentFilter filterByKeyWord = new ContentFilter(keyWord);

        List<UserPost> listToBeSorted = filterByKeyWord.filter(inputList);
        int[] count = new int[listToBeSorted.size()];

        for (int i = 0; i < listToBeSorted.size(); i++) {

            String[] words = splitContent(listToBeSorted.get(i));
            count[i] = checkForCountOfKeyWordInContent(words);
            sortedMap.put(count[i], listToBeSorted.get(i));
        }

        for ( UserPost userPost: sortedMap.values()) {
            finalList.add(userPost);
        }

        if(orderIn==SortOrder.DESC) {
            Collections.reverse(finalList);
        }

        return finalList;
    }

    private int checkForCountOfKeyWordInContent(String[] words) {

        int keyWordCount=0;
        for (String word : words) {
            if (word.contains(keyWord)) {
                keyWordCount++;
            }
        }
        return keyWordCount;
    }

    private String[] splitContent(UserPost userPost) {

        String content = userPost.getContents();

        return content.toLowerCase().split(" ");
    }
}
