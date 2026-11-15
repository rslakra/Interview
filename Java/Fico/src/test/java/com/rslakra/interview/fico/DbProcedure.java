package com.rslakra.interview.fico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/26/24 12:25 PM
 */
public class DbProcedure {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbProcedure.class);

    //
//    Write a function that calls a DB stored procedure.Returns:  List<Record>
//    If an error occurs the function should log error and return an empty List.

    static class Record {
    }

    public List<Record> findRecord() {
        List<Record> results = new ArrayList<>();
//        Connection conn = null;
//        Mapper
//        try {
//            ResultSet rs = conn.prepareCall("call FETECH_RECORD()");
//            while (rs.next()) {
//                Record record = new Record();
//                record.setId( rs.getLong("id"));
//                results.add(record);
//            }
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
////            throw new RuntimeException(e);
//        }

        return results;
    }

    /**
     * Order
     *        Columns:
     *               order_id        UNIQUEIDENTIFIER NOT NULL
     *               user_id                 UNIQUEIDENTIFIER NOT NULL
     *               email_address     VARCHAR(50) NOT NULL
     *               order_date           DATETIME NOT NULL
     *               order_total           MONEY NOT NULL
     *               order_state          INT NULL
     *               etc……
     *  
     * OrderLineItem
     *        Columns:
     *               order_id        UNIQUEIDENTIFIER NOT NULL
     *               lineitem_id           BIGINT
     *               quantity                INT NOT NULL
     *               unit_price             MONEY NOT NULL
     *               lineitem_total        MONEY NOT NULL
     *               product_id            VARCHAR(64) NOT NULL
     *               purchase_id         BIGINT NOT NULL
     *               item_state            INT NULL
     *               etc…..
     *  
     * Select order counts by day for previous month
     */

}
