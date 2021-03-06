package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopicSortTest {
    TopicSort sortByTopicFrequency = new TopicSort();
    private UserPost createUserPost(String content) {
        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }
    @Test
    public void topicSorter_mixedPostsAsc_sortedByTopicReturned() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost userPost2 = createUserPost("Cats make good friends with cats.Oh cats, oh cats, oh cats");
        UserPost userPost3 = createUserPost("Dogs, dogs,dogs! She said");
        UserPost userPost4 = createUserPost("Cats, and  then cats are loveable cats! He said");

        List<UserPost> userPosts = Arrays.asList(userPost1,userPost2,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost4,userPost2,userPost3,userPost1);

        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_nullPostsAsc_nullPostIgnored() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost userPost3 = createUserPost("Cats, and  then cats are loveable cats! Cat said");
        UserPost userPost4 = createUserPost("Cats make good friends with cats");
        List<UserPost> userPosts = Arrays.asList(userPost1,null,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost4,userPost3,userPost1);

        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_emptyListAsc_emptyListReturned() throws IOException {

        List<UserPost> userPosts = new ArrayList<>();

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.ASC);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_nullListAsc_emptyListReturned() throws IOException {

        List<UserPost> actualResult = sortByTopicFrequency.sort(null, SortOrder.ASC);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_noTopicAsc_emptyListReturned() throws IOException {
        UserPost userPost1 = createUserPost("I  and ");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts =Arrays.asList(userPost1,userPost2);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.ASC);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_mixedPostsDesc_sortedByTopicReturned() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost userPost2 = createUserPost("Cats, and  then cats are loveable cats! He said");
        UserPost userPost3 = createUserPost("Dogs, dogs,dogs! She said");
        UserPost userPost4 = createUserPost("Cats make good friends with cats.Oh cats, oh cats, oh cats");
        List<UserPost> userPosts = Arrays.asList(userPost1,userPost2,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost4,userPost2,userPost3,userPost1);

        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_nullPostsDesc_nullPostIgnored() throws IOException {
        UserPost userPost1 = createUserPost("I love cats and Dogs");
        UserPost userPost3 = createUserPost("Dogs, dogs,dogs! She said");
        UserPost userPost4 = createUserPost("Cats make good friends with cats");
        List<UserPost> userPosts = Arrays.asList(userPost1,null,userPost3,userPost4);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost4,userPost3,userPost1);

        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void topicSorter_emptyListDesc_emptyListReturned() throws IOException {

        List<UserPost> userPosts = new ArrayList<>();

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.DESC);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_nullListDesc_emptyListReturned() throws IOException {

        List<UserPost> actualResult = sortByTopicFrequency.sort(null, SortOrder.DESC);

        assertTrue(actualResult.isEmpty());
    }
    @Test
    public void topicSorter_noTopicDesc_emptyListReturned() throws IOException {

        UserPost userPost1 = createUserPost("I  and ");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts =Arrays.asList(userPost1,userPost2);

        List<UserPost> actualResult = sortByTopicFrequency.sort(userPosts, SortOrder.DESC);

        assertTrue(actualResult.isEmpty());
    }

}