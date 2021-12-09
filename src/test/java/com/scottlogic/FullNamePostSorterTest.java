package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class FullNamePostSorterTest {

    FullNamePostSorter sortByFullName = new FullNamePostSorter();

    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void fullNameSorter_mixedValidNames_correctListReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void fullNameSorter_spaceForLastName_correctListReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void fullNameSorter_nullForName_nullLastReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost(null);
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void fullNameSorter_spaceForName_spaceFirstReturnedAsc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost(" ");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void fullNameSorter_mixedValidNames_correctListReturnedDesc() {
        UserPost userPost1 = createUserPost("Joe");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost2, userPost4, userPost1);
        Assert.assertEquals(sortByMethod, sorted);
    }

    @Test
    public void fullNameSorter_spaceForLastName_correctListReturnedDesc() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> sortByMethod = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost1, userPost4, userPost2);
        Assert.assertEquals(sortByMethod, sorted);
    }
}