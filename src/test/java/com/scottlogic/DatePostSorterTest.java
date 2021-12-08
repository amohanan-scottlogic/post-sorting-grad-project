package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class DatePostSorterTest {


    DatePostSorter sortByDate = new DatePostSorter();
    private UserPost createUserPost (OffsetDateTime date ) {

        return new UserPost("Joe BLoggs",
                date,
                "Hello World!", 2);
    }



    @Test
    public void dateSorter_mixedValidNames_correctListReturnedAsc() {
        UserPost userPost1 = createUserPost(OffsetDateTime.of(20, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost2 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost3 = createUserPost(OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 1, ZoneOffset.UTC));
        UserPost userPost4 = createUserPost(OffsetDateTime.of(2012, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC));
        UserPost userPost5 = createUserPost(OffsetDateTime.of(2020, 12, 31, 7, 12, 3, 0, ZoneOffset.UTC));

        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        sortByDate.sort(userPosts, SortOrder.ASC);
        List<UserPost> sorted = Arrays.asList(userPost1, userPost4, userPost2, userPost3, userPost5);
        Assert.assertEquals(userPosts, sorted);
    }
//
//    @Test
//    public void authorSorter_oneObjectList_oneObjectListReturnedAsc() {
//        List<UserPost> userPosts = Arrays.asList(userPost1);
//        dps.sorter(userPosts, SortOrder.ASC);
//        List<UserPost> sorted = Arrays.asList(userPost1);
//        Assert.assertEquals(userPosts, sorted);
//    }
//
//
//    @Test
//    public void authorSorter_mixedValidNames_correctListReturnedDesc() {
//        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
//        dps.sorter(userPosts, SortOrder.DESC);
//        List<UserPost> sorted = Arrays.asList(userPost5, userPost3, userPost4, userPost2, userPost1);
//        Assert.assertEquals(userPosts, sorted);
//    }
//
//    @Test
//    public void authorSorter_oneObjectList_oneObjectListReturnedDesc() {
//        List<UserPost> userPosts = Arrays.asList(userPost1);
//        dps.sorter(userPosts, SortOrder.DESC);
//        List<UserPost> sorted = Arrays.asList(userPost1);
//        Assert.assertEquals(userPosts, sorted);
//    }


}