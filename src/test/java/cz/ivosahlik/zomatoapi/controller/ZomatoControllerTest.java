package cz.ivosahlik.zomatoapi.controller;

import cz.ivosahlik.zomatoapi.service.impl.ZomatoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

/**
 * Intellij Idea
 * Created by ivosahlik on 31/03/2018
 */
@Slf4j
public class ZomatoControllerTest {

    @Mock
    private ZomatoServiceImpl zomatoService;

    ZomatoController zomatoController;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        zomatoController = new ZomatoController(zomatoService);
    }

    @Test
    public void zopatoApi() throws Exception{

        String daily_menu_id = "19598365";

        List<String> list = new ArrayList<>();
        when(zomatoService.getZomatoDailyMenuFromJsonFull(daily_menu_id)).thenReturn(list);

        String viewName = zomatoController.zopatoApi(model);
        assertEquals("view", viewName);

        ArgumentCaptor<List<String>> argumentCaptor = ArgumentCaptor.forClass(List.class);

//        verify(zomatoService, times(1)).getZomatoDailyMenuFromJsonFull(daily_menu_id);
//        verify(model, times(1)).addAttribute(eq("daily_menu"), argumentCaptor.capture());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(zomatoController).build();
        // mockMvc.perform(get("/api"))
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("view"));

    }



}
