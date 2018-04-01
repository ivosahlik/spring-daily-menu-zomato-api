package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Intellij Idea
 * Created by ivosahlik on 22/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoDishWrapperDto {

    @JsonProperty("dish")
    private ZomatoDishDto dish;



    public ZomatoDishDto getDish() {
        return dish;
    }

    public void setDish(ZomatoDishDto dish) {
        this.dish = dish;
    }
}
