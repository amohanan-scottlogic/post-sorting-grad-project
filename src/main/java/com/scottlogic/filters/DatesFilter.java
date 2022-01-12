package com.scottlogic.filters;

import com.scottlogic.NullPostChecker;
import com.scottlogic.UserPost;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DatesFilter implements PostFilter {

    OffsetDateTime dateStart, dateEnd;

    public DatesFilter(OffsetDateTime date1, OffsetDateTime date2) {
        dateStart = date1;
        dateEnd = date2;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        List<UserPost> listToBeFiltered;

        if (dateEnd == null || dateStart == null || inputList == null) {
            return filteredList;
        }

        listToBeFiltered = NullPostChecker.nullPostCheck(inputList);

        filteredList = listToBeFiltered.stream()
                .filter(Objects::nonNull)
                .filter(post -> !post.getDateTime().isBefore(dateStart))
                .filter(post -> !post.getDateTime().isAfter(dateEnd))
                .collect(Collectors.toList());

        return filteredList;
    }
}
