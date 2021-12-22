package com.scottlogic;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public  class TopicFinderTest {


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

    @Test
    public void topicFinder_generalPost_stopWordsPunctuationRemoved()  {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        String actualResult = topicFind.topicFinder(userPost1);
        String expectedResult = "example post lines breaks";
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicFinder_nullPost_emptyStringReturned()  {
        String actualResult = topicFind.topicFinder(null);
        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicFinder_emptyPost_emptyStringReturned()  {
        UserPost userPost = createUserPost("");
        String actualResult = topicFind.topicFinder(userPost);
        assertTrue(actualResult.isEmpty());
    }
}