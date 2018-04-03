package cz.ivosahlik.zomatoapi.cache;

import cz.ivosahlik.zomatoapi.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 02/04/2018
 */
@Slf4j
@Component
public class Cache {

    /**
     *
     * Run every day
     *
     * This method remove cache on server, launch remove cache is set cron
     *
     * 0 0 5,17 * * * = 5:00 AM and 5:00 PM every day.
     * 0 0 5 * * * = 5:00 AM every day.
     *
     */
//    @Scheduled(cron = "*/20 * * * * *")
//    @Scheduled(fixedDelay=5_000)
    @Scheduled(fixedDelay = 20_000)
//    @Scheduled(cron = "0 0 6 * * *")
    @CacheEvict(value = Constants.ZOMATO_CACHE_NEARBY_RESTAURANT, allEntries = true)
    public void clearCache() {
        log.info("cache cleared ... {}", LocalDateTime.now());
    }

}
