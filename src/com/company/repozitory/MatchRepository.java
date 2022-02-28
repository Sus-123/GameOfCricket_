package com.company.repozitory;
import com.company.database.DbConnector;
import java.sql.*;

public class MatchRepository {


    //Table- MatchTable(Id, Inning1Id, Inning2Id)
    public static void insertMatch(int inning1Id, int inning2Id) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query =  "INSERT INTO `CrecketMatch`.`MatchTable` (`Inning1Id`, `Inning2Id`) VALUES (?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inning1Id);
        preparedStatement.setInt(2,inning2Id);
        preparedStatement.executeUpdate();
        connection.commit();



    }

}
