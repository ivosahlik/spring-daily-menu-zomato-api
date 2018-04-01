package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 22/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoDailyMenuDto {

    @JsonProperty("daily_menu_id")
    private int id;

    @JsonProperty("name")
    private String name;

    private List<ZomatoDishWrapperDto> dishes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ZomatoDishWrapperDto> getDishes() {
        return dishes;
    }

    public void addDishes(List<ZomatoDishWrapperDto> dishes) {
        this.dishes = dishes;
    }
}
