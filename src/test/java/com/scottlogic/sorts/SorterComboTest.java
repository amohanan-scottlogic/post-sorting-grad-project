package com.scottlogic.sorts;

import com.scottlogic.UserPost;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SorterComboTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 5, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 5);

    UserPost userPost3 = new UserPost("Joane Smith",
            OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "Another example post.", 3);

    @Test
    public void sort_validNameAndDate_sortedFirstByNameThenDate() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        AuthorPostSorter sortByAuthor = new AuthorPostSorter();
        DatePostSorter sortByDate = new DatePostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByAuthor, sortByDate);

        List<UserPost> actualResult = sortByBoth.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost3, userPost2, userPost1);

        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void sort_validInput_sortedByFirstSortCriteriaThenSecondSortCriteria() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3);
        ContentPostSorter sortByContent = new ContentPostSorter();
        LikesPostSorter sortByLikes = new LikesPostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByContent, sortByLikes);

        List<UserPost> actualResult = sortByBoth.sort(userPosts, SortOrder.ASC);
        List<UserPost> expectedResult = Arrays.asList(userPost1, userPost3, userPost2);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_nullPostNameAndContent_NullIgnored() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, null, userPost3);
        AuthorPostSorter sortByAuthor = new AuthorPostSorter();
        ContentPostSorter sortByContent = new ContentPostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByAuthor, sortByContent);

        List<UserPost> actualResult = sortByBoth.sort(userPosts, SortOrder.DESC);
        List<UserPost> expectedResult = Arrays.asList(userPost2, userPost1, userPost3);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sort_emptyList_emptyListReturned() {

        AuthorPostSorter sortByAuthor = new AuthorPostSorter();
        ContentPostSorter sortByContent = new ContentPostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByAuthor, sortByContent);

        List<UserPost> actualResult = sortByBoth.sort(new ArrayList<>(), SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_nullList_emptyListReturned() {
        LikesPostSorter sortByLikes = new LikesPostSorter();
        ContentPostSorter sortByContent = new ContentPostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByLikes, sortByContent);

        List<UserPost> actualResult = sortByBoth.sort(null, SortOrder.DESC);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void sort_validInput_inputListNotMutated() {
        List<UserPost> userPosts = Arrays.asList(userPost1,userPost2,userPost3);
        List<UserPost> userPostsCopy = Arrays.asList(userPost1,userPost2,userPost3);
        DatePostSorter sortByDate = new DatePostSorter();
        LikesPostSorter sortByLikes = new LikesPostSorter();
        SorterCombo sortByBoth = new SorterCombo(sortByLikes, sortByDate);

        sortByBoth.sort(userPosts, SortOrder.ASC);

        Assert.assertEquals(userPostsCopy, userPosts);

    }

}