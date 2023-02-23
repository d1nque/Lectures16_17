package ua.d1nque.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Declaration {
    @JsonProperty("position_en")
    private String positionEn;
    @JsonProperty("url")
    private String url;
    @JsonProperty("income")
    private String income;
    @JsonProperty("region_uk")
    private String regionUk;
    @JsonProperty("office_en")
    private String officeEn;
    @JsonProperty("position_uk")
    private String positionUk;
    @JsonProperty("year")
    private String year;
    @JsonProperty("office_uk")
    private String officeUk;
    @JsonProperty("region_en")
    private String regionEn;
}
