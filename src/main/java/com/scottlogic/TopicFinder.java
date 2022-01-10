package com.scottlogic;

import java.util.ArrayList;
import java.util.List;


public class TopicFinder {

    List<String> stopWords;

    public TopicFinder(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public ArrayList<String> findTopics(UserPost inputPost) {

        ArrayList<String> result = new ArrayList<>();
        if (inputPost != null && inputPost.getContents() != null) {
            if (!inputPost.getContents().isEmpty()) {
                String content = inputPost.getContents();
                String[] allWords = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
                StringBuilder builder = new StringBuilder();
                for (String word : allWords) {
                    if (!stopWords.contains(word)) {
                        result.add(word);
                    }
                }
            }
        }
        return result;
    }
}
