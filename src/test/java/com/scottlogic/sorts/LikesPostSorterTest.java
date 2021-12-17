package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikesPostSorterTest {

    LikesPostSorter sortByLikes = new LikesPostSorter();

    private UserPost createUserPost(int likes) {

        return new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", likes);
    }

    @Test
    public void sort_mixedValidLikesAsc_sortedListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost4, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_negativeLikesAsc_sortedListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(-1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost4, userPost3, userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_nullPostsAsc_nullRemovedReturned() {
        UserPost userPost1 = null;
        UserPost userPost2 = createUserPost(0);
        UserPost userPost3 = createUserPost(-1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void sort_nullListAsc_emptyListReturned() {
        List<UserPost> userPosts = null;
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.ASC);
        Assert.assertTrue(actualResult.isEmpty());
    }
    @Test
    public void sortemptyListAsc_emptyListReturned() {
        List<UserPost> userPosts =new ArrayList<>();
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.ASC);
        Assert.assertTrue(actualResult.isEmpty());
    }


    @Test
    public void sort_mixedValidLikesDesc_sortedListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(0);
        UserPost userPost4 = createUserPost(1);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void likesSorter_negativeLikesDesc_sortedListReturned() {
        UserPost userPost1 = createUserPost(3);
        UserPost userPost2 = createUserPost(1);
        UserPost userPost3 = createUserPost(-1);
        UserPost userPost4 = createUserPost(0);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void sort_nullListDesc_emptyListReturned() {
        List<UserPost> userPosts = null;
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.DESC);
        Assert.assertTrue(actualResult.isEmpty());
    }
    @Test
    public void sort_emptyListDesc_emptyListReturned() {
        List<UserPost> userPosts =new ArrayList<>();
        List<UserPost> actualResult = sortByLikes.sort(userPosts, SortOrder.DESC);
        Assert.assertTrue(actualResult.isEmpty());
    }
}