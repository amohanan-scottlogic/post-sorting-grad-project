package com.scottlogic;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TopicFinderTest {


    private static List<String> stopWords;

    private UserPost createUserPost(String content) {
        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }

    @BeforeClass
    public static void loadStopWords() throws IOException {
        File file = new File("C:/Dev/post-sorting-grad-project/src/main/resources/English_StopWords.txt");
        stopWords = Files.readAllLines(Paths.get(String.valueOf(file)));
    }

    TopicFinder topicFind = new TopicFinder(stopWords);

    @ParameterizedTest
    @ValueSource(strings = {"An example of a post \nwith line breaks.", "An! example, of a post with line breaks","An! example, of a- post with line breaks"})
    public void topicFinder_generalPostParameterized_stopWordsPunctuationRemoved(String content) throws IOException {

        File file = new File("C:/Dev/post-sorting-grad-project/src/main/resources/English_StopWords.txt");
        stopWords = Files.readAllLines(Paths.get(String.valueOf(file)));
        TopicFinder topicFind = new TopicFinder(stopWords);
        UserPost userPost = createUserPost(content);

        ArrayList<String> actualResult = topicFind.findTopics(userPost);
        ArrayList<String> expectedResult = new ArrayList<String>(Arrays.asList("example", "post", "line", "breaks"));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void topicFinder_nullPost_emptyStringReturned() {

        ArrayList<String> actualResult = topicFind.findTopics(null);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void topicFinder_emptyPost_emptyStringReturned() {

        UserPost userPost = createUserPost("");

        ArrayList<String> actualResult = topicFind.findTopics(userPost);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicFinder_nullContent_emptyStringReturned() {

        UserPost userPost = createUserPost(null);

        ArrayList<String> actualResult = topicFind.findTopics(userPost);

        assertTrue(actualResult.isEmpty());
    }
}