package com.coffee.assessment.utils;


/**
 * Constant class for coffee
 */
public class CoffeeConstant {


    //File constant
    public static final String PAYMENT_FILE= "/data/payments.json";

    public static final String PRODUCT_FILE= "/data/products.json";

    public static final String ORDER_FILE= "/data/orders.json";

    //Amounts constant
    public static final Double PRICE_NULL = 0.0;


    //Queries constant
    public  static final String FIND_PAYMENT_QUERY = "SELECT p.user AS user, p.amount AS amount FROM Payment p";

    public  static final String FIND_TOTAL_PAID_BY_USER_QUERY = "SELECT SUM(p.amount) FROM Payment p WHERE p.user = :username";

    public static final String FIND_TOTAL_OWES_BY_USER_QUERY = " " +
            "SELECT o.user AS user, o.drink AS drink, o.size AS size, p.prices AS prices " +
            "FROM Order o " +
            "JOIN Product p ON o.drink = p.drink_name";


    //Messages constant
    public static final String ERROR_USER_NOT_FOUND = "User not found";

    public static final String ERROR_UNEXPECTED = "Unexpected error. Please try again later.";
}
