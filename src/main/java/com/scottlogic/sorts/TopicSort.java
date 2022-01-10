package com.scottlogic.sorts;

import com.scottlogic.NullPostChecker;
import com.scottlogic.TopicFinder;
import com.scottlogic.UserPost;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopicSort implements PostSorter {

    public TreeMap<String, List<UserPost>> topicMap = new TreeMap<>();
    private List<UserPost> finalList = new ArrayList<>();

    public List<UserPost> sort(List<UserPost> inputList, SortOrder orderIn) {
        if (inputList == null) {
            return finalList;
        }
        List<UserPost> listToBeSorted = NullPostChecker.nullPostCheck(inputList);

        File file = new File("C:/Dev/post-sorting-grad-project/src/main/resources/English_StopWords.txt");
        List<String> stopWords = null;
        try {
            stopWords = Files.readAllLines(Paths.get(String.valueOf(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TopicFinder findTopics = new TopicFinder(stopWords);
        for (UserPost userPost : listToBeSorted) {
            String[] topics = findTopics.findTopics(userPost).toArray(new String[0]);
            String mainTopic = mainTopicFinder(topics);
            addToMap(mainTopic, userPost);
        }
        for (Map.Entry<String, List<UserPost>> entry : topicMap.entrySet()) {
            List<UserPost> tempUserPost = entry.getValue();
            KeywordSort sortByFrequencyOfTopic = new KeywordSort(entry.getKey());
            List<UserPost> tempSortedUserPost = sortByFrequencyOfTopic.sort(tempUserPost, orderIn);
            finalList.addAll(tempSortedUserPost);
        }
        return finalList;
    }

    private void addToMap(String mainTopic, UserPost userPost) {
        if ((!topicMap.containsKey(mainTopic))) {
            topicMap.put(mainTopic, new ArrayList<UserPost>());
        }
        topicMap.get(mainTopic).add(userPost);
    }

    private String mainTopicFinder(String[] topics) {

        HashMap<String, Integer> hs = new HashMap<String, Integer>();
        for (String topic : topics) {
            if (hs.containsKey(topic)) {
                hs.put(topic, hs.get(topic) + 1);
            } else {
                hs.put(topic, 1);
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
