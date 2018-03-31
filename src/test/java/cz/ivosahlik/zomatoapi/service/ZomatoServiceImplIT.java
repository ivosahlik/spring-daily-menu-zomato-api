package cz.ivosahlik.zomatoapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import cz.ivosahlik.zomatoapi.model.DailyMenus;
import cz.ivosahlik.zomatoapi.service.impl.ZomatoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Intellij Idea
 * Created by ivosahlik on 31/03/2018
 */
@Slf4j
public class ZomatoServiceImplIT {

    private ZomatoServiceImpl zomatoService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        zomatoService = new ZomatoServiceImpl();
    }

    @Test
    public void dailyMenuWrapper() throws IOException{
        TypeReference<DailyMenus> typeReference = new TypeReference<DailyMenus>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/dailyMenu1.json");

        List<String> list = zomatoService.getDailyMenuList(inputStream);

        assertEquals(7, list.size());
    }


}
