package com.taotao.commom.FtpClint;

import java.util.Random;
import java.util.UUID;

/**
 * Created by winsion on 2017/4/18.
 */
public class imageNameCreate {


    /**
     * 图片名生成,时间加随机数
     */
    public static String getImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 图片名生成,UUID
     */
    public static String getImageNameByUUID(){

        return  UUID.randomUUID().toString();
    }

}
