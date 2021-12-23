package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterORTest {

    @Test
    public void filter_authorORContent_authorContentAllResults() {

        UserPost userPost1 = new UserPost("Jin Bloggs",
                OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);

        UserPost userPost2 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 1);

        UserPost userPost3 = new UserPost("Joane Smith",
                OffsetDateTime.of(2020, 1, 4, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        AuthorFilter filterByAuthor = new AuthorFilter("Jo");
        DatesFilter filterByDates = new DatesFilter(OffsetDateTime.of(2020, 1, 1, 7, 12, 3, 0, ZoneOffset.UTC), OffsetDateTime.of(2020, 1, 4, 7, 12, 3, 0, ZoneOffset.UTC));
        FilterOR filteringOR = new FilterOR(filterByDates, filterByAuthor);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_keywordORLikes_postsWithMatchingKeywordOrLikesReturned() {
        UserPost userPost1 = new UserPost("Jin Bloggs",
                OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 0);

        UserPost userPost2 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 0);

        UserPost userPost3 = new UserPost("Joane Smith",
                OffsetDateTime.of(2020, 1, 4, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        LikesFilter filterByLikesMock = mock(LikesFilter.class);
        when(filterByContentMock.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterByLikesMock.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR = new FilterOR(filterByContentMock, filterByLikesMock);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullORAuthor_authorMatchSent() {
        UserPost userPost1 = new UserPost("Jin Bloggs",
                OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 0);

        UserPost userPost2 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 0);

        UserPost userPost3 = new UserPost("Joane Smith",
                OffsetDateTime.of(2020, 1, 4, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost2,userPost3));
        when(filterByContentMock.filter(userPosts)).thenReturn(Arrays.asList());

        FilterOR filteringOR = new FilterOR(filterByContentMock, filterByAuthorMock);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void filter_nullPosts_nullsRemoved() {
        UserPost userPost1 = new UserPost("Jin Bloggs",
                OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 0);

        UserPost userPost2 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 0);

        UserPost userPost3 = null;

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost2));
        when(filterByContentMock.filter(userPosts)).thenReturn(Arrays.asList(userPost2));

        FilterOR filteringOR = new FilterOR(filterByContentMock, filterByAuthorMock);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2);
        Assert.assertEquals(expectedResult, actualResult);

    }

}