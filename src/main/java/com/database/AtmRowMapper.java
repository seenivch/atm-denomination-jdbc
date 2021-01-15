package com.database;



import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.model.Atm;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Seenivasan Chandrasekaran
 */
@SuppressWarnings("rawtypes")
@Component
public class AtmRowMapper implements RowMapper{

    @Override
    public Atm mapRow(ResultSet resultSet, int row) throws SQLException {
         int hundreds = resultSet.getInt("hundreds");
         int fiveHundreds = resultSet.getInt("five_Hundreds");
         int twoThousands = resultSet.getInt("two_Thousands");
         Atm atm = new Atm(hundreds, fiveHundreds, twoThousands);
         return atm;
    }
}
