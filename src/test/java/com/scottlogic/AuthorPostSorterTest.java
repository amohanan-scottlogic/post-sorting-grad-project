package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class AuthorPostSorterTest {

    private AuthorPostSorter sortByAuthor = new AuthorPostSorter();

    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void authorSorter_mixedValidNames_correctListReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost4, userPost1, userPost2, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void authorSorter_oneObjectList_oneObjectListReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost1);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_nullNamesAsc_returnNullLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        UserPost userPost5 = createUserPost(null);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost4, userPost1, userPost2, userPost3, userPost5);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_blankNamesAsc_returnBlankFirst() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        UserPost userPost5 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost5, userPost4, userPost1, userPost2, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_mixedValidNamesDesc_correctListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost2, userPost1, userPost4);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_oneObjectListDesc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost1);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_nullNamesDesc_returnNullLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        UserPost userPost5 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost1, userPost5, userPost4, userPost2);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void sort_blankNamesDesc_returnBlankLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        UserPost userPost5 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> sortByMethod = sortByAuthor.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost2, userPost1, userPost4, userPost5);
        Assert.assertEquals(sortByMethod, sorted);
    }
}