package com.github.pvv.jdbcexample;

import com.github.pvv.ExampleJDBC;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleJDBCIT implements Consumer<String> {


    private ExampleJDBC exampleJDBC = new ExampleJDBC("jdbc:h2:target/example");
    private List<String> data;

    @BeforeAll
    static  void setUp() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    ExampleJDBCIT()
    {
        data = new ArrayList<String>();
    }

    @Test
    void selectExample() throws SQLException {

        assertEquals("VW", exampleJDBC.selectColumn().get());
    }

    @Test
    void selectColumns() throws SQLException {
        exampleJDBC.selectColumns(this);
        //assertEquals(1, data.size());
        assertEquals("Name: JACK, age: 5", data.get(0));
    }

    @Override
    public void accept(String s) {
        data.add(s);
    }
}
