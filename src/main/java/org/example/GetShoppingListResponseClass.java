package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})
@Data
public class GetShoppingListResponseClass {

    @JsonProperty("aisles")
    public List<Aisle> aisles = new ArrayList<Aisle>();
    @JsonProperty("cost")
    public Double cost;
    @JsonProperty("startDate")
    public Integer startDate;
    @JsonProperty("endDate")
    public Integer endDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "aisle",
            "items"
    })
    @Data
    public class Aisle {

        @JsonProperty("aisle")
        public String aisle;
        @JsonProperty("items")
        public List<Item> items = new ArrayList<Item>();

        public Aisle withAisle(String aisle) {
            this.aisle = aisle;
            return this;
        }

        public Aisle withItems(List<Item> items) {
            this.items = items;
            return this;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "measures",
            "pantryItem",
            "aisle",
            "cost",
            "ingredientId"
    })
    @Data
    public class Item {

        @JsonProperty("id")
        public Integer id;
        @JsonProperty("name")
        public String name;
        @JsonProperty("measures")
        public Measures measures;
        @JsonProperty("pantryItem")
        public Boolean pantryItem;
        @JsonProperty("aisle")
        public String aisle;
        @JsonProperty("cost")
        public Double cost;
        @JsonProperty("ingredientId")
        public Integer ingredientId;

        public Item withId(Integer id) {
            this.id = id;
            return this;
        }

        public Item withName(String name) {
            this.name = name;
            return this;
        }

        public Item withMeasures(Measures measures) {
            this.measures = measures;
            return this;
        }

        public Item withPantryItem(Boolean pantryItem) {
            this.pantryItem = pantryItem;
            return this;
        }

        public Item withAisle(String aisle) {
            this.aisle = aisle;
            return this;
        }

        public Item withCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Item withIngredientId(Integer ingredientId) {
            this.ingredientId = ingredientId;
            return this;
        }

    }

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
