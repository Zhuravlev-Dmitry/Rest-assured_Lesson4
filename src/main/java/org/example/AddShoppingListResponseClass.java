package org.example;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "usages",
        "usageRecipeIds",
        "pantryItem",
        "aisle",
        "cost",
        "ingredientId"
})
@Data
public class AddShoppingListResponseClass {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("measures")
    public Measures measures;
    @JsonProperty("usages")
    public List<Object> usages = new ArrayList<Object>();
    @JsonProperty("usageRecipeIds")
    public List<Object> usageRecipeIds = new ArrayList<Object>();
    @JsonProperty("pantryItem")
    public Boolean pantryItem;
    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("cost")
    public Double cost;
    @JsonProperty("ingredientId")
    public Integer ingredientId;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "original",
            "metric",
            "us"
    })
    @Data
    public class Measures {

        @JsonProperty("original")
        public Original original;
        @JsonProperty("metric")
        public Metric metric;
        @JsonProperty("us")
        public Us us;

        public Measures withOriginal(Original original) {
            this.original = original;
            return this;
        }

        public Measures withMetric(Metric metric) {
            this.metric = metric;
            return this;
        }

        public Measures withUs(Us us) {
            this.us = us;
            return this;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    public class Metric {

        @JsonProperty("amount")
        public Double amount;
        @JsonProperty("unit")
        public String unit;

        public Metric withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Metric withUnit(String unit) {
            this.unit = unit;
            return this;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    public class Original {

        @JsonProperty("amount")
        public Double amount;
        @JsonProperty("unit")
        public String unit;

        public Original withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Original withUnit(String unit) {
            this.unit = unit;
            return this;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    public class Us {

        @JsonProperty("amount")
        public Double amount;
        @JsonProperty("unit")
        public String unit;

        public Us withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Us withUnit(String unit) {
            this.unit = unit;
            return this;
        }

    }
}