import java.util.*;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动\
        View.Start_view();
        Scanner scanner = new Scanner(System.in);
        int module = 0;
        Player player = null;
        int moudle_1 = 0;
        while (module != 3) {
            View.Module_view();
            module = scanner.nextInt();
            switch (module) {
                case 1://登陆
                    moudle_1 = 1;
                    while (moudle_1 != 8) {
                        if (player == null) {
                            System.out.println("输入账户ID");
                            String ID = scanner.next();
                            player = Module.login(ID);
                        } else {
                            player = Module.login(player.getID());
                        }
                        if (player == null) {
                            System.out.println("没有查询到您的信息,请您再次输入进行功能选择");
                            System.out.println("请您选择功能");
                            moudle_1 = 8;
                            View.Module_view();
                            module = scanner.nextInt();
                        } else {
                            System.out.println("请您选择功能");
                            System.out.println("1 进行与机器人--wang 进行扑克对战");
                            System.out.println("2 进行查询您的对战记录");
                            System.out.println("3 获取您的获胜率");
                            System.out.println("4 查询其它玩家的信息（通过玩家的用户名）");
                            System.out.println("5 查看某起始到终止时间内对战记录的功能");
                            System.out.println("6 退出登陆");
                            System.out.println("7 注销您的账户");
                            moudle_1 = scanner.nextInt();
                            switch (moudle_1) {
                                case 1:
                                    System.out.println();
                                    Poker poker = new Poker();
                                    System.out.println("初始化的一套扑克为:");
                                    System.out.println(Arrays.toString(poker.getPoker()));
                                    StringBuffer[] pokers = poker.getPoker();
                                    List<StringBuffer> Pokers = new ArrayList<>();
                                    Map<StringBuffer, Integer> map = new HashMap<>();
                                    Map<StringBuffer, Integer> huaseNumber_map = new HashMap<>();
                                    for (Integer i = 0, j = 0, k = 1; i < 52; i++, j++) {
                                        Pokers.add(pokers[i]);
                                        map.put(Pokers.get(i), j);
                                        huaseNumber_map.put(Pokers.get(i), k);
                                        if (j == 12) {//保证排序;
                                            j = -1;
                                            k += 1;//因为上面要加一
                                        }
                                    }
                                    poker.xipai(Pokers);
                                    System.out.println("洗牌完毕，洗后的牌为" + "\n" + Pokers);
                                    Player player1 = player;//player1就是玩家
                                    Player player2 = null;
                                    System.out.println("机器人玩家:robot与您进行对战");
                                    player2 = Player.getrobot();
                                    System.out.println("开始进入发牌阶段！");
                                    player1.setShoupai(poker.getshoupais(Pokers));
                                    player2.setShoupai(poker.getshoupais(Pokers));
                                    System.out.println("玩家一的手牌为:" + Arrays.toString(player1.shoupais));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }//这里是为了达到一种发牌的气氛
                                    System.out.println("玩家二的手牌为:" + Arrays.toString(player2.shoupais));
                                    player1.setMaxNumber(map.get(player1.shoupais[0]), map.get(player1.shoupais[1]));
                                    player2.setMaxNumber(map.get(player2.shoupais[0]), map.get(player2.shoupais[1]));
                                    int num1 = player1.getMaxNumber();
                                    int num3 = player1.getHuaseNumber();
                                    int num2 = player2.getMaxNumber();
                                    int num4 = player2.getHuaseNumber();
                                    player1.setHuaseNumber(huaseNumber_map.get(player1.shoupais[0]), huaseNumber_map.get(player1.shoupais[1]));
                                    player2.setHuaseNumber(huaseNumber_map.get(player2.shoupais[0]), huaseNumber_map.get(player2.shoupais[1]));
                                    View.Process_view();
                                    if (num1 > num2) {
                                        System.out.println("通过比较最大手牌的数字");
                                        player1.Victory();
                                        Record.add_winner_recode(player1, player2);
                                    } else if (num1 < num2) {
                                        System.out.println("通过比较最大手牌的数字");
                                        player2.Victory();
                                        Record.add_winner_recode(player2, player1);
                                    } else if (num3 > num4) {
                                        System.out.println("因为最大数的花色相同我们比较花色得出");
                                        player1.Victory();
                                        Record.add_winner_recode(player1, player2);
                                    } else if (num3 < num4) {
                                        player2.Victory();
                                        Record.add_winner_recode(player2, player2);
                                        System.out.println(player2.getMaxNumber());
                                    } else {
                                        System.out.println("经过比较，本次比赛的结果为平局");
                                        Record.add_pinju_recode(player1, player2);
                                    }
                                    View.Process_view();
                                    System.out.println("对战结束");
                                    System.out.println("请您进行继续选择功能");
                                    System.out.println("请您选择功能");
                                    System.out.println("1 进行与机器人--wang 进行扑克对战");
                                    System.out.println("2 进行查询您的对战记录");
                                    System.out.println("3 获取您的获胜率");
                                    System.out.println("4 查询其它玩家的信息（通过玩家的用户名）");
                                    System.out.println("5 查看某起始到终止时间内对战记录的功能");
                                    System.out.println("6 退出登陆");
                                    System.out.println("7 注销您的账户");
                                    moudle_1 = scanner.nextInt();
                                    break;
                                case 2:
                                    Module.chaxun(player.getID());
                                    break;
                                case 3:
                                    float b = Module.chaxun_huoshenglv(player.getID());
                                    System.out.println("您的获胜率为" + b * 100 + "%");
                                    break;
                                case 4:
                                    System.out.println("输入要查询的用户名");
                                    String name = scanner.next();
                                    Module.chanxun_other_person(name);
                                    break;
                                case 5:
                                    Module.chaxun_by_time(player.getID());
                                    break;
                                case 6:
                                    player = null;//这样就达到退出登陆的效果了
                                    System.out.println("已经退出，请输入数字进行功能选择");
                                    moudle_1 = 8;
                                    break;
                                case 7:
                                    Module.Delete(player.getID());
                                    break;
                            }
                        }
                    }
                    break;
                case 2://注册
                    Module.register();
                    break;
            }
        }
        View.End_viwe();
    }
}
