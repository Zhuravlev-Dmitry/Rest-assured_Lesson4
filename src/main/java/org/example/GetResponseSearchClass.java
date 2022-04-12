package org.example;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results",
        "offset",
        "number",
        "totalResults"
})
@Data
public class GetResponseSearchClass {
    @JsonProperty("results")
    private List<result> results = null;
    @Data
    private static class result {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private int id;
        @JsonProperty ("title")
        private String title;
        @JsonProperty ("image")
        private String image;
        @JsonProperty ("imageType")
        private String imageType;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    }

    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("totalResults")
    public Integer totalResults;
}
