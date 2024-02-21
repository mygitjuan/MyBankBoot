package com.dxc.mypersonalbankapi.util;

import com.dxc.mypersonalbankapi.persistencia.DBConnector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DBConnectorTest {

    @Test
    void connect_isOK() {
        DBConnector dbc = new DBConnector();
        try {
            dbc.connect();
            assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}