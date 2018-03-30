package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;

/**
 * Intellij Idea
 * Created by ivosahlik on 22/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ZomatoDishDto {

    @JsonProperty("dish_id")
    private int dish;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private String price;


    public int getDish() {
        return dish;
    }

    public void setDish(int dish) {
        this.dish = dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
