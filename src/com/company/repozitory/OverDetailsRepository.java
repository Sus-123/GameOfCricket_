package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.util.DatabaseUtil;

import java.sql.*;

public class OverDetailsRepository {

    //OverDetailsTable (Id, Inning1Id, Inning2Id)
    public static int insertOverDetails (int inningId, String bowlerName, String teamName) throws SQLException, ClassNotFoundException {

        int bowlerId = DatabaseUtil.getPlayerIdFromDb(bowlerName, teamName);
        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO `CrecketMatch`.`OverDetailsTable` (`InningId`, `BowlerId`) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inningId);
        preparedStatement.setInt(2,bowlerId);

        int overDetailsId  = 0;

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                overDetailsId = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return overDetailsId;

    }


}
