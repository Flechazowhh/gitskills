import java.sql.*;

public class Record {
    private String user_name;
    private String user_id;
    private String winner_name;
    private String winner_id;
    public static void add_winner_recode(Player player,Player loser)throws Exception{
        Connection connection=DButil.set_comment();
        String sql=("INSERT INTO record(user1_name,user1_id,winner_id,date,winner_name,winner_shoupai,loser_shoupai,user2_name,user2_id) VALUES(?,?,?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement =connection.prepareStatement(sql.toString());
        preparedStatement.setString(1,player.getName());
        preparedStatement.setString(2, player.getID());
        preparedStatement.setString(3,player.getID());
        java.util.Date d = new java.util.Date();
        preparedStatement.setTimestamp(4, new Timestamp(d.getTime()));
        preparedStatement.setString(5, player.getName());
        preparedStatement.setString(6,player.shoupais[0].toString()+"与"+player.shoupais[1].toString());
        preparedStatement.setString(7, loser.shoupais[0].toString()+"与"+loser.shoupais[1].toString());
        preparedStatement.setString(8,loser.getName());
        preparedStatement.setString(9,loser.getID());
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
    }
    public static void add_pinju_recode(Player player,Player loser)throws Exception{
        Connection connection=DButil.set_comment();
        String sql=("INSERT INTO record(user1_name,user1_id,winner_id,date,winner_name,winner_shoupai,loser_shoupai,user2_name,user2_id) VALUES(?,?,?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement =connection.prepareStatement(sql.toString());
        preparedStatement.setString(1,player.getName());
        preparedStatement.setString(2,player.getID());
        preparedStatement.setString(3,"无");
        java.util.Date d = new java.util.Date();
        preparedStatement.setTimestamp(4, new Timestamp(d.getTime()));
        preparedStatement.setString(5, "无");
        preparedStatement.setString(6,"无");
        preparedStatement.setString(7, "无");
        preparedStatement.setString(8,loser.getName());
        preparedStatement.setString(9,loser.getID());
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(String winner_id) {
        this.winner_id = winner_id;
    }
}


