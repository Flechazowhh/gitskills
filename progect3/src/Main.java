import java.util.*;
public class Main {
    public static void main(String[] args) {

        Poker poker=new Poker();
        System.out.println("创建一副扑克:");
        System.out.println(Arrays.toString(poker.getPoker()));
        StringBuffer[] pokers=poker.getPoker();
        List<StringBuffer> Pokers=new ArrayList<>();
        Map<StringBuffer,Integer> map=new HashMap<>();
        Map<StringBuffer,Integer> huaseNumber_map=new HashMap<>();
        for (Integer i=0,j=0,k=1;i<52;i++,j++)
        {
            Pokers.add(pokers[i]);
            map.put(Pokers.get(i),j);
            huaseNumber_map.put(Pokers.get(i),k);
            if(j==12){
                j=-1;k+=1;
            }
        }
        poker.xipai(Pokers);
        System.out.println("洗牌完毕，洗后的牌为"+"\n"+Pokers);
        Player player1=new Player();
        Player player2=new Player();
        System.out.println("请输入第一位玩家的信息");
        player1.setID();
        player1.setName();
        System.out.println("请输入第二位玩家的信息");
        player2.setID();
        player2.setName();
        System.out.println("开始发牌！");
        //发牌
        player1.setShoupai(poker.getshoupais(Pokers));
        player2.setShoupai(poker.getshoupais(Pokers));
        System.out.println(Arrays.toString(player1.shoupais));
        System.out.println(Arrays.toString(player2.shoupais));
        player1.setMaxNumber(map.get(player1.shoupais[0]),map.get(player1.shoupais[1]));
        player2.setMaxNumber(map.get(player2.shoupais[0]),map.get(player2.shoupais[1]));
        int num1=player1.getMaxNumber();
        int num2=player2.getMaxNumber();
        int num3=player2.getHuaseNumber();
        int num4=player2.getHuaseNumber();
        player1.setHuaseNumber(huaseNumber_map.get(player1.shoupais[0]),huaseNumber_map.get(player1.shoupais[1]));
        player2.setHuaseNumber(huaseNumber_map.get(player2.shoupais[0]),huaseNumber_map.get(player2.shoupais[1]));
        if(num1> num2){
            System.out.println("通过比较");
            player1.Victory();
        }
        else if (num1<num2){
            System.out.println("通过比较");
            player2.Victory();
        }else if(num3>num4){
            System.out.println("通过比较");
            player1.Victory();
        }else  if(num3<num4){
            player2.Victory();
            System.out.println(player2.getMaxNumber());
        }else {
            System.out.println("经过比较，本次比赛的结果为平局");
        }
    }
}
//466bd127ce8bffc622188db82bb18348