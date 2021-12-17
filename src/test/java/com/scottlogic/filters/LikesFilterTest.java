package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikesFilterTest {
    private UserPost createUserPost(int likes) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", likes);
    }

    @Test
    public void filter_likesCountWhenPresent_correctListReturned() {
        LikesFilter filterByLikes = new LikesFilter();
        UserPost userPost1 = createUserPost(2);
        UserPost userPost2 = createUserPost(0);
        UserPost userPost3 = createUserPost(1);
        UserPost userPost4 = createUserPost(3);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByLikes.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_likesCountWhenNegative_correctListReturned() {
        LikesFilter filterByLikes = new LikesFilter();
        UserPost userPost1 = createUserPost(-2);
        UserPost userPost2 = createUserPost(0);
        UserPost userPost3 = createUserPost(1);

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = filterByLikes.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullPostInList_nullIgnored() {
        LikesFilter filterByLikes = new LikesFilter();
        UserPost userPost1 = createUserPost(2);
        UserPost userPost2 = createUserPost(0);
        UserPost nullPost = null;
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, nullPost);
        List<UserPost> actualResult = filterByLikes.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_emptyList_emptyListReturned() {
        LikesFilter filterByLikes = new LikesFilter();
        List<UserPost> userPosts = new ArrayList<>();
        List<UserPost> actualResult = filterByLikes.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullList_emptyListReturned() {
        LikesFilter filterByLikes = new LikesFilter();
        List<UserPost> userPosts = null;
        List<UserPost> actualResult = filterByLikes.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }
}