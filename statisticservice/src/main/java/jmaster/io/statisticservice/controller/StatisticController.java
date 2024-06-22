package jmaster.io.statisticservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jmaster.io.statisticservice.model.StatisticDTO;
import jmaster.io.statisticservice.service.StatisticService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StatisticController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticService statisticService;

    // Add new
    @PostMapping("/statistic")
    public StatisticDTO add(@RequestBody StatisticDTO statisticDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	logger.debug("Add statistic");
	
//	try {
//	    Thread.sleep(10000);
//	} catch (InterruptedException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
	
	statisticService.add(statisticDTO);
	return statisticDTO;
    }

    // get all
    @GetMapping("/statistics")
    public List<StatisticDTO> getAll() {
	logger.debug("Get all statistic");

	return statisticService.getAll();
    }
}