package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;


public class DatesFilterTest {

    private UserPost createUserPost(OffsetDateTime date) {

        return new UserPost("Joe BLoggs",
                date,
                "Hello World!", 2);
    }

    @Test
    public void filter_mixedValidDates_correctListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC), OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullDates_emptyListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));


        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC), null);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullDatesList_correctListReturned() {
        UserPost userPost1 = createUserPost(null);
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));


        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC), OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }
}