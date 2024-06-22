package accountservice.accountservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import accountservice.accountservice.client.NotificationService;
import accountservice.accountservice.client.StatisticService;
import accountservice.accountservice.model.MessageDTO;
import accountservice.accountservice.model.StatisticDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import accountservice.accountservice.model.AccountDTO;
import accountservice.accountservice.service.AccountService;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private NotificationService notificationService;
    
    // add new
    @PostMapping("/account")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
        log.info("[addAccount] -> Execute");
        accountService.add(accountDTO);
        statisticService.add(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("nguyenquangos0302code@gmail.com");
        messageDTO.setTo(accountDTO.getUsername());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome to Master");
        messageDTO.setContent("Master is online");
        notificationService.sendNotification(messageDTO);
        return accountDTO;
    }

    // get all
    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        log.info("[getAll] -> Execute");
        statisticService.add(new StatisticDTO("Get all", new Date()));
	    return accountService.getAll();
    }
    
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable(name = "id") Long id) {
        log.info("[get] -> Execute");
        statisticService.add(new StatisticDTO("Get account with id " + id, new Date()));
	    return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK))
		    .orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        log.info("[delete] -> Execute");
        accountService.delete(id);
        statisticService.add(new StatisticDTO("Delete account with id " + id + " success ", new Date()));
    }

    @PutMapping("/account")
    public void update(@RequestBody AccountDTO accountDTO) {
        log.info("[update] -> Execute");
        accountService.update(accountDTO);
        statisticService.add(new StatisticDTO("Update account  " + accountDTO.getUsername() + " success", new Date()));
    }

}
