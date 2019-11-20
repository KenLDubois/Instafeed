package nc.prog1415.instafeed;

import java.io.Serializable;
import java.util.Date;

public class Rating implements Serializable {
    public String UserName;
    public String Title;
    public String Description;
    public Integer StarRating;
    public Business RatedBusiness;
    public Date Date;
}