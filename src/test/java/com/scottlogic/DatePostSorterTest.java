package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class DatePostSorterTest {

    DatePostSorter sortByDate = new DatePostSorter();

    private UserPost createUserPost(OffsetDateTime date) {

        return new UserPost("Joe BLoggs",
                date,
                "Hello World!", 2);
    }

    @Test
    public void sort_mixedValidDatesAsc_correctListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost4, userPost2, userPost3, userPost5);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_oneObjectListAsc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1);
        sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1);
        Assert.assertEquals(userPosts, expectedResult);
    }

    @Test
    public void sort_nullDateAsc_nullLast() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));


        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1,  userPost3, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void sort_mixedValidDatesDesc_correctListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost5, userPost3, userPost2, userPost4, userPost1);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sort_oneObjectListDesc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1);
        sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost1);
        Assert.assertEquals(userPosts, expectedResult);
    }

    @Test
    public void sort_nullDateDesc_nullLast() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByDate.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList( userPost3, userPost1, userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }
}