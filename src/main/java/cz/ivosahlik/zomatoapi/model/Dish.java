package cz.ivosahlik.zomatoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/03/2018
 */
@Data
@AllArgsConstructor
@Embeddable
public class Dish {

    private String dish_id;

    private String name;

    private String price;

    public Dish() {
    }
}
