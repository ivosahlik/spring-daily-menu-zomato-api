package cz.ivosahlik.zomatoapi.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Intellij Idea
 * Created by ivosahlik on 31/03/2018
 */
@Slf4j
public class DailyMenuTest {

    private DailyMenu dailyMenu;

    @Before
    public void setUp() throws Exception {
        dailyMenu = new DailyMenu();
    }

    @Test
    public void getDaily_menu_id() {
        String daily_menu_id = "16506246";
        dailyMenu.setDaily_menu_id(daily_menu_id);

        assertEquals(daily_menu_id, dailyMenu.getDaily_menu_id());

    }

}
