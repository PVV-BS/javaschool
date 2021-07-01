package ru.bs.controller;

import org.slf4j.Logger;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bs.entity.Account;
import ru.bs.entity.Client;
import ru.bs.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class AtmWebController {

    final private ClientService clientService;

    final private Logger logger;

    private Client getSessionClient(HttpServletRequest request)
    {
        return (Client) request.getSession().getAttribute("client");
    }

    public boolean isAuthenticated(HttpServletRequest request)
    {
        return getSessionClient(request) != null;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model)
    {
        logger.info("index");
        return "index";
    }

    @GetMapping(value = "/verifyCode")
    public String verifyCode(Model model, String pinCode, String account, HttpServletRequest request)
    {
        Client client = clientService.getClientByPinCodeAndAccount(Integer.parseInt(pinCode), account);

        // redirect
        if (client == null) {
            logger.error("Login fail with pin-code " + pinCode + " and account " + account);
            request.getSession().setAttribute("error_log", "WRONG PIN-CODE");
            return "redirect:/index";
        }

        Account acc = clientService.getClientAccount(client, account);
        request.getSession().removeAttribute("error_log");
        request.getSession().setAttribute("client", client);
        request.getSession().setAttribute("account", acc);
        request.getSession().setAttribute("balance", new DecimalFormat( "###,###.##" ).format(acc.getBalance()));
        request.getSession().setAttribute("today", LocalDate.now());

        // authorization
        List<String> services = clientService.getClientServices(client);
        request.getSession().setAttribute("services", services);
        logger.info("Login " + client.getName());

        return "redirect:/welcome";
    }

    @GetMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest request)
    {
        if ( !isAuthenticated(request) ) {
            return "redirect:/index";
        }

        return "welcome";
    }

    @GetMapping(value = "/get_service")
    public String getService(Model model, String service, HttpServletRequest request)
    {
        if ( !isAuthenticated(request) ) {
            logger.error("Fail to get service " + service);
            return "redirect:/index";
        }

        List<String> services = (List<String>) request.getSession().getAttribute("services");
        if (services == null) {
            logger.error("Fail to get service " + service);
            return "redirect:/index";
        }

        if (! services.contains(service) ) {
            logger.error("Fail to get service " + service);
            return "redirect:/index";
        }

        logger.info("Redirect to service " + service);
        return "redirect:/" + service;
    }

    @GetMapping(value = "/Check balance")
    public String checkBalance(HttpServletRequest request)
    {
        if (! isAuthenticated(request) ) {
            logger.error("Fail to get service Check balance");
            return "redirect:/index";
        }
        logger.info("Check balance");

        return "balance";
    }

    @GetMapping(value = "/Get cash")
    public String getCash(HttpServletRequest request)
    {
        if (! isAuthenticated(request) ) {
            logger.error("Fail to get service Get cash");
            return "redirect:/index";
        }
        logger.info("Get cash");

        return "todo";
    }

    @GetMapping(value = "/Make a transfer")
    public String makeTransfer(HttpServletRequest request)
    {
        if (! isAuthenticated(request) ) {
            logger.error("Fail to get service Make a transfer");
            return "redirect:/index";
        }

        logger.info("Make a transfer");
        return "todo";
    }

    @GetMapping(value = "/Put cash")
    public String putCash(HttpServletRequest request)
    {
        if (! isAuthenticated(request) ) {
            logger.error("Fail to get service Put cash");
            return "redirect:/index";
        }

        logger.info("Put cash");
        return "todo";
    }

    @GetMapping(value = "/Exit")
    public String exit(HttpServletRequest request)
    {
        request.getSession().removeAttribute("client");
        request.getSession().removeAttribute("services");
        request.getSession().removeAttribute("account");
        logger.info("Exit");
        return "redirect:/index";
    }


}
