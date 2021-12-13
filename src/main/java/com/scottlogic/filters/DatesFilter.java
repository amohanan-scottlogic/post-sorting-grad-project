package com.scottlogic.filters;

import com.scottlogic.UserPost;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatesFilter implements PostFilter{

    OffsetDateTime dateStart, dateEnd;
    public DatesFilter (OffsetDateTime date1, OffsetDateTime date2) {
        dateStart = date1;
        dateEnd = date2;
    }
    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<>();
        if(dateEnd==null||dateStart==null) return filteredList;
        for (UserPost userPost : inputList) {
            if(userPost.getDateTime().compareTo(dateStart)>=0&&userPost.getDateTime().compareTo(dateEnd)<=0) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
