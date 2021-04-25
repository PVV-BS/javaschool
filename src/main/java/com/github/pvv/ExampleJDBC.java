package com.github.pvv;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExampleJDBC {
    private String url;

    public ExampleJDBC(String url) {
        this.url = url;
    }

    public Optional<String> selectColumn() throws SQLException {
        try(Connection connection = getConnection().orElseThrow(JDBCConnectionException::new)) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery("select name from machine")) {
                    if (resultSet.next()) {
                        return Optional.of(resultSet.getString("NAME"));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        }
    }

    public void selectColumns(Consumer<String> consumer) throws SQLException {
        try(Connection connection = getConnection().orElseThrow(JDBCConnectionException::new)) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery("select name, age from animal")) {
                    while (resultSet.next()) {
                        consumer.accept("Name: " + resultSet.getString("NAME") + ", age: " + resultSet.getString("age"));
                    }
                }
            }
        }
    }

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.ofNullable(DriverManager.getConnection(url));
    }

}
