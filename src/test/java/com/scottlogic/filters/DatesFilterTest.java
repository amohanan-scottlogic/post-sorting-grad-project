package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullDateRequested_emptyListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC), null);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullList_emptyListReturned() {

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> actualResult = filterByDates.filter(null);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullStartDateRequested_emptyListReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(null, OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullPost_nullIgnoredReturned() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List userPosts = Arrays.asList(userPost1, userPost2, null);
        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_emptyList_emptyListReturned() {

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = new ArrayList<>();
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_extremeDates_filteredListReturned() {
        UserPost minDate = createUserPost(OffsetDateTime.of(0000, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.MIN,
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(minDate, userPost2, userPost3);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(minDate, userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_extremeDatesInInput_filteredListReturned() {
        UserPost extremeMinDate = createUserPost(OffsetDateTime.MIN);
        UserPost extremeMaxDate = createUserPost(OffsetDateTime.MAX);
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(1800, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2800, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(extremeMinDate, extremeMaxDate, userPost3, userPost4);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost4);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_borderDatesMax_borderDatesIncludedReturned() {
        OffsetDateTime maxDate = OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC);

        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost containsMaxDate = createUserPost(maxDate);


        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                maxDate);
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, containsMaxDate);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3, containsMaxDate);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_borderDatesMin_borderDatesIncludedReturnedSecondsIgnored() {
        OffsetDateTime minDate = OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC);

        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost minDatePlusSeconds = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 4, 0, ZoneOffset.UTC));
        UserPost hasMinDate = createUserPost(minDate);
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        DatesFilter filterByDates = new DatesFilter(minDate, OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, minDatePlusSeconds, hasMinDate, userPost4, userPost5);
        List<UserPost> actualResult = filterByDates.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(minDatePlusSeconds, hasMinDate, userPost5);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_validInput_inputListNotMutated() {
        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 12, 3, 7, 12, 3, 0, ZoneOffset.UTC));

        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);
        filterByDates.filter(userPosts);
        Assert.assertEquals(userPostsCopy, userPosts);
    }
}