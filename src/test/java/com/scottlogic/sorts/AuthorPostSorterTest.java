package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorPostSorterTest {

    private AuthorPostSorter sortByAuthor = new AuthorPostSorter();

    private UserPost createUserPost(String name) {

        return new UserPost(name,
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
    }

    @Test
    public void authorSorter_mixedValidNamesAsc_sortedListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);

        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost4, userPost1, userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void authorSorter_oneObjectListAsc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,  SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullNamesAsc_returnNullLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC );
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3, userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_blankNamesAsc_returnBlankFirst() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Blogg");
        UserPost userPost3 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,  SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void sort_upperCaseAsc_upperCaseFirstReturned() {
        UserPost userPost1 = createUserPost("JOE Bloggs");
        UserPost userPost2 = createUserPost("Joe Blogg");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC );
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void sort_specialCharactersAsc_returnsAsPerNaturalOrder() {
        UserPost userPost1 = createUserPost("Joe! Bloggs");
        UserPost userPost2 = createUserPost("Joe. Blogg");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC );
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostAsc_nullRemovedReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = null;
        UserPost userPost3 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC );
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullListAsc_emptyListReturned() {
        List<UserPost> userPosts = null;
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,  SortOrder.ASC);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_emptyListAsc_emptyListReturned() {
        List<UserPost> userPosts = new ArrayList<>();
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.ASC );
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputAsc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("May");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);
        sortByAuthor.sort(userPosts, SortOrder.ASC );
        Assert.assertEquals(userPostsCopy, userPosts);
    }

    @Test
    public void sort_mixedValidNamesDesc_correctListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Bloggs");
        UserPost userPost3 = createUserPost("May Bloggs");
        UserPost userPost4 = createUserPost("Joe Blogg");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1, userPost4);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_oneObjectListDesc_oneObjectListReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void sort_upperCaseDesc_upperCaseFirstReturned() {
        UserPost userPost1 = createUserPost("JOE Bloggs");
        UserPost userPost2 = createUserPost("Joe Blogg");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void sort_specialCharactersDesc_returnsAsPerNaturalOrder() {
        UserPost userPost1 = createUserPost("Joe! Bloggs");
        UserPost userPost2 = createUserPost("Joe. Blogg");

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullNamesDesc_returnNullLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost(null);
        UserPost userPost3 = createUserPost("May Bloggs");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost1, userPost2);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_blankNamesDesc_returnBlankLast() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("Joe Blogg");
        UserPost userPost3 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost2, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostDesc_nullRemovedReturned() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = null;
        UserPost userPost3 = createUserPost("");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_emptyListDesc_emptyListReturned() {
        List<UserPost> userPosts = new ArrayList<>();
        List<UserPost> actualResult = sortByAuthor.sort(userPosts,SortOrder.DESC );
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInputDesc_inputListNotMutated() {
        UserPost userPost1 = createUserPost("Joe Bloggs");
        UserPost userPost2 = createUserPost("May");
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1, userPost2);
        sortByAuthor.sort(userPosts,SortOrder.DESC );
        Assert.assertEquals(userPostsCopy, userPosts);
    }
}