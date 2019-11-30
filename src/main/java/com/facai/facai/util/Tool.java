package com.facai.facai.util;

import com.facai.facai.entity.Order;
import com.facai.facai.util.date.DateStyle;
import com.facai.facai.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 9:59
 */
public class Tool {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //订单编号
    public static String generateOrderNum(){
        int a = (int)(Math.random()*(99999-10000+1))+10000;
        return DateUtils.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS_NO)+a;
    }

    public static void main(String[] args) {
        System.out.println(generateOrderNum());


    }

}
