package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class AuthorFilterTest {

    AuthorFilter filterByAuthor = new AuthorFilter();
    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void filter_firstNameUppercase_correctListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts, "JOE");
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_lastName_correctListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bligg");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts, "Blogg");
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullrequested_emptyListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bligg");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = filterByAuthor.filter(userPosts, null);
        Assert.assertTrue(actualResult.isEmpty());
    }

}