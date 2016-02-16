package com.dortega.challenge.common.models;

import java.io.Serializable;

/**
 * Created by dortega on 2/15/16.
 */

public class OMDBResponse implements Serializable {

    public String Title;
    public String Year;
    public String Rated;
    public String Released;
    public String Runtime;
    public String Genre;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Language;
    public String Country;
    public String Awards;
    public String Poster;
    public String MetaScore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbId;
    public String Type;
    public String Response;

    @Override
    public String toString() {
        return "OMDBResponse{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", MetaScore='" + MetaScore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", Type='" + Type + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }
}
