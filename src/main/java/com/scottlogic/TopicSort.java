package com.scottlogic;

import com.scottlogic.sorts.KeywordSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopicSort {
    public TreeMap<String, List<UserPost>> topicMap = new TreeMap<>();
    List<UserPost> finalList = new ArrayList<>();
    KeywordSearch sortByFrequencyOfTopic = new KeywordSearch();

    public List<UserPost> topicSorter(List<UserPost> inputList) throws IOException {
        if(inputList==null) {
            return finalList;
        }
        File file = new File("C:/Dev/post-sorting-grad-project/src/main/resources/English_StopWords.txt");
        List<String> stopWords = Files.readAllLines(Paths.get(String.valueOf(file)));
        TopicFinder findTopics = new TopicFinder(stopWords);
        for (UserPost userPost : inputList) {
            String[] topics = findTopics.topicFinder(userPost).split(" ");
            String mainTopic = mainTopicFinder(topics);
            System.out.println(mainTopic);
            addToMap(mainTopic, userPost);
        }
        for (Map.Entry<String, List<UserPost>> entry : topicMap.entrySet()) {
            List<UserPost> tempUserPost = entry.getValue();
            List<UserPost> tempSortedUserPost = sortByFrequencyOfTopic.keywordSearchSort(tempUserPost, entry.getKey());
            Collections.reverse(tempSortedUserPost);
            finalList.addAll(tempSortedUserPost);
        }
        return finalList;
    }

    private void addToMap(String mainTopic, UserPost userPost) {
        if((!topicMap.containsKey(mainTopic))) {
            topicMap.put(mainTopic, new ArrayList<UserPost>());
        }
        topicMap.get(mainTopic).add(userPost);
    }

    public String mainTopicFinder(String[] topics) {

        HashMap<String, Integer> hs = new HashMap<String, Integer>();
        for (int i = 0; i < topics.length; i++) {
            if (hs.containsKey(topics[i])) {
                hs.put(topics[i], hs.get(topics[i]) + 1);
            } else {
                hs.put(topics[i], 1);
            }
        }
        Set<Map.Entry<String, Integer>> set = hs.entrySet();
        String key = "";
        int value = 0;

        for (Map.Entry<String, Integer> me : set) {
            if (me.getValue() > value) {
                value = me.getValue();
                key = me.getKey();
            }
        }
        return key;
    }
}
