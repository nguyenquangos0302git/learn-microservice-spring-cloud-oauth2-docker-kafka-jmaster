package accountservice.accountservice.client;

import accountservice.accountservice.model.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StatisticServiceImpl implements StatisticService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void add(StatisticDTO statisticDTO) {
        // fallback
        logger.error("Error");
    }
}
