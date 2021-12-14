package com.scottlogic.filters;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatesFilter implements PostFilter {

    OffsetDateTime dateStart, dateEnd;

    public DatesFilter(OffsetDateTime date1, OffsetDateTime date2) {
        dateStart = date1;
        dateEnd = date2;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        List<UserPost> nullsRemoved = new ArrayList<>();
        List<UserPost> listToBeFiltered = new ArrayList<>();

        if (dateEnd == null || dateStart == null) {
            return filteredList;
        }
        if(inputList==null) {
            return filteredList;
        }
        listToBeFiltered = NullPostChecker.nullPostCheck(inputList);
        for (UserPost userPost : listToBeFiltered) {
            if (userPost.getDateTime() != null) nullsRemoved.add(userPost);
        }

        for (UserPost userPost : nullsRemoved) {
            if (userPost.getDateTime().compareTo(dateStart) >= 0 && userPost.getDateTime().compareTo(dateEnd) <= 0) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
