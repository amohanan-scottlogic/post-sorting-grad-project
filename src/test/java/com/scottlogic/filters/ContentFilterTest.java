package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentFilterTest {

    private UserPost createUserPost(String content) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }

    @Test
    public void filter_validKeywordSearch_correctListReturned() {
        ContentFilter filterByKeyword = new ContentFilter("example");
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        UserPost userPost3 = createUserPost("Hello World!");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = filterByKeyword.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullSearch_emptyReturned() {
        ContentFilter filterByKeyword = new ContentFilter(null);
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = filterByKeyword.filter(userPosts);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullContent_correctListReturned() {
        ContentFilter filterByKeyword = new ContentFilter("example");
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("Another example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = filterByKeyword.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullPostInList_nullIgnored() {
        ContentFilter filterByKeyword = new ContentFilter("post");
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null);

        List<UserPost> actualResult = filterByKeyword.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_emptyList_emptyListReturned() {
        ContentFilter filterByKeyword = new ContentFilter("post");

        List<UserPost> actualResult = filterByKeyword.filter(new ArrayList<>());

        Assert.assertTrue(actualResult.isEmpty());
    }
    @Test
    public void filter_nullList_emptyListReturned() {
        ContentFilter filterByKeyword = new ContentFilter("post");

        List<UserPost> actualResult = filterByKeyword.filter(null);

        Assert.assertTrue(actualResult.isEmpty());
    }
    @Test
    public void filter_searchItemNotInList_emptyListReturned() {
        ContentFilter filterByKeyword = new ContentFilter("cats");
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = filterByKeyword.filter(userPosts);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_validInput_inputListNotMutated() {
        ContentFilter filterByKeyword = new ContentFilter("post");
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);

        filterByKeyword.filter(userPosts);

        Assert.assertEquals(userPostsCopy, userPosts);
    }
}