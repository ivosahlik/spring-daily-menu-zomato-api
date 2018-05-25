package cz.ivosahlik.zomatoapi.controller;

import cz.ivosahlik.zomatoapi.enumeration.GeoCodeEnum;
import cz.ivosahlik.zomatoapi.service.ZomatoService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@Api            // swagger2
@Slf4j
@RequestMapping(ZomatoController.BASE_URL)
@Controller
public class ZomatoController {

    public static final String BASE_URL = "/api";

    private static final String PARAM_VIEW = "view";

    private ZomatoService zomatoService;

    public ZomatoController(ZomatoService zomatoService) {
        this.zomatoService = zomatoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String zopatoApi(Model model) throws IOException {

        model.addAttribute("restaurants",
                zomatoService.getZomatoNearbyRestaurantFromRestApi(GeoCodeEnum.PRAHA.getLatitude(),GeoCodeEnum.PRAHA.getLongtitude()));

//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyMenuFromJsonFile("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyMenuFromJsonFull("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyMenuFromJsonWeb("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyMenuFromJsonWeb("16505933"));

//        Map<String, String> map = zomatoService.getZomatoRestaurantFromJsonFile("16506246");
//
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//           model.addAttribute(entry.getKey().toString(), entry.getValue());
//        }

//        return "dailyMenu";
        return PARAM_VIEW;

    }


}
