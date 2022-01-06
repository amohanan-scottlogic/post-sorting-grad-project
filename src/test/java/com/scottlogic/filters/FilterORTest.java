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
    PostFilter filterA = mock(PostFilter.class);
    PostFilter filterB = mock(PostFilter.class);

    @Test
    public void filter_filterAORFilterB_postsWithMatchingFilterAOrFilterBReturned() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR = new FilterOR(filterA, filterB);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_filterBORFilterA_sameResultAsAboveButDifferentOrder() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR = new FilterOR(filterB, filterA);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_emptyORFilterA_filterAResultSent() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2,userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList());
        FilterOR filteringOR = new FilterOR(filterB, filterA);
        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);

    }
}