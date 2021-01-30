import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Player {
    private String ID;
    private String name;
    public StringBuffer[] shoupais=new StringBuffer[2];
    private Integer MaxNumber=0;
    private Integer huaseNumber=0;
   
    public static Player getrobot(){
        Player robot=new Player();
        robot.ID="1";
        robot.name="wang";
        return robot;
    }
    public Integer getHuaseNumber() {
        return huaseNumber;
    }

    public void setHuaseNumber(Integer num1,Integer num2) {
        if(num1>num2)
        {
            this.MaxNumber=num1;
        }else {
            this.MaxNumber=num2;
        }
    }

    public Integer getMaxNumber() {
        return MaxNumber;
    }

    public void setMaxNumber(Integer num1,Integer num2) {
        if(num1>num2)
        {
            this.MaxNumber=num1;
        }else {
            this.MaxNumber=num2;
        }
    }

    public StringBuffer[] getShoupais() {
        return shoupais;
    }

    public void setShoupai(StringBuffer[] shoupais) {
        this.shoupais = shoupais;
    }
    Player(){}
    Scanner scanner=new Scanner(System.in);
    public Player(String ID,String name){
        this.ID=ID;
        this.name=name;
    }

    public String getID() {
        return ID;
    }

    public void setID() {
        System.out.println("请输入您的ID--由数字构成");
        boolean a=false;
        while (!a){
            String id=scanner.nextLine();
            a=id.matches("[0-9]+");
            if(a==false){
                System.out.println("输入的格式有误!请您再次输入");
            }
            if (a==true){
                this.ID=id;
                System.out.println("输入ID完毕！");
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("请您创建玩家的姓名");
        this.name =scanner.nextLine();
        System.out.println("创建成功");
    }
    public void getPokers(StringBuffer[] shoupai){

    }
    public void Victory(){
        System.out.println("获胜玩家ID:"+this.ID+" "+"玩家名称:"+this.name);
        System.out.println("该玩家的手牌为:"+this.shoupais[0]+this.shoupais[1]);
        System.out.println("让我们祝贺该玩家");
    }


    public void SetName(String user_name) {
        this.name=user_name;

    }

    public void SetID(String user_id) {
        this.ID=user_id;
    }
}
