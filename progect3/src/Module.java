import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Module {
    //用户的登陆 根据ID登陆 进行数据比对
    //用户的注册  新添加记录。
    public static Player login(String ID)throws Exception{
        Connection connection=DButil.set_comment();
        String sql="SELECT * FROM user where user_id=?";//这里的SELECT选择的是* 否则如果只选一个就无法获得其它数据；
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,ID);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
        if(resultSet.next()){//查到了数据 就将数据库的数据返回给Player类
            Player player=new Player();
            player.SetID(resultSet.getString("user_id"));
            player.SetName(resultSet.getString("user_name"));
            System.out.println("欢迎您的到来");
            return player;
        }
        else{
            System.out.println("该ID已经不存在于数据库");
            return null;
        }
    }
    public static  void register()throws  Exception{
        Connection connection=DButil.set_comment();
        Player player=new Player();
        System.out.println("请输入您的注册ID,用于登录您的账户");
        player.setID();

        //检测是否已经被注册了2
        String temp_sql="SELECT * FROM user where user_id=?";
        PreparedStatement temp=connection.prepareStatement(temp_sql);
        temp.setString(1,player.getID());
        temp.execute();
        ResultSet temp_resultSet=temp.getResultSet();
        while (temp_resultSet.next()){
            System.out.println("该ID已经被注册 请输入其它的ID");
            player.setID();
        }
        System.out.println("请输入您的注册名称");
        player.setName();
        String sql="INSERT INTO user(user_name,user_id,register_time) VALUES(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,player.getName());
        preparedStatement.setString(2,player.getID());
        // preparedStatement.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
        java.util.Date d = new java.util.Date();
        preparedStatement.setTimestamp(3, new Timestamp(d.getTime()));
        preparedStatement.execute();
        System.out.println("注册完成 可以登陆了");
    }
    public static void Delete(String ID)throws Exception{
        Connection connection=DButil.set_comment();
        String sql="DELETE FROM user WHERE user_id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,ID);
        preparedStatement.execute();
        System.out.println("删除成功");
    }
    public static void chaxun(String ID)throws Exception{
        Connection connection=DButil.set_comment();
        String sql="SELECT * FROM record where user1_id=? or user2_id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,ID);
        preparedStatement.setString(2,ID);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("用户 1:"+resultSet.getString("user1_name")+" " +
                    "用户 2:" +resultSet.getString("user2_name")+" " +"用户一ID:"+resultSet.getString("user1_id")+
                    "" +"用户二ID:"+resultSet.getString("user2_id")+ " " +"获胜者名称:"+resultSet.getString("winner_name")+
                    " " +"获胜者ID:"+resultSet.getString("winner_id")+" "+"获胜者手牌:"+resultSet.getString("winner_shoupai")+" "+
                    "失败者手牌:" +resultSet.getString("loser_shoupai")+" "+"对战时间:"+resultSet.getString("date"));
        }
    }
    public static float chaxun_huoshenglv(String ID)throws Exception{
        Connection connection=DButil.set_comment();
        //计算所有记录数
        String sql="SELECT count(*) FROM record where user1_id=? or user2_id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,ID);
        preparedStatement.setString(2,ID);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
        float count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        //计算胜者记录数
        String sql_1="SELECT  count(*) FROM record where winner_id=?";
        PreparedStatement preparedStatement1=connection.prepareStatement(sql_1);
        preparedStatement1.setString(1,ID);
        preparedStatement1.execute();
        ResultSet resultSet1= preparedStatement1.getResultSet();
        float count_1=0;
        while (resultSet1.next()){
            count_1=resultSet1.getInt(1);
        }
        return count_1/count;
    }
    //登陆成功以后有查询功能 1查询自己的功能 2根据用户名搜索其他用户信息
    public static void chanxun_other_person(String name)throws Exception{
        Connection connection=DButil.set_comment();
        String sql="SELECT * FROM user where user_name=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
        while (resultSet.next()){
            System.out.println("用户名称:"+name+" 用户ID:"+resultSet.getString("user_id"));
        }
    }
    //添加可根据输入查看某起始到终止时间内对战记录的功能
    public static void chaxun_by_time(String ID)throws Exception//对该用户的记录查询
    {
        Connection connection=DButil.set_comment();
        String sql="SELECT * FROM record where (user1_id=? or user2_id=?) and date > ? and date < ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,ID);
        preparedStatement.setString(2,ID);
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入时间段 时间段的格式为yyyy-MM-dd hh:mm:ss");
        //开始时间
        String starttime= scanner.nextLine();
        String endtime= scanner.nextLine();
        while (Module.date_zhuanhuan(starttime)==0&&Module.date_zhuanhuan(endtime)==0){
            starttime=scanner.nextLine();
        }
        Timestamp starttime_0=new Timestamp(Module.date_zhuanhuan(starttime));
        Timestamp  endtime_0=new Timestamp(Module.date_zhuanhuan(endtime));
        preparedStatement.setTimestamp(3,starttime_0);
        preparedStatement.setTimestamp(4,endtime_0);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("用户 1:"+resultSet.getString("user1_name")+" " +
                    "用户 2:" +resultSet.getString("user2_name")+" " +"用户一ID:"+resultSet.getString("user1_id")+
                    "" +"用户二ID:"+resultSet.getString("user2_id")+ " " +"获胜者名称:"+resultSet.getString("winner_name")+
                    " " +"获胜者ID:"+resultSet.getString("winner_id")+" "+"获胜者手牌:"+resultSet.getString("winner_shoupai")+" "+
                    "失败者手牌:" +resultSet.getString("loser_shoupai")+" "+"对战时间:"+resultSet.getString("date"));
        }
    }
    //时间 把字符串型的输入数据转换为long 型 注意 Timetable 用到的就说
    public static long  date_zhuanhuan(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return format.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


}

