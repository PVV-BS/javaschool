package ru.bs.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AtmWebControllerTest {

    private final String TEST_PIN_CODE = "1111";
    private final String TEST_ACCOUNT = "11111111111111111111";

    @Autowired
    AtmWebController controller;

    private HttpServletRequest request;

    AtmWebControllerTest() {
        request = new MockHttpServletRequest();
    }

    @BeforeEach
    void Before() {
        if (!controller.isAuthenticated(request)) {
            controller.verifyCode(null, TEST_PIN_CODE, TEST_ACCOUNT, request);
        }
    }

    @Test
    void index() {
        assertEquals("index", controller.index(null), "controller.index fail!");
    }

    @Test
    void verifyCode() {
        assertEquals("redirect:/welcome", controller.verifyCode(null, TEST_PIN_CODE, TEST_ACCOUNT, request), "controller.verifyCode fail!");
    }

    @Test
    void welcome() {
        assertEquals("welcome", controller.welcome(null, request), "controller.welcome fail!");
    }

    @Test
    void getService() {
        assertEquals("redirect:/Check balance", controller.getService(null, "Check balance", request), "controller.getService Check balance fail!");
        assertEquals("redirect:/Get cash", controller.getService(null, "Get cash", request), "controller.getService Get cash fail!");
        assertEquals("redirect:/Make a transfer", controller.getService(null, "Make a transfer", request), "controller.getService Make a transfer fail!");
        assertEquals("redirect:/Put cash", controller.getService(null, "Put cash", request), "controller.getService Put cash fail!");
        assertEquals("redirect:/Exit", controller.getService(null, "Exit", request), "controller.getService Exit fail!");
    }

    @Test
    void checkBalance() {
        assertEquals("balance", controller.checkBalance(request), "controller.checkBalance fail!");
    }

    @Test
    void getCash() {
        assertEquals("todo", controller.getCash(request), "controller.getCash fail!");
    }

    @Test
    void exit() {
        assertEquals("redirect:/index", controller.exit(request), "controller.exit fail!");
    }
}