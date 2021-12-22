package com.scottlogic;

import java.util.ArrayList;
import java.util.List;


public class TopicFinder {

    List<String> stopWords = new ArrayList<>();

    public TopicFinder(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public String topicFinder(UserPost inputPost) {

        String result = "";
        if (inputPost != null) {
            String content = inputPost.getContents();
            String[] allWords = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
            StringBuilder builder = new StringBuilder();
            for (String word : allWords) {
                if (!stopWords.contains(word)) {
                    builder.append(word);
                    builder.append(' ');
                }
            }
            result = builder.toString().trim();
        }
        return result;

    }
}
