package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterANDTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    UserPost userPost3 = new UserPost("Joane Smith",
            OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);




    @Test
    public void filter_authorANDDate_postsWithBothReturned() {

        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        DatesFilter filterByDatesMock = mock(DatesFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost1, userPost2));
        when(filterByDatesMock.filter(Arrays.asList(userPost1,userPost2))).thenReturn(Arrays.asList(userPost2));
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByDatesMock);
        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_authorANDContent_postsWithBothReturned() {

        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost1, userPost2,userPost3));
        when(filterByContentMock.filter(userPosts)).thenReturn(Arrays.asList(userPost2,userPost3));
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByContentMock);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void filter_nullANDAuthor_emptyListReturned() {
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost1, userPost2,userPost3));
        when(filterByContentMock.filter(userPosts)).thenReturn(Arrays.asList());
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByContentMock);


        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_nullPosts_nullsRemoved() {

        UserPost userPost3 = null;
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList(userPost1, userPost2));
        when(filterByContentMock.filter(Arrays.asList(userPost1,userPost2))).thenReturn(Arrays.asList(userPost2));
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByContentMock);


        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_nullList_EmptyReturned() {
        List<UserPost> userPosts = null;
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        ContentFilter filterByContentMock = mock(ContentFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList());
        when(filterByContentMock.filter(Arrays.asList())).thenReturn(Arrays.asList());
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByContentMock);

        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void filter_emptyList_EmptyReturned() {
        List<UserPost> userPosts = new ArrayList<>();
        AuthorFilter filterByAuthorMock = mock(AuthorFilter.class);
        DatesFilter filterByDateMock = mock(DatesFilter.class);
        when(filterByAuthorMock.filter(userPosts)).thenReturn(Arrays.asList());
        when(filterByDateMock.filter(Arrays.asList())).thenReturn(Arrays.asList());
        FilterAND filteringAnd = new FilterAND(filterByAuthorMock, filterByDateMock);
        List<UserPost> actualResult = filteringAnd.filter(userPosts);
        Assert.assertTrue(actualResult.isEmpty());

    }
}