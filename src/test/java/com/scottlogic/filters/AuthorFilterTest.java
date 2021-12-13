package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class AuthorFilterTest {


    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void filter_firstNameUppercase_correctListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("JOE");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_lastName_correctListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Blogg");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bligg");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullRequested_emptyListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter(null);
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_spaceRequested_correctListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter(" ");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullNameInList_correctListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("JO");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("Joe Bloggs");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResults = Arrays.asList(userPost1, userPost3);
        Assert.assertEquals(actualResult, expectedResults);
    }

}