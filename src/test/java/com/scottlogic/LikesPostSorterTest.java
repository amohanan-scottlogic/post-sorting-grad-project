package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class LikesPostSorterTest {

    LikesPostSorter sortByLikes = new LikesPostSorter();
    private UserPost createUserPost (int likes ) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", likes);
    }

    @Test
    public void sort_mixedValidLikesAsc_correctListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        sortByLikes.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost2, userPost4, userPost1);
        Assert.assertEquals(userPosts, sorted);
    }

    @Test
    public void sort_negativeLikesAsc_correctListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(-1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        sortByLikes.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost4, userPost3, userPost2, userPost1);
        Assert.assertEquals(userPosts, sorted);
    }

    @Test
    public void sort_mixedValidLikesDesc_correctListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        sortByLikes.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(userPosts, sorted);
    }

    @Test
    public void likesSorter_negativeLikesDesc_correctListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(-1);
        UserPost userPost4 = createUserPost(0);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        sortByLikes.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(userPosts, sorted);
    }
}