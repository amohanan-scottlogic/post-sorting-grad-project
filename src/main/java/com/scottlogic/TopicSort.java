package com.scottlogic;

import com.scottlogic.sorts.KeywordSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List <String> list = Stream.of(topics).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        Map <String, Integer > wordCounter = list.stream()
                .collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        wordCounter.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap.entrySet().iterator().next().getKey();

    }
}
