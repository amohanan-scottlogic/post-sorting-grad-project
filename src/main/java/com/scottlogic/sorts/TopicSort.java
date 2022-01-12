package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.TopicFinder;
import com.scottlogic.UserPost;

import java.util.*;

public class TopicSort implements PostSorter {

    public TreeMap<String, List<UserPost>> topicMap = new TreeMap<>();
    private final List<UserPost> finalList = new ArrayList<>();

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        if (inputList == null) {
            return finalList;
        }
        List<UserPost> listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        TopicFinder findTopics = new TopicFinder();
        for (UserPost userPost : listToBeSorted) {
            List<String> topics = findTopics.findTopics(userPost);
            String mainTopic = mainTopicFinder(topics);
            addToMap(mainTopic, userPost);
        }
        for (String key : topicMap.keySet()) {
            List<UserPost> tempUserPost = topicMap.get(key);
            KeywordSort sortByFrequencyOfTopic = new KeywordSort(key);
            List<UserPost> tempSortedUserPost = sortByFrequencyOfTopic.sort(tempUserPost, orderIn);
            finalList.addAll(tempSortedUserPost);
        }
        return finalList;
    }

    private void addToMap(String mainTopic, UserPost userPost) {

        if (!mainTopic.isEmpty()) {
            topicMap.putIfAbsent(mainTopic, new ArrayList<>());
            topicMap.get(mainTopic).add(userPost);
        }
    }

    private String mainTopicFinder(List<String> topics) {

        Map<String, Integer> allTopicMap = new HashMap<>();
        for (String topic : topics) {

            allTopicMap.putIfAbsent(topic, 0);
            allTopicMap.computeIfPresent(topic, (topicName, count) -> count + 1);
        }


        String mainTopic = "";
        int value = 0;

        for (String key : allTopicMap.keySet()) {
            if (allTopicMap.get(key) > value) {
                value = allTopicMap.get(key);
                mainTopic = key;
            }
        }
        return mainTopic;
    }
}
