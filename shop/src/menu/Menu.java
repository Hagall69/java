package menu;

import clients.Client;
import employees.Employee;
import products.Product;
import sql.DbHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class Menu {

    private final DbHandler dbHandler = DbHandler.getInstance();

    public Menu(){
    }

    private String reader(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return "Ошибка ввода";
        }
    }

    public void showMenu() {
        while (true) {
            for (MenuItem m : MenuItem.values()) {
                System.out.println((String.format("%s. %s", m.ordinal() + 1, m.toString())));
            }
            String item = reader();
            if (item.equals(Integer.toString(MenuItem.EXIT.ordinal() + 1))){
                exit();
                break;
            }
            if (item.equals(Integer.toString(MenuItem.CLIENTS.ordinal() + 1))){
                showMenuContent(Tables.CLIENTS);
                continue;
            }
            if (item.equals(Integer.toString(MenuItem.EMPLOYEES.ordinal() + 1))){
                showMenuContent(Tables.EMPLOYEES);
                continue;
            }
            if (item.equals(Integer.toString(MenuItem.PRODUCTS.ordinal() + 1))){
                showMenuContent(Tables.PRODUCTS);
                continue;
            }
            failed();
        }
    }

    private void exit(){System.exit(0);}

    private void failed(){
        System.out.println("Неверная команда\n");
    }

    private void showMenuContent(Tables table){
        MenuItem menuItem = MenuItem.EXIT;
        String[] graduation = {};
        switch (table) {
            case CLIENTS: {
                graduation = new String[]{"ы", "а", "а", "а", "а", ""};
                menuItem = MenuItem.CLIENTS;
                break;
            }
            case EMPLOYEES: {
                graduation = new String[]{"и", "а", "а", "а", "а", ""};
                menuItem = MenuItem.EMPLOYEES;
                break;
            }
            case PRODUCTS: {
                graduation = new String[]{"ы", "", "", "", "", ""};
                menuItem = MenuItem.PRODUCTS;
                break;
            }
        }
        while (true) {
            int i = 0;
            int n = 0;
            for (MenuContent m : MenuContent.values()) {
                System.out.println(String.format("%s. %s", ++n, m.toString(menuItem.toString(graduation[i++]))));
            }
            String item = reader();
            if (item.equals(Integer.toString(MenuContent.PREVIOUS_MENU.ordinal() + 1))) {
                showMenu();
                break;
            }
            if (item.equals(Integer.toString(MenuContent.ALL.ordinal() + 1))) {
                showMenuGetAll(table);
                continue;
            }
            if (item.equals(Integer.toString(MenuContent.ADD.ordinal() + 1))) {
                showMenuAdd(table);
                continue;
            }
            if (item.equals(Integer.toString(MenuContent.FIND.ordinal() + 1))) {
                showMenuFind(table);
                continue;
            }
            if (item.equals(Integer.toString(MenuContent.REPLACE.ordinal() + 1))) {
                showMenuReplace(table);
                continue;
            }
            if (item.equals(Integer.toString(MenuContent.DELETE.ordinal() + 1))) {
                showMenuDelete(table);
                continue;
            }
            failed();
        }
    }

    private void showMenuGetAll(Tables table){
        //DbHandler dbHandler = DbHandler.getInstance();
        try {
            List<?> list = dbHandler.getAll(table);
            for (Object o : list) {
                System.out.println(o.toString());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Нет подключения к базе");
            System.out.println();
        }
    }

    private String type(String item){
        try{
            //noinspection deprecation
            Double number = new Double(item);
            if(number == number.intValue()){
                return "int";
            }else{
                return "double";
            }
        }catch(NumberFormatException e){
            return "string";
        }
    }

    private boolean typeCheck(String str, String item) {
        if (!str.equals(type(item))) {
            System.out.println("Неверный тип данных\n");
            return true;
        }
        return false;
    }

    private void showMenuAdd(Tables table){
        //DbHandler dbHandler = DbHandler.getInstance();
        ArrayList<Object> columns = new ArrayList<>();
        String item = "";
        switch (table) {
            case CLIENTS:
                while (true) {
                    System.out.println("Введите Фамилию: ");
                    String f = reader();
                    if (typeCheck("string", f)) {
                        continue;
                    }
                    item = item + f;
                    break;
                }
                while (true) {
                    System.out.println("Введите Имя: ");
                    String i = reader();
                    if (typeCheck("string", i)) {
                        continue;
                    }
                    item = String.format("%s %s", item, i);
                    break;
                }
                while (true) {
                    System.out.println("Введите Отчетво: ");
                    String o = reader();
                    if (typeCheck("string", o)) {
                        continue;
                    }
                    item = String.format("%s %s", item, o);
                    break;
                }
                columns.add(item);
                while (true) {
                    System.out.println("Введите количество баллов: ");
                    item = reader();
                    if (typeCheck("int", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                break;
            case EMPLOYEES:
                while (true) {
                    System.out.println("Введите Фамилию: ");
                    String f = reader();
                    if (typeCheck("string", f)) {
                        continue;
                    }
                    item = item + f;
                    break;
                }
                while (true) {
                    System.out.println("Введите Имя: ");
                    String i = reader();
                    if (typeCheck("string", i)) {
                        continue;
                    }
                    item = String.format("%s %s", item, i);
                    break;
                }
                while (true) {
                    System.out.println("Введите Отчетво: ");
                    String o = reader();
                    if (typeCheck("string", o)) {
                        continue;
                    }
                    item = String.format("%s %s", item, o);
                    break;
                }
                columns.add(item);
                while (true) {
                    System.out.println("Введите должность: ");
                    item = reader();
                    if (typeCheck("string", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                while (true) {
                    System.out.println("Введите з/п: ");
                    item = reader();
                    if (typeCheck("int", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                break;
            case PRODUCTS:
                while (true) {
                    System.out.println("Введите штрихкод: ");
                    item = reader();
                    if (typeCheck("string", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(1 + item);
                while (true) {
                    System.out.println("Введите наименование: ");
                    item = reader();
                    if (typeCheck("string", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                while (true) {
                    System.out.println("Введите цену: ");
                    item = reader();
                    if (typeCheck("double", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                while (true) {
                    System.out.println("Введите скидку: ");
                    item = reader();
                    if (typeCheck("int", item)) {
                        continue;
                    }
                    break;
                }
                columns.add(item);
                break;
        }
        dbHandler.add(table, columns);
    }

    private void showMenuFind(Tables table){
        //DbHandler dbHandler = DbHandler.getInstance();
        ArrayList<?> result;
        String item = "";
        String column = "";
        System.out.println("По какому значению искать?: ");
        switch (table) {
            case CLIENTS: {
                for (MenuFindClients menu : MenuFindClients.values()){
                    System.out.println(String.format("%d. %s", menu.ordinal()+1, menu.toString()));
                }
                while (true) {
                    item = reader();
                    if (item.equals(Integer.toString(MenuFindClients.ID.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindClients.ID.toString()));
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindClients.ID.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindClients.FULL_NAME.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindClients.FULL_NAME.toString()));
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindClients.FULL_NAME.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindClients.CANCEL.ordinal() + 1))) {
                        showMenuContent(table);
                        break;
                    }
                    failed();
                }
                break;
            }
            case EMPLOYEES: {
                for (MenuFindEmployees menu : MenuFindEmployees.values()){
                    System.out.println(String.format("%d. %s", menu.ordinal()+1, menu.toString()));
                }
                while (true) {
                    item = reader();
                    if (item.equals(Integer.toString(MenuFindEmployees.ID.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindEmployees.ID.toString()));
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindEmployees.ID.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindEmployees.FULL_NAME.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindEmployees.FULL_NAME.toString()));
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindEmployees.FULL_NAME.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindEmployees.POSITION.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindEmployees.POSITION.toString()));
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindEmployees.POSITION.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindEmployees.CANCEL.ordinal() + 1))) {
                        showMenuContent(table);
                        break;
                    }
                    failed();
                }
                break;
            }
            case PRODUCTS: {
                for (MenuFindProducts menu : MenuFindProducts.values()){
                    System.out.println(String.format("%d. %s", menu.ordinal()+1, menu.toString()));
                }
                while (true) {
                    item = reader();
                    if (item.equals(Integer.toString(MenuFindProducts.ID.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindProducts.ID.toString()));
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindProducts.ID.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindProducts.NAME.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindProducts.NAME.toString()));
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindProducts.NAME.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindProducts.BARCODE.ordinal() + 1))) {
                        while (true) {
                            System.out.println(String.format("Введите %s", MenuFindProducts.BARCODE.toString()));
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        column = MenuFindProducts.BARCODE.toColumn();
                        break;
                    }
                    if (item.equals(Integer.toString(MenuFindEmployees.CANCEL.ordinal() + 1))) {
                        showMenuContent(table);
                        break;
                    }
                }
                break;
            }
        }
        result = dbHandler.find(column, item, table);
        for (Object o : result) {
            System.out.println(o.toString());
        }
        System.out.println();
    }

    private void showMenuReplace(Tables table){
        //DbHandler dbHandler = DbHandler.getInstance();
        String name = "";
        String item;
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        switch (table) {
            case CLIENTS: {
                name = "Клиента";
                break;
            }
            case EMPLOYEES: {
                name = "Сотрудника";
                break;
            }
            case PRODUCTS: {
                name = "Товара";
                break;
            }
        }
        while (true) {
            System.out.println("Введите ID " + name);
            item = reader();
            if (typeCheck("int", item)) {
                continue;
            }
            break;
        }
        int id = Integer.parseInt(item);
        ArrayList<?> obj = dbHandler.find("id", Integer.toString(id), table);
        for (Object o : obj) {
            System.out.println(o.toString());
            if (o.equals("Объект не найден")){
                System.out.println();
                showMenuContent(table);
                break;
            }
        }
        System.out.println();
        String[] read;
        while (true) {
            System.out.println("Что хотите изменить (можно выбрать несколько значений через пробел)?:");
            switch (table) {
                case CLIENTS:
                    for (MenuReplaceClients menu : MenuReplaceClients.values()) {
                        System.out.println(String.format("%d. %s", menu.ordinal() + 1, menu.toString()));
                    }
                    break;
                case EMPLOYEES:
                    for (MenuReplaceEmployees menu : MenuReplaceEmployees.values()) {
                        System.out.println(String.format("%d. %s", menu.ordinal() + 1, menu.toString()));
                    }
                    break;
                case PRODUCTS:
                    for (MenuReplaceProducts menu : MenuReplaceProducts.values()) {
                        System.out.println(String.format("%d. %s", menu.ordinal() + 1, menu.toString()));
                    }
                    break;
            }
            item = reader();
            read = item.split("\\s");
            boolean b = false;
            for (String n : read) {
                if (typeCheck("int", n)) {
                    b = true;
                    break;
                }
            }
            if (b) {
                failed();
                continue;
            }
            int i = 0;
            for (String column : read) {
                switch (table) {
                    case CLIENTS:
                        for (MenuReplaceClients menu : MenuReplaceClients.values()) {
                            if (menu.ordinal() + 1 == Integer.parseInt(column)) {
                                i++;
                            }
                        }
                        break;
                    case EMPLOYEES:
                        for (MenuReplaceEmployees menu : MenuReplaceEmployees.values()) {
                            if (menu.ordinal() + 1 == Integer.parseInt(column)) {
                                i++;
                            }
                        }
                        break;
                    case PRODUCTS:
                        for (MenuReplaceProducts menu : MenuReplaceProducts.values()) {
                            if (menu.ordinal() + 1 == Integer.parseInt(column)) {
                                i++;
                            }
                        }
                        break;
                }
            }
            if (i != read.length) {
                failed();
                continue;
            }
            break;
        }

        for (String column : read) {
            switch (table) {
                case CLIENTS:
                    if (Integer.parseInt(column) == MenuReplaceClients.CANCEL.ordinal() + 1) {
                        System.out.println("Отмена\n");
                        showMenuContent(table);
                        break;
                    }
                    break;
                case EMPLOYEES:
                    if (Integer.parseInt(column) == MenuReplaceEmployees.CANCEL.ordinal() + 1) {
                        System.out.println("Отмена\n");
                        showMenuContent(table);
                        break;
                    }
                    break;
                case PRODUCTS:
                    if (Integer.parseInt(column) == MenuReplaceProducts.CANCEL.ordinal() + 1) {
                        System.out.println("Отмена\n");
                        showMenuContent(table);
                        break;
                    }
                    break;
            }
        }
        Client clientFind = new Client();
        Client clientReplace = new Client();
        Employee employeeFind = new Employee();
        Employee employeeReplace = new Employee();
        Product productFind = new Product();
        Product productReplace = new Product();
        switch (table) {
            case CLIENTS: {
                clientFind = (Client) obj.get(0);
                clientReplace.copyClient(clientFind);
                break;
            }
            case EMPLOYEES: {
                employeeFind = (Employee) obj.get(0);
                employeeReplace.copyEmployee(employeeFind);
                break;
            }
            case PRODUCTS: {
                productFind = (Product) obj.get(0);
                productReplace.copyProduct(productFind);
                break;
            }
        }
        for (String column : read){
            switch (table) {
                case CLIENTS:
                    if (Integer.parseInt(column) == MenuReplaceClients.FULL_NAME.ordinal() + 1){
                        columns.add(MenuReplaceClients.FULL_NAME.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceClients.FULL_NAME.toString()));
                        while (true) {
                            System.out.println("Введите Фамилию: ");
                            String f = reader();
                            if (typeCheck("string", f)) {
                                continue;
                            }
                            item = f;
                            break;
                        }
                        while (true) {
                            System.out.println("Введите Имя: ");
                            String i = reader();
                            if (typeCheck("string", i)) {
                                continue;
                            }
                            item = String.format("%s %s", item, i);
                            break;
                        }
                        while (true) {
                            System.out.println("Введите Отчетво: ");
                            String o = reader();
                            if (typeCheck("string", o)) {
                                continue;
                            }
                            item = String.format("%s %s", item, o);
                            break;
                        }
                        values.add(item);
                        clientReplace.setName(item);
                    }
                    if (Integer.parseInt(column) == MenuReplaceClients.POINTS.ordinal() + 1){
                        columns.add(MenuReplaceClients.POINTS.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceClients.POINTS.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        clientReplace.setPoints(Integer.parseInt(item));
                    }
                    break;
                case EMPLOYEES:
                    if (Integer.parseInt(column) == MenuReplaceEmployees.FULL_NAME.ordinal() + 1){
                        columns.add(MenuReplaceEmployees.FULL_NAME.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceEmployees.FULL_NAME.toString()));
                        while (true) {
                            System.out.println("Введите Фамилию: ");
                            String f = reader();
                            if (typeCheck("string", f)) {
                                continue;
                            }
                            item = f;
                            break;
                        }
                        while (true) {
                            System.out.println("Введите Имя: ");
                            String i = reader();
                            if (typeCheck("string", i)) {
                                continue;
                            }
                            item = String.format("%s %s", item, i);
                            break;
                        }
                        while (true) {
                            System.out.println("Введите Отчетво: ");
                            String o = reader();
                            if (typeCheck("string", o)) {
                                continue;
                            }
                            item = String.format("%s %s", item, o);
                            break;
                        }
                        values.add(item);
                        employeeReplace.setName(item);
                    }
                    if (Integer.parseInt(column) == MenuReplaceEmployees.POSITION.ordinal() + 1){
                        columns.add(MenuReplaceEmployees.POSITION.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceEmployees.POSITION.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        employeeReplace.setPosition(item);
                    }
                    if (Integer.parseInt(column) == MenuReplaceEmployees.SALARY.ordinal() + 1){
                        columns.add(MenuReplaceEmployees.SALARY.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceEmployees.SALARY.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        employeeReplace.setSalary(Integer.parseInt(item));
                    }
                    break;
                case PRODUCTS:
                    if (Integer.parseInt(column) == MenuReplaceProducts.NAME.ordinal() + 1){
                        columns.add(MenuReplaceProducts.NAME.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceProducts.NAME.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        productReplace.setName(item);
                    }
                    if (Integer.parseInt(column) == MenuReplaceProducts.BARCODE.ordinal() + 1){
                        columns.add(MenuReplaceProducts.BARCODE.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceProducts.BARCODE.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("string", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        productReplace.setBarcode(item);
                    }
                    if (Integer.parseInt(column) == MenuReplaceProducts.PRICE.ordinal() + 1){
                        columns.add(MenuReplaceProducts.PRICE.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceProducts.PRICE.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("double", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        productReplace.setPrice(Double.parseDouble(item));
                    }
                    if (Integer.parseInt(column) == MenuReplaceProducts.DISCOUNT.ordinal() + 1){
                        columns.add(MenuReplaceProducts.DISCOUNT.toColumn());
                        System.out.println(String.format("На какое значение хотите поменять %s?:",
                                MenuReplaceProducts.DISCOUNT.toString()));
                        while (true) {
                            item = reader();
                            if (typeCheck("int", item)) {
                                continue;
                            }
                            break;
                        }
                        values.add(item);
                        productReplace.setDiscount(Integer.parseInt(item));
                    }
                    break;
            }
        }

        while (true) {
            switch (table) {
                case CLIENTS:
                    System.out.println("Уверены что хотите поменять с:");
                    System.out.println(clientFind.toString());
                    System.out.println("на:");
                    System.out.println(clientReplace.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
                case EMPLOYEES:
                    System.out.println("Уверены что хотите поменять с:");
                    System.out.println(employeeFind.toString());
                    System.out.println("на:");
                    System.out.println(employeeReplace.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
                case PRODUCTS:
                    System.out.println("Уверены что хотите поменять с:");
                    System.out.println(productFind.toString());
                    System.out.println("на:");
                    System.out.println(productReplace.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
            }
            item = reader().toUpperCase();
            switch (item) {
                case "Y":
                case "Д":
                    dbHandler.replace(table, id, columns, values);
                    break;
                case "N":
                case "Н":
                    showMenuContent(table);
                    break;
                default:
                    failed();
                    continue;
            }
            break;
        }
    }

    private void showMenuDelete (Tables table) {
        System.out.println("Введите id");
        String item;
        while (true) {
            item = reader();
            if (typeCheck("int", item)) {
                continue;
            }
            break;
        }
        int id = Integer.parseInt(item);
        ArrayList<?> obj = dbHandler.find("id", Integer.toString(id), table);
        for (Object o : obj) {
            if (o.equals("Объект не найден")){
                System.out.println();
                showMenuContent(table);
                break;
            }
        }
        while (true) {
            switch (table) {
                case CLIENTS:
                    System.out.println("Вы уверены что хотите удалить?:");
                    Client clientFind = (Client) obj.get(0);
                    System.out.println(clientFind.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
                case EMPLOYEES:
                    System.out.println("Вы уверены что хотите удалить?:");
                    Employee employeeFind = (Employee) obj.get(0);
                    System.out.println(employeeFind.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
                case PRODUCTS:
                    System.out.println("Вы уверены что хотите удалить?:");
                    Product productFind = (Product) obj.get(0);
                    System.out.println(productFind.toString());
                    System.out.println("(Y/N Д/Н)");
                    break;
            }
            item = reader().toUpperCase();
            switch (item) {
                case "Y":
                case "Д":
                    dbHandler.delete(table, id);
                    break;
                case "N":
                case "Н":
                    showMenuContent(table);
                    break;
                default:
                    failed();
                    continue;
            }
            break;
        }
    }

}