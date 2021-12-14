package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class ContentPostSorterTest {

    ContentPostSorter sortByContent = new ContentPostSorter();

    private UserPost createUserPost(String content) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }

    @Test
    public void sort_mixedLengthPostsAsc_correctListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_sameLengthPostsAsc_correctListReturned() {
        UserPost userPost1 = createUserPost("Hello World!");
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_newLineSamePostsAsc_correctListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("An example of a post with lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");
        UserPost userPost4 = createUserPost("An example of a post without line breaks");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_nullContentAsc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_nullPostAsc_nullRemovedReturned() {
        UserPost userPost1 = null;
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_mixedLengthPostsDesc_correctListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_sameLengthPostsDesc_correctListReturned() {
        UserPost userPost1 = createUserPost("Hello World!");
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_newLineSamePostsDesc_correctListReturned() {
        UserPost userPost1 = createUserPost("An example of a post with lines breaks.");
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");
        UserPost userPost4 = createUserPost("An example of a post without line breaks");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost4, userPost1, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_nullContentDesc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_nullPostDesc_nullRemovedReturned() {
        UserPost userPost1 = null;
        UserPost userPost2 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Hello World!");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByContent.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }
}