package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatePostSorterTest {

    DatePostSorter sortByDate = new DatePostSorter();

    private UserPost createUserPost(OffsetDateTime date) {

        return new UserPost("Joe BLoggs",
                date,
                "Hello World!", 2);
    }

    @Test
    public void sort_mixedValidDatesAsc_sortedListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost4, userPost2, userPost3, userPost5);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_oneObjectListAsc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Collections.singletonList(userPost1);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Collections.singletonList(userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullDateAsc_nullLast() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostAsc_nullIgnored() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, null, userPost3);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullListAsc_emptyListReturned() {
        List<UserPost> actualResult = sortByDate.sort(null, SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_emptyListAsc_emptyListReturned() {
        List<UserPost> actualResult = sortByDate.sort(new ArrayList<>(), SortOrder.ASC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputAsc_inputListNotMutated() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);

        Assert.assertEquals(userPostsCopy, userPosts);
    }

    @Test
    public void sort_mixedValidDatesDesc_sortedListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost5, userPost3, userPost2, userPost4, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_oneObjectListDesc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Collections.singletonList(userPost1);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Collections.singletonList(userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullDateDesc_nullLast() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostDesc_nullIgnored() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, null, userPost3);

        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullListDesc_emptyListReturned() {
        List<UserPost> actualResult = sortByDate.sort(null, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_emptyListDesc_emptyListReturned() {
        List<UserPost> actualResult = sortByDate.sort(new ArrayList<>(), SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputDesc_inputListNotMutated() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);

        sortByDate.sort(userPosts, SortOrder.DESC);

        Assert.assertEquals(userPostsCopy, userPosts);
    }
}