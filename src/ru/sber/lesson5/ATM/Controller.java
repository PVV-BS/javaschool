package ru.sber.lesson5.ATM;

import com.sun.net.httpserver.HttpServer;
import ru.sber.lesson5.ATM.Model.Account;
import ru.sber.lesson5.ATM.Model.Client;
import ru.sber.lesson5.ATM.Transport.AuthResponse;
import ru.sber.lesson5.Card.Card;

import javax.print.attribute.standard.MediaSizeName;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Controller {

    public Controller() throws IOException {

        //Hashtable<String, Function<Map<String, String>, String>> RequestHandlers = new Hashtable<String, Function<Map<String, String>, String>>();
        //RequestHandlers.put("authorization", this::authorization);

        Clients = new Hashtable<String, Client>();

        CreateTestData();

        HttpServer server = HttpServer.create(new InetSocketAddress(Constants.SERVER_PORT_DEFAULT), 0);
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
    private void CreateTestData()
    {
        Client client = new Client();
        client.setBirthday(LocalDate.parse("23062000", DateTimeFormatter.ofPattern("ddMMyyyy")));
        client.setNumber(1);
        client.setFirstName("Ivan");
        client.setSecondName("Ivanov");

        Hashtable<String, Account> accounts = new Hashtable<String, Account>();
        Account account = new Account();
        account.setAccount("12345810123456789012");
        account.setAvailableCash(new BigDecimal(123456));
        account.setCurrencyIsoCode("RUB");
        accounts.put(account.getAccount(), account);

        Card card = new Card();
        card.setAccount(account);
        card.setNumber("1234567812345678");
        card.setCSV(123);

        card.setEndService(LocalDate.parse("010624", DateTimeFormatter.ofPattern("ddMMyy")));
        Hashtable<String, Card> cards = new Hashtable<String, Card>();
        cards.put(card.getNumber(), card);

        client.setCards(cards);
        client.setAccounts(accounts);
        Clients.put((new Integer(client.getNumber())).toString(), client);
    }

    private Map<String, String> parseUri(String Uri)
    {
        Map<String, String> result = new HashMap<String, String>();
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
            return new AuthResponse(Constants.AUTH_BAD_REQUEST);

        String numberClient = arguments.getOrDefault("numberClient", "");
        if (numberClient.isEmpty())
            return new AuthResponse(Constants.AUTH_BAD_REQUEST);

        String csv = arguments.getOrDefault("csv", "");
        if (csv.isEmpty())
            return new AuthResponse(Constants.AUTH_BAD_REQUEST);

        String dateEnd = arguments.getOrDefault("dateEnd", "");
        if (dateEnd.isEmpty())
            return new AuthResponse(Constants.AUTH_BAD_REQUEST);

        Client client = Clients.get(numberClient);

        if (client == null)
            return new AuthResponse(Constants.AUTH_CLIENT_NOT_FOUND);

        Card card = client.getCards().get(numberCard);

        if (card == null)
            return new AuthResponse(Constants.AUTH_CARD_NOT_REGISTERED);

        Integer cardCsv = card.getCSV();
        if (csv.compareTo(cardCsv.toString()) != 0)
            return new AuthResponse(Constants.AUTH_WRONG_CODE);

        LocalDate date = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("ddMMyy"));

        if (date.compareTo(LocalDate.now()) < 0)
            return new AuthResponse(Constants.AUTH_WRONG_DATE_CARD);

        AuthResponse result = new AuthResponse(Constants.AUTH_AUTHORIZATION_SUCCESS);
        result.setAccount(card.getAccount());
        result.setBalance(card.getAvailableCash().toString());
        result.setCurrencyCodeIso(card.getCurrencyIsoCode());
        return result;
    }

    private HttpServer server;
    //private Hashtable<String, Function<Map<String, String>, String>> RequestHandlers;
    private Hashtable<String, Client> Clients;

}
