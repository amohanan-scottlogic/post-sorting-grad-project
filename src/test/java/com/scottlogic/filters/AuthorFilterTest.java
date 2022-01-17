package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthorFilterTest {


    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void filter_firstNameUppercase_caseInsensitiveListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("JOE");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_lastName_lastNameListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Blogg");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bligg");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4);

        Assert.assertEquals(expectedResult, actualResult);
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
    public void filter_spaceRequested_allNamesWithSpaceReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter(" ");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullNameInList_nullNotIncludedReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("JO");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nameNotInList_emptyReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Jen");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullList_emptyReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Jen");

        List<UserPost> actualResult = filterByAuthor.filter(null);

        Assert.assertTrue(actualResult.isEmpty());
    }
    @Test
    public void filter_nullPostInList_nullIgnored() {
        AuthorFilter filterByAuthor = new AuthorFilter("May");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null);

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);
        List<UserPost> expectedResult = Collections.singletonList(userPost2);

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void filter_emptyList_emptyListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("May");
        List<UserPost> userPosts = new ArrayList<>();

        List<UserPost> actualResult = filterByAuthor.filter(userPosts);

        Assert.assertTrue(actualResult.isEmpty());

    }

  

    @Test
    public void filter_validInput_inputListNotMutated() {
        AuthorFilter filterByAuthor = new AuthorFilter("May");
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);
        filterByAuthor.filter(userPosts);

        Assert.assertEquals(userPostsCopy, userPosts);
    }

}