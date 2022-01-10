package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
    @Mock
    PostFilter filterA;
    @Mock
    PostFilter filterB;

    @Test
    public void filter_filterAOrFilterB_postsWithMatchingFilterAOrFilterBReturned() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR = new FilterOR(filterA, filterB);

        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void filter_commutativePropertyOfOr_sameResultDifferentOrder() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR1 = new FilterOR(filterB, filterA);
        FilterOR filteringOR2 = new FilterOR(filterA,filterB);

        List<UserPost> actualResult1 = filteringOR1.filter(userPosts);
        List<UserPost> actualResult2 = filteringOR2.filter(userPosts);

        Assert.assertTrue(CollectionUtils.isEqualCollection(actualResult1,actualResult2));
    }

    @Test
    public void filter_emptyOrFilterA_filterAResultSent() {

        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2,userPost3));
        when(filterB.filter(userPosts)).thenReturn(Collections.emptyList());
        FilterOR filteringOR = new FilterOR(filterB, filterA);

        List<UserPost> actualResult = filteringOR.filter(userPosts);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost3);
        
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void filter_validInput_inputListNotMutated() {
        List<UserPost> userPostsCopy = userPosts;
        when(filterA.filter(userPosts)).thenReturn(Arrays.asList(userPost2, userPost3));
        when(filterB.filter(userPosts)).thenReturn(Arrays.asList(userPost3));
        FilterOR filteringOR = new FilterOR(filterA, filterB);

        filteringOR.filter(userPosts);

        Assert.assertEquals(userPosts,userPostsCopy);
    }
}