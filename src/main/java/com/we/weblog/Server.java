package com.we.weblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/** 原因在于需要继承UnicastRemoteObject
 * @author tangwei
 * @date 2018/7/11 18:35
 */
public class Server {


    private final static Logger LOG =  LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        LOG.info("logback成功了");

    }
}
