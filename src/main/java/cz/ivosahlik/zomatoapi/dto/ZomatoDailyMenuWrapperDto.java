package cz.ivosahlik.zomatoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;

/**
 * Intellij Idea
 * Created by ivosahlik on 22/03/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoDailyMenuWrapperDto {

    @JsonProperty("daily_menu")
    @Embedded
    private ZomatoDailyMenuDto dailyMenu;


    public ZomatoDailyMenuDto getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(ZomatoDailyMenuDto dailyMenu) {
        this.dailyMenu = dailyMenu;
    }
}
