package com.results.plexus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class App {

    @JsonProperty("Application")
    public String application;
    @JsonProperty("Month")
    public String month;
    @JsonProperty("Year")
    public int year;
    @JsonProperty("Rating (1-4)")
    public String rating14;
    @JsonProperty("Notes")
    public String notes;
    @JsonProperty("MicroG Rating (1-4)")
    public String microGRating14;
    @JsonProperty("MicroG Notes")
    public String microGNotes;

}
