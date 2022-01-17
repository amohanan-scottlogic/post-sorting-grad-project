package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_spaceForLastNameAsc_spaceComesFirstReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_nullForNameAsc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_spaceForNameAsc_spaceFirstReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_middleNameAsc_ignoreMiddleNameReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("Zoey B Space");
        UserPost userPost4 = createUserPost("Zoey A Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3, userPost4);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_lastNameOnlyAsc_treatsFirstNameAsBlankReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" Bloggs");
        UserPost userPost3 = createUserPost("Zoey Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_nullPostAsc_nullRemovedReturned() {
        UserPost userPost1 = createUserPost("Zoey Space");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_emptyListAsc_emptyListReturned() {
        List<UserPost> actualResult = sortByFullName.sort(new ArrayList<>(), SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void fullNameSorter_nullListAsc_emptyListReturned() {
        List<UserPost> actualResult = sortByFullName.sort(null, SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputAsc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Andy");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);

        sortByFullName.sort(userPosts, SortOrder.ASC);

        Assert.assertEquals(userPostsCopy, userPosts);
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

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_spaceForLastNameDesc_spaceComesLastReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_nullForNameDesc_nullLastReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_spaceForNameDesc_spaceLastReturned() {
        UserPost userPost1 = createUserPost("Joe ");
        UserPost userPost2 = createUserPost(" ");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_middleNameDesc_ignoreMiddleNameReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe ");
        UserPost userPost3 = createUserPost("Zoey B Space");
        UserPost userPost4 = createUserPost("Zoey A Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost4, userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_lastNameOnlyDesc_treatsFirstNameAsBlankReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(" Bloggs");
        UserPost userPost3 = createUserPost("Zoey Space");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_nullPostDesc_nullRemovedReturned() {
        UserPost userPost1 = createUserPost("Zoey Space");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null);

        List<UserPost> actualResult = sortByFullName.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void fullNameSorter_emptyListDesc_emptyListReturned() {
        List<UserPost> actualResult = sortByFullName.sort(new ArrayList<>(), SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void fullNameSorter_nullListDesc_emptyListReturned() {
        List<UserPost> actualResult = sortByFullName.sort(null, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputDesc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Andy");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);

        sortByFullName.sort(userPosts, SortOrder.DESC);

        Assert.assertEquals(userPostsCopy, userPosts);
    }

}