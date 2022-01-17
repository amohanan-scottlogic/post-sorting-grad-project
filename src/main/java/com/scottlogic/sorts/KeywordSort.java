package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import com.scottlogic.filters.ContentFilter;

import java.util.*;

public class KeywordSort implements PostSorter {
    private String keyWord;

    public KeywordSort(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {

        TreeMap<Integer, UserPost> sortedMap = new TreeMap<>();
        List<UserPost> finalList = new ArrayList<>();
        ContentFilter filterByKeyWord = new ContentFilter(keyWord);

        List<UserPost> listToBeSorted = filterByKeyWord.filter(inputList);
        int[] count = new int[listToBeSorted.size()];

        for (UserPost userPost : listToBeSorted) {

            sortedMap.put(countKeyWords(splitContent(userPost)), userPost);
        }

        finalList.addAll(sortedMap.values());

        if (orderIn == SortOrder.DESC) {
            Collections.reverse(finalList);
        }

        return finalList;
    }

    private int countKeyWords(String[] words) {

        int keyWordCount = 0;
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
