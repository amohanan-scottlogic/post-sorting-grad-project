package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeywordSearchTest {

    KeywordSearch sortByKeyWord = new KeywordSearch();

    private UserPost createUserPost(String content) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                content, 2);
    }

    @Test
    public void keywordSearchSort_mixedLengthPosts_filteredSortedListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost2 = createUserPost("Another example example post.");
        UserPost userPost3 = createUserPost("Hello World!");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(userPosts, "example");
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void keywordSearchSort_nullPosts_nullPostsRemoved() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, null, userPost3);

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(userPosts, "example");
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void keywordSearchSort_nullContent_nullContentIgnored() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost nullContent = createUserPost(null);
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, nullContent, userPost3);

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(userPosts, "example");
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void keywordSearchSort_nullKeyWord_emptyListReturned() {
        UserPost userPost1 = createUserPost("An example of a post \nwith lines breaks.");
        UserPost nullContent = createUserPost("Hello!");
        UserPost userPost3 = createUserPost("Another example example post.");
        List<UserPost> userPosts = Arrays.asList(userPost1, nullContent, userPost3);

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(userPosts, null);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void keywordSearchSort_nullList_emptyListReturned() {

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(null, "example");

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void keywordSearchSort_emptyList_emptyListReturned() {

        List<UserPost> actualResult = sortByKeyWord.keywordSearchSort(new ArrayList<>(), "example");

        Assert.assertTrue(actualResult.isEmpty());
    }
}