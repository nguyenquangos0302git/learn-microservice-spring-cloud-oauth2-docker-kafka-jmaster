package accountservice.accountservice.client;

import accountservice.accountservice.model.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "statistic-service", fallback = StatisticServiceImpl.class, configuration = NotificationFeignClientConfig.class)
public interface StatisticService {

//    @PostMapping("/statistic")
//    void add(@RequestBody StatisticDTO statisticDTO, @RequestHeader("Authorization") String bearerToken);

    @PostMapping("/statistic")
    void add(@RequestBody StatisticDTO statisticDTO);

}
