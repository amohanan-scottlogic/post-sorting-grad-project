package com.scottlogic.sorts;

import com.scottlogic.UserPost;
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
    public void fullNameSorter_mixedValidNamesAsc_sortedListReturned() {
        UserPost userPost1 = createUserPost("Joe");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_spaceForLastNameAsc_spaceComesFirstReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_nullForNameAsc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_spaceForNameAsc_spaceFirstReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_middleNameAsc_ignoreMiddleNameReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("Zoey Andy Space ");
        UserPost userPost4 = createUserPost("Joe Tloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3, userPost4);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_lastNameOnlyAsc_treatsFirstNameAsBlankReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" Bloggs");
        UserPost userPost3 = createUserPost("Zoey Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_mixedValidNamesDesc_sortedListReturned() {
        UserPost userPost1 = createUserPost("Joe");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost4, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_spaceForLastNameDesc_spaceComesLastReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_nullForNameDesc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_spaceForNameDesc_spaceLastReturned() {
        UserPost userPost1 = createUserPost("Joe ");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_middleNameDesc_ignoreMiddleNameReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("Zoey Andy Space ");
        UserPost userPost4 = createUserPost("Joe Tloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost4, userPost3, userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void fullNameSorter_lastNameOnlyDesc_treatsFirstNameAsBlankReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" Bloggs");
        UserPost userPost3 = createUserPost("Zoey Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

}