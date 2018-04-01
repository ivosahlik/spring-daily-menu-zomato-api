package cz.ivosahlik.zomatoapi.controller;

import cz.ivosahlik.zomatoapi.service.ZomatoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 20/03/2018
 */
@Slf4j
@Controller
public class ZomatoController {

    private ZomatoService zomatoService;

    public ZomatoController(ZomatoService zomatoService) {
        this.zomatoService = zomatoService;
    }

    @GetMapping("/api")
    public String zopatoApi(Model model) throws IOException {

//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyFromJsonFile("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyFromJsonFull("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyFromJsonWeb("16506246"));
//        model.addAttribute("daily_menu", zomatoService.getZomatoDailyFromJsonWeb("16505933"));

        model.addAttribute("restaurants", zomatoService.getZomatoNearbyRestaurantFromRestApi("50.070008","14.403369"));

//        Map<String, String> map = zomatoService.getZomatoRestaurantFromJsonFile("16506246");
//
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//           model.addAttribute(entry.getKey().toString(), entry.getValue());
//        }

//        return "dailyMenu";
        return "view";

    }


}
