import java.util.List;
import java.util.Map;
public class Poker {
    private final String[] huase=new String[]{"黑桃","红桃","梅花","方片"};
    private final String[] xuhao=new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private final StringBuffer[] Pokers=new StringBuffer[52];
    public StringBuffer[] getPoker(){
        int j=3,k=0;
        for (int i=0;i<52;i++) {
            Pokers[i] = new StringBuffer();
            Pokers[i].append(huase[j]).append(xuhao[k]);
            k++;
            if(k==13){k=0;j--;}
        }
        return Pokers;
    }
    public void xipai(List<StringBuffer> list){
        //这里要进行洗牌 洗牌要具有随机性；
        //如何洗呢？
        for (int i=0;i<100;i++){
            //进行一百次随机互换。
            double a= Math.random()*51;
            int b=(int) a;
            double c=Math.random()*51;
            int d=(int) c;
            var temp=list.get(d);
            list.set(d, list.get(b));
            list.set(b,temp);
        }

    }
    public StringBuffer[] getshoupais(List<StringBuffer> list){
        int a=(int)(Math.random()*52);
        int b=(int)(Math.random()*52);
        //返回
        StringBuffer[] shoupais=new StringBuffer[]{list.get(a),list.get(b)};
        return shoupais;
    }

}
