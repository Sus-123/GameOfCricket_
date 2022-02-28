package com.company.repozitory;
import com.company.database.DbConnector;
import java.sql.*;

public class InningRepository {

    //InningTable(Id, BattingTeamId, BowlingTeamId, Overs);
    public static int insertInning (int  BattingTeamId ,int BowlingTeamId, int Overs) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        // String query = "insert into Inning(Match_Id, BattingTeam_Id, BowlingTeam_Id) values(?,?,?)";
        String query =  "INSERT INTO `CrecketMatch`.`InningTable` (BattingTeamId, BowlingTeamId, Overs) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,BattingTeamId);
        preparedStatement.setInt(2,BowlingTeamId);
        preparedStatement.setInt(3,Overs);

        int inningId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                inningId = rs.getInt(1);
            }
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return inningId;

    }

}
