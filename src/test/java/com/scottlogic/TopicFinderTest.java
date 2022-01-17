package com.scottlogic;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TopicFinderTest {

    private UserPost createUserPost(String content) {
        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }



    TopicFinder topicFind = new TopicFinder();


    @ParameterizedTest
    @ValueSource(strings = {"An example of a post \nwith line breaks.", "An! example, of a post with line breaks","An! example, of a- post with line breaks"})
    public void topicFinder_generalPostParameterized_stopWordsPunctuationRemoved(String content)  {

        UserPost userPost = createUserPost(content);

        List<String> actualResult = topicFind.findTopics(userPost);
        List<String> expectedResult = new ArrayList<String>(Arrays.asList("example", "post", "line", "breaks"));

        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideContentForParameterizedTests")
    public void topicFinder_parametersThroughMethod_stopWordsPunctuationRemoved(String content, List<String> expectedResult)  {


        UserPost userPost = createUserPost(content);

        List<String> actualResult = topicFind.findTopics(userPost);

        assertEquals(expectedResult, actualResult);
    }
    private static Stream<Arguments> provideContentForParameterizedTests() {
        return Stream.of(
          Arguments.of("An example of a post \nwith line breaks.",Arrays.asList("example","post","line","breaks")),
                Arguments.of("I love cats and dogs",Arrays.asList("love","cats","dogs")),
                Arguments.of("I and this",Arrays.asList())

        );
    }
    @Test
    public void topicFinder_validPost_emptyStringReturned() {

        UserPost userPost = createUserPost("An example of a post \nwith line breaks.");

        List<String> actualResult = topicFind.findTopics(userPost);
        List<String> expectedResult = new ArrayList<String>(Arrays.asList("example", "post", "line", "breaks"));

        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void topicFinder_nullPost_emptyStringReturned() {

        List<String> actualResult = topicFind.findTopics(null);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void topicFinder_emptyPost_emptyStringReturned() {

        UserPost userPost = createUserPost("");

        List<String> actualResult = topicFind.findTopics(userPost);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicFinder_nullContent_emptyStringReturned() {

        UserPost userPost = createUserPost(null);

        List<String> actualResult = topicFind.findTopics(userPost);

        assertTrue(actualResult.isEmpty());
    }
}