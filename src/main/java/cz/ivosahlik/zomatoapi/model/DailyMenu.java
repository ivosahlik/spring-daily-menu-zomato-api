package cz.ivosahlik.zomatoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/03/2018
 */
@Data
@AllArgsConstructor
@Embeddable
public class DailyMenu {

    private String daily_menu_id;

    private String start_date;

    private String end_date;

    private String name;

    @JsonProperty("dishes")
    @Embedded
    private List<Dishes> dishes;

    public DailyMenu() {
    }
}
