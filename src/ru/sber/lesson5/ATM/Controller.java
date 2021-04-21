package ru.sber.lesson5.ATM;

import com.sun.net.httpserver.HttpServer;
import ru.sber.lesson5.ATM.Model.Account;
import ru.sber.lesson5.ATM.Model.Client;
import ru.sber.lesson5.ATM.Model.CurrencyIsoCode;
import ru.sber.lesson5.ATM.Transport.AuthResponse;
import ru.sber.lesson5.ATM.Transport.AuthResponseResult;
import ru.sber.lesson5.ATM.Model.Card;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class Controller {
    public final static int SERVER_PORT_DEFAULT = 18188;


    private HttpServer server;
    //private Hashtable<String, Function<Map<String, String>, String>> RequestHandlers;
    private Map<String, Client> Clients;

    public Controller() throws IOException {

        //Hashtable<String, Function<Map<String, String>, String>> RequestHandlers = new Hashtable<String, Function<Map<String, String>, String>>();
        //RequestHandlers.put("authorization", this::authorization);

        Clients = new HashMap<>();

        createTestData();

        HttpServer server = HttpServer.create(new InetSocketAddress(Controller.SERVER_PORT_DEFAULT), 0);
        server.createContext("/api/atm/authorization", httpExchange->{
            if ("GET".equals(httpExchange.getRequestMethod())) {
                Map<String, String> attributes = parseUri(httpExchange.getRequestURI().toString());
                AuthResponse response = authorization(attributes);
                String result = response.toString();
                httpExchange.sendResponseHeaders(200, result.getBytes().length);
                OutputStream output = httpExchange.getResponseBody();
                output.write(result.getBytes());
                output.flush();
            } else {
                httpExchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            httpExchange.close();
        });
        server.setExecutor(null); // creates a default executor
        server.start();

    }

    // TODO: dataset instead it
    private void createTestData()
    {
        Account account = new Account();
        account.setAccount("12345810123456789012");
        account.setAvailableCash(new BigDecimal(123456));
        account.setCurrencyIsoCode(CurrencyIsoCode.RUB);

        Card card = new Card();
        card.setAccount(account);
        card.setNumber("1234567812345678");
        card.setCvs(123);
        card.setEndService(LocalDate.parse("010624", DateTimeFormatter.ofPattern("ddMMyy")));

        Client client = new Client();
        client.setBirthday(LocalDate.parse("23062000", DateTimeFormatter.ofPattern("ddMMyyyy")));
        client.setNumber(1);
        client.setFirstName("Ivan");
        client.setSecondName("Ivanov");
        client.setAccount(account);
        client.setCard(card);
        Clients.put((client.getNumber()).toString(), client);
    }

    private Map<String, String> parseUri(String Uri)
    {
        Map<String, String> result = new HashMap<>();
        String[] strings;
        strings = Uri.split("\\?", 2);
        if (strings.length < 2)
            return result;
        strings = strings[1].split("&");
        for (String pair : strings) {
            String[] keyValue = pair.split("=");
            if (keyValue.length != 2)
                continue;
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }

    private AuthResponse authorization(Map<String, String> arguments)
    {
        String numberCard = arguments.getOrDefault("numberCard", "");
        if (numberCard.isEmpty())
            return new AuthResponse(AuthResponseResult.AUTH_BAD_REQUEST);

        String numberClient = arguments.getOrDefault("numberClient", "");
        if (numberClient.isEmpty())
            return new AuthResponse(AuthResponseResult.AUTH_BAD_REQUEST);

        String cvs = arguments.getOrDefault("cvs", "");
        if (cvs.isEmpty())
            return new AuthResponse(AuthResponseResult.AUTH_BAD_REQUEST);

        String dateEnd = arguments.getOrDefault("dateEnd", "");
        if (dateEnd.isEmpty())
            return new AuthResponse(AuthResponseResult.AUTH_BAD_REQUEST);

        String pinCode = arguments.getOrDefault("pinCode", "");
        if (pinCode.isEmpty())
            return new AuthResponse(AuthResponseResult.AUTH_BAD_REQUEST);

        Client client = Clients.get(numberClient);

        if (client == null)
            return new AuthResponse(AuthResponseResult.AUTH_CLIENT_NOT_FOUND);

        Optional<Card> card  = client.getCard(numberCard);

        if (!card.isPresent())
            return new AuthResponse(AuthResponseResult.AUTH_CARD_NOT_REGISTERED);

        LocalDate date = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("ddMMyy"));

        if (date.compareTo(LocalDate.now()) < 0)
            return new AuthResponse(AuthResponseResult.AUTH_WRONG_DATE_CARD);

        Integer cardCsv = card.get().getCVS();
        if (cvs.compareTo(cardCsv.toString()) != 0)
            return new AuthResponse(AuthResponseResult.AUTH_WRONG_CVS);

        Integer cardPinCode =card.get().getPinCode();
        if (pinCode.compareTo(cardPinCode.toString()) != 0)
            return new AuthResponse(AuthResponseResult.AUTH_WRONG_CODE);

        AuthResponse result = new AuthResponse(AuthResponseResult.AUTH_AUTHORIZATION_SUCCESS);
        result.setAccount(card.get().getAccount());
        result.setBalance(card.get().getAvailableCash().toString());
        result.setCurrencyCodeIso(card.get().getCurrencyIsoCode());
        return result;
    }

}
