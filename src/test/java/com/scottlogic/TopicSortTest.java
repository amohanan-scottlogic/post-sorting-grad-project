package com.scottlogic;

import org.junit.Test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TopicSortTest {
    TopicSort sortByTopicFrequency = new TopicSort();
    private UserPost createUserPost(String content) {
        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }
    @Test
    public void topicSorter_mixedPosts_sortedByTopicReturned() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost userPost2 = createUserPost("Cats, and  then cats are loveable cats! He said");
        UserPost userPost3 = createUserPost("Dogs, dogs,dogs! She said");
        UserPost userPost4 = createUserPost("Cats make good friends with cats");
        List<UserPost> userPosts = Arrays.asList(userPost1,userPost2,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.topicSorter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2,userPost4,userPost3,userPost1);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_nullPosts_nullPostIgnored() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost nullPost = null;
        UserPost userPost3 = createUserPost("Dogs, dogs,dogs! She said");
        UserPost userPost4 = createUserPost("Cats make good friends with cats");
        List<UserPost> userPosts = Arrays.asList(userPost1,nullPost,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.topicSorter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost4,userPost3,userPost1);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_emptyList_emptyListReturned() throws IOException {

        List<UserPost> userPosts = new ArrayList<>();
        List<UserPost> actualResult = sortByTopicFrequency.topicSorter(userPosts);
        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_nullList_emptyListReturned() throws IOException {

        List<UserPost> userPosts = null;
        List<UserPost> actualResult = sortByTopicFrequency.topicSorter(userPosts);
        assertTrue(actualResult.isEmpty());
    }

}