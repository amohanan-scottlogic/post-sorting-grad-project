package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FilterANDTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 0);

    UserPost userPost3 = new UserPost("Joane Smith",
            OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);

    @Test
    public void filter_authorAndDate_postsWithBothReturned() {

        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2020, 1, 3, 10, 12, 3, 0, ZoneOffset.UTC));
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByDates);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Collections.singletonList(userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_authorAndContent_postsWithBothReturned() {

        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        ContentFilter filterByContent = new ContentFilter("post");
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByContent);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_commutativeProperty_sameListDifferentOrderReturned() {

        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        ContentFilter filterByContent = new ContentFilter("post");
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByContent);
        FilterAND filteringAnd2 = new FilterAND(filterByContent, filterByAuthor);


        List<UserPost> filter1Result = filteringAnd.filter(userPosts);
        List<UserPost> filter2Result = filteringAnd2.filter(userPosts);

        Assert.assertTrue(CollectionUtils.isEqualCollection(filter1Result, filter2Result));
    }

    @Test
    public void filter_associativeProperty_sameListDifferentOrderReturned() {

        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        ContentFilter filterByContent = new ContentFilter("post");
        LikesFilter filterByLikes = new LikesFilter();
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByContent);
        FilterAND filteringAnd2 = new FilterAND(filteringAnd, filterByLikes);
        FilterAND filteringAndB = new FilterAND(filterByLikes, filterByAuthor);
        FilterAND filteringAndB2 = new FilterAND(filteringAndB, filterByContent);


        List<UserPost> actualResultA = filteringAnd2.filter(userPosts);
        List<UserPost> actualResultB = filteringAndB2.filter(userPosts);

        Assert.assertTrue(CollectionUtils.isEqualCollection(actualResultA, actualResultB));
    }

    @Test
    public void filter_nullAndAuthor_emptyListReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        ContentFilter filterByContent = new ContentFilter(null);
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByContent);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullPosts_nullsRemoved() {

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null);
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        ContentFilter filterByContent = new ContentFilter("post");
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByContent);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Collections.singletonList(userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullList_EmptyReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        LikesFilter filterByLikes = new LikesFilter();
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByLikes);

        List<UserPost> actualResult = filteringAnd.filter(null);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_emptyList_EmptyReturned() {
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        LikesFilter filterByLikes = new LikesFilter();
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByLikes);

        List<UserPost> actualResult = filteringAnd.filter(new ArrayList<>());

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_validInput_inputListNotMutated() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        LikesFilter filterByLikes = new LikesFilter();
        FilterAND filteringAnd = new FilterAND(filterByAuthor, filterByLikes);

        filteringAnd.filter(userPosts);

        Assert.assertEquals(userPostsCopy, userPosts);
    }

}