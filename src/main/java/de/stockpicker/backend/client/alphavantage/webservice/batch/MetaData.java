package de.stockpicker.backend.client.alphavantage.webservice.batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {

    private String information;
    private String notes;
    private String timezone;

    @JsonProperty("information")
    public String getInformation() {
        return information;
    }

    @JsonProperty("1. Information")
    public void setInformation(String information) {
        this.information = information;
    }

    @JsonProperty("noted")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("2. Notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("3. Time Zone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
