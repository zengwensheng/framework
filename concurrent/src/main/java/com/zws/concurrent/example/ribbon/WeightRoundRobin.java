package com.zws.concurrent.example.ribbon;

import com.zws.common.MathUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/23
 *
 * 负载均衡轮渡权重算法
 *
 */
@Slf4j
public class WeightRoundRobin {


    /**
     * 服务存储容器
     */
    private List<Server>  serverList;

    /**
     * 当前服务的下标
     */
    private Integer  currentIndex=-1;
    /**
     *  当前权重的指标
     */
    private Integer currentWeight=0;
    /**
     * 最大权重值
     */
    private Integer maxWeight;

    /**
     *  所有权重值的最大公约数
     */
    private Integer gcdWeight;



    private Integer serverCount;

    private WeightRoundRobin(){
        init();
    }

    protected  void init(){
        serverList = new ArrayList<>();
        serverList.add(new Server("192.168.1.1",1));
        serverList.add(new Server("192.168.1.2",3));
        serverList.add(new Server("192.168.1.3",6));
        serverCount=serverList.size();
        maxWeight = getMaxWeight();
        gcdWeight =getGcdWeight();
    }

    /**
     * 获取服务中的最大权重值
     * @return
     */
    private Integer getMaxWeight(){
        Integer weight = 0;
        for(Server server:serverList){
            if(server.getWeight()>weight){
                weight = server.getWeight();
            }
        }
        return weight;
    }

    /**
     * 获取服务中所有权重值的最大公约数
     */
    private Integer getGcdWeight(){
        Integer gcdWeight=0;
        if(serverList!=null&&serverList.size()>0){
            gcdWeight = serverList.get(0).getWeight();
        }

        for(int i=1;i<serverList.size();i++){

            Integer weight = serverList.get(i).getWeight();
            gcdWeight =  MathUtil.getGcdByDivide(gcdWeight,weight);

        }
        return gcdWeight;
    }

    /**
     *  根据轮渡权重算法获取服务ip
     * @return
     */
    public Server getServer(){
        while(true){
            currentIndex = (currentIndex+1) % serverCount;
            if(currentIndex==0){
                currentWeight = currentWeight-gcdWeight;
                if(currentWeight<=0){
                    currentWeight = maxWeight;
                    if(currentWeight==0){
                        return null;
                    }
                }
            }
            if(serverList.get(currentIndex).getWeight()>=currentWeight){
                return  serverList.get(currentIndex);
             }

        }
    }


    public static void main(String[] args) {
        WeightRoundRobin weightRoundRobin = new WeightRoundRobin();
        for(int i=0;i<20;i++){
            Server server = weightRoundRobin.getServer();
            log.info("ip:{},weight:{}",server.getIp(),server.getWeight());
        }
    }


}

@Data
class Server{
    private String ip;
    private Integer weight;

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }
}
