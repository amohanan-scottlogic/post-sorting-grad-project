package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeywordSortTest {


    private UserPost createUserPost(String content) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }

    KeywordSort sortByKeyWord = new KeywordSort("example");

    @Test
    public void sort_mixedLengthPostsAsc_filteredSortedListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example example post.");
        UserPost userPost3 = createUserPost("Hello World!");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostsAsc_nullPostsRemoved() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, null, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullContentAsc_nullContentIgnored() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost nullContent = createUserPost(null);
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, nullContent, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullKeyWordAsc_emptyListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Hello!");
        UserPost userPost3 = createUserPost("Another example example post.");
        KeywordSort sortByKeyWord = new KeywordSort(null);


        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_nullListAsc_emptyListReturned() {

        List<UserPost> actualResult = sortByKeyWord.sort(null, SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_emptyListAsc_emptyListReturned() {

        List<UserPost> userPosts = new ArrayList<>();

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputAsc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Hello!");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> userPostCopy = userPosts;

        sortByKeyWord.sort(userPosts, SortOrder.ASC);

        Assert.assertEquals(userPosts, userPostCopy);
    }

    @Test
    public void sort_mixedLengthPostsDesc_filteredSortedListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example example post.");
        UserPost userPost3 = createUserPost("Hello World!");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostsDesc_nullPostsRemoved() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, null, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullContentDesc_nullContentIgnored() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost nullContent = createUserPost(null);
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, nullContent, userPost3);

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullKeyWordDesc_emptyListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Hello!");
        UserPost userPost3 = createUserPost("Another example example post.");
        KeywordSort sortByKeyWord = new KeywordSort(null);


        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_nullListDesc_emptyListReturned() {

        List<UserPost> actualResult = sortByKeyWord.sort(null, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_emptyListDesc_emptyListReturned() {

        List<UserPost> userPosts = new ArrayList<>();

        List<UserPost> actualResult = sortByKeyWord.sort(userPosts, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputDesc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Hello!");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> userPostCopy = userPosts;

        sortByKeyWord.sort(userPosts, SortOrder.DESC);

        Assert.assertEquals(userPosts, userPostCopy);
    }
}