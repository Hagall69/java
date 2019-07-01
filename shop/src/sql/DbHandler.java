package sql;

import clients.Client;
import employees.Employee;
import menu.Tables;
import org.sqlite.JDBC;
import products.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DbHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:base\\TestBase";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance(){
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    private DbHandler(){
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR);
        } catch (SQLException e) {
            System.out.println("Нет подключения к базе");
            System.out.println();
        }
    }

    /**
     *
     * @param table - название запрашиваемой таблицы
     * @return возвращает список в виде объектов из таблицы, пибо пустой список в случае ошибки
     */

    public List<?> getAll(Tables table) {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            List<Employee> employees = new ArrayList<>();
            List<Client> clients = new ArrayList<>();
            List<Product> products = new ArrayList<>();
            String select;
            String[] column;
            ResultSet resultSet;
            switch (table){
                case EMPLOYEES:
                    select = "id, name, position, salary";
                    column = select.split(",\\s");
                    // В resultSet будет храниться результат запроса,
                    // который выполняется командой statement.executeQuery()
                    resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", select, table.toString()));
                    // Проходимся по нашему resultSet и заносим данные в List
                    while (resultSet.next()) {
                        employees.add(new Employee(resultSet.getInt(column[0]),
                                resultSet.getString(column[1]),
                                resultSet.getString(column[2]),
                                resultSet.getInt(column[3])));
                    }
                    return employees;
                case CLIENTS:
                    select = "id, name, points";
                    column = select.split(",\\s");
                    resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", select, table.toString()));
                    while (resultSet.next()) {
                        clients.add(new Client(resultSet.getInt(column[0]),
                                resultSet.getString(column[1]),
                                resultSet.getInt(column[2])));
                    }
                    return clients;
                case PRODUCTS:
                    select = "id, barcode, name, price, discount";
                    column = select.split(",\\s");
                    resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", select, table.toString()));
                    while (resultSet.next()) {
                        products.add(new Product(resultSet.getInt(column[0]),
                                resultSet.getString(column[1]),
                                resultSet.getString(column[2]),
                                resultSet.getDouble(column[3]),
                                resultSet.getInt(column[4])));
                    }
                    return products;
                default:
                    return Collections.emptyList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();

        }
    }

    /**
     *
     * @param table - название запрашиваемой таблицы
     * @param values - список всех значений для новой строки таблицы
     */

    public void add(Tables table, ArrayList<?> values) {
        String col = "";
        switch (table){
            case EMPLOYEES:
                col = "('name', 'position', 'salary') VALUES (?, ?, ?)";
                break;
            case CLIENTS:
                col = "('name', 'points') VALUES (?, ?)";
                break;
            case PRODUCTS:
                col = "('barcode', 'name', 'price', 'discount') VALUES (?, ?, ?, ?)";
                break;
        }
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("INSERT INTO %s %s", table.toString(), col))){
            int i = 1;
            for (Object o : values) {
                statement.setObject(i, o);
                i++;
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param column - название колонки поиска
     * @param value - значение колонки
     * @param table - таблица в которой необходимо найти строку
     * @return возвращает соответствующий объект, либо null в случае ошибки
     */

    public ArrayList<?> find(String column, String value, Tables table) {
        try(Statement statement = this.connection.createStatement()){
            String select = "";
            ResultSet resultSet;
            ArrayList<Object> result = new ArrayList<>();
            switch (table){
                case EMPLOYEES:
                    select = "id, name, position, salary";
                    break;
                case CLIENTS:
                    select = "id, name, points";
                    break;
                case PRODUCTS:
                    select = "id, barcode, name, price, discount";
                    break;
            }
            //условие для строковых типов
            if (column.equals("name") || column.equals("position") || column.equals("barcode")) {
                resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = '%s'",
                        select, table.toString(), column, value));
            } else {
                resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = %s",
                        select, table.toString(), column, value));
            }
            switch (table) {
                case EMPLOYEES:
                    while (resultSet.next()) {
                        result.add(new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("position"), resultSet.getInt("salary")));
                    }
                    if (result.isEmpty()){
                        result.add("Объект не найден");
                        return result;
                    }
                    return result;
                case CLIENTS:
                    while (resultSet.next()) {
                        result.add(new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("points")));
                    }
                    if (result.isEmpty()){
                        result.add("Объект не найден");
                        return result;
                    }
                    return result;
                case PRODUCTS:
                    while (resultSet.next()) {
                        result.add(new Product(resultSet.getInt("id"), resultSet.getString("barcode"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getInt("discount")));
                    }
                    if (result.isEmpty()){
                        result.add("Объект не найден");
                        return result;
                    }
                    return result;
            }
            ArrayList<String> notFound = new ArrayList<>();
            notFound.add("Объект не найден");
            return notFound;
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<String> notFound = new ArrayList<>();
            notFound.add("Объект не найден");
            return notFound;
        }
    }

    /**
     *
     * @param table - название таблицы
     * @param id - id строки в таблице
     * @param columns - список колонок которые необходимо заменить
     * @param values - список новых значений для этих колонок
     */

    public void replace(Tables table, int id, ArrayList<String> columns, ArrayList<?> values){
        StringBuilder set = new StringBuilder();
        int i = 0;
        while (i < columns.size()) {
            if (i > 0) {
                set.append(", ");
            }
            //условие для строковых типов
            if ("name".equals(columns.get(i)) ||
                    "position".equals(columns.get(i)) ||
                    "barcode".equals(columns.get(i))) {
                set.append(String.format("%s = '%s'", columns.get(i), values.get(i)));
            } else {
                set.append(String.format("%s = %s", columns.get(i), values.get(i)));
            }
            i++;
        }
        try(PreparedStatement statement = this.connection.prepareStatement
                (String.format("UPDATE %s SET %s WHERE id = %s", table.toString(), set, id))){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param table - название таблицы
     * @param id - id строки в таблице
     */

    public void delete(Tables table, int id) {
        try(PreparedStatement statement = this.connection.prepareStatement
                (String.format("DELETE FROM %s WHERE id = %s", table.toString(), id))){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}