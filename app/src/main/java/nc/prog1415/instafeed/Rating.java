package nc.prog1415.instafeed;

import android.location.Location;

import java.io.Serializable;
import java.util.Date;

public class Rating implements Serializable {
    public String UserName;
    public String Title;
    public String Description;
    public Float StarRating;
    public Business RatedBusiness;
    public Date RatingDate;
    public Double Latitude;
    public Double Longitude;
    public transient android.location.Location Location;


    public Rating(Business business, float starRating){

        RatedBusiness = business;
        StarRating = starRating;
        RatingDate = new Date();
    }

    public Rating(Business business, float starRating, String title, String description){
        RatedBusiness = business;
        StarRating = starRating;
        RatingDate = new Date();
        Title = title;
        Description = description;
    }

}