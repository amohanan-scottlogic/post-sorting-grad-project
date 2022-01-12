package com.scottlogic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TopicFinder {

    public List<String> findTopics(UserPost inputPost) {

        List<String> result = new ArrayList<>();
        String filePath = "src/main/resources/English_StopWords.txt";
        File file = new File(filePath);
        List<String> stopWords = null;
        try {
            stopWords = Files.readAllLines(Paths.get(String.valueOf(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputPost == null || inputPost.getContents() == null || inputPost.getContents().isEmpty()) {
            return result;
        }
        
        String content = inputPost.getContents();
        String[] allWords = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        for (String word : allWords) {
            if (!stopWords.contains(word)) {
                result.add(word);
            }
        }

        return result;
    }
}
