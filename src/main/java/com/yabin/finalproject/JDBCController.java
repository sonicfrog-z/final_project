package com.yabin.finalproject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";


    @CrossOrigin
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String printCryptTest() {
        AESUtils aesUtils = new AESUtils();

        String encryptedStr = aesUtils.encrypt("Hello World!", KEYFILEPATH);
        return ("Decrypt = " + aesUtils.decrypt(encryptedStr, KEYFILEPATH));
    }

    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/printAllProducts", method = RequestMethod.GET)
    public String printAllUsers() {
        JdbcTemplate jdbcTemplate= JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from product;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("product_id")).append(", ")
                    .append(sqlRowSet.getString("product_name"))
                    .append("\n");
        }
        return ("SELECT * from product:\n" + resultStr);
    }

    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/printAllRegions", method = RequestMethod.GET)
    public String printAllRegions() {
        JdbcTemplate jdbcTemplate= JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from region;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("region_id")).append(", ")
                    .append(sqlRowSet.getString("region_name"))
                    .append("\n");
        }
        return ("SELECT * from region:\n" + resultStr);
    }

    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/printAllSales", method = RequestMethod.GET)
    public String printAllSales() {
        JdbcTemplate jdbcTemplate= JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from sales_totals s join region r on s.region_id = " +
                "r.region_id join product p on p.product_id = s.product_id order by year, month, s.region_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("year")).append(", ")
                    .append(String.format("%-5s", sqlRowSet.getString("month"))).append(", ")
                    .append(String.format("%-15s", sqlRowSet.getString("region_name"))).append(", ")
                    .append(String.format("%-15s", sqlRowSet.getString("product_name"))).append(", ")
                    .append(sqlRowSet.getString("sales")).append(", ")
                    .append("\n");
        }
        return ("All sales:\nYear  Month  Region           Product Name     Sales\n" + resultStr);
    }

    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/addSales", method = RequestMethod.POST)
    public String addUser(@RequestBody AddSalesData addSalesData) {
        JdbcTemplate jdbcTemplate= JDBCConnector.getJdbcTemplate();
        String queryStr = "INSERT INTO sales_totals (product_id, region_id, year, month, sales) " +
                "VALUES (" +
                "'" + addSalesData.getProduct_id() + "'," +
                "'" + addSalesData.getRegion_id() + "'," +
                "'" + addSalesData.getYear() + "'," +
                "'" + addSalesData.getMonth() + "'," +
                "'" + addSalesData.getSales() + "'" +
                ");";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }
}
