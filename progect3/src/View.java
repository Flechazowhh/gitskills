public class View {
    public static void Start_view()throws Exception{
        System.out.println("欢迎进入超凡的扑克游戏系统");
        for(int i=0;i<9;i++){
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
            }
            System.out.print("-");
        }
    }
    public static void Module_view(){
        System.out.println("请您选择功能进行操作");
        System.out.println("1 进行登陆操作");
        System.out.println("2 进行注册账户");
        System.out.println("3 退出程序");
    }
    public static void End_viwe(){
        for(int i=0;i<9;i++){
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
            }
            System.out.print("❀");
        }//使用这里的目的是 使用学过的抓捕异常 第二 美化界面
        System.out.println("\n程序运行结束");
    }
    public static void Process_view(){
        try {
            System.out.println("玩家的牌进行比较");
            Thread.sleep(2300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
