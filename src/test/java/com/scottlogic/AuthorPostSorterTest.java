package com.scottlogic;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorPostSorterTest {

    AuthorPostSorter aps = new AuthorPostSorter();
    private UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    private UserPost userPost2 = new UserPost("Joey Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    private UserPost userPost3 = new UserPost("Jane Smith",
            OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    private UserPost userPost4 = new UserPost("May Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    private UserPost userPost5 = new UserPost(null,
            OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

//    Scanner scan = new Scanner(System.in);
//    system.out.println("Please enter names");
//    String s = scan.next();

    List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);

    @Test
    public void authorSorter_mixedValidNames_correctListReturned() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        aps.authorSorter(userPosts);
        List<UserPost> sorted = Arrays.asList(userPost3, userPost1, userPost2, userPost4);
        Assert.assertEquals(userPosts, sorted);
    }

    @Test
    public void authorSorter_oneObjectList_oneObjectListReturned() {
        List<UserPost> userPosts = Arrays.asList(userPost1);
        aps.authorSorter(userPosts);
        List<UserPost> sorted = Arrays.asList(userPost1);
        Assert.assertEquals(userPosts, sorted);
    }

    @Test
    public void authorSorter_nullNames_returnNull() {
        List<UserPost> userPosts = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> sorted = aps.authorSorter(userPosts);
        Assert.assertNull(sorted);
    }

}