package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import com.scottlogic.filters.ContentFilter;

import java.util.ArrayList;
import java.util.List;

public class KeywordSearch {

    public List<UserPost> keywordSearchSort(List<UserPost> inputList, String keyWord) {

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
        }
        int tempOrder;
        int tempCount;
        int[] order = new int[count.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }

        for (int i = 0; i < listToBeSorted.size(); i++) {
            for (int j = 0; j < listToBeSorted.size(); j++) {
                if (count[i] > count[j]) {
                    tempCount = count[i];
                    tempOrder = order[i];
                    count[i] = count[j];
                    order[i] = order[j];
                    count[j] = tempCount;
                    order[j] = tempOrder;
                }
            }
        }

        List<UserPost> sortedList = new ArrayList<>();
        for (int i = 0; i < listToBeSorted.size(); i++) {
            sortedList.add(listToBeSorted.get(order[i]));
        }
        return sortedList;
    }
}
