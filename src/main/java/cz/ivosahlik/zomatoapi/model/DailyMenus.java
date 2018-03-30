package cz.ivosahlik.zomatoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/03/2018
 */
@Data
@AllArgsConstructor
public class DailyMenus {

    @JsonProperty("daily_menu")
    @Embedded
    private DailyMenu dailyMenu;

    public DailyMenus() {
    }
}
