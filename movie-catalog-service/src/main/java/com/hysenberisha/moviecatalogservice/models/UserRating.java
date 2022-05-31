package com.hysenberisha.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    private List<Rating> userRating;

    public UserRating(){

    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
