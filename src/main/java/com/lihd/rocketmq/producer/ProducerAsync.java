package com.lihd.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @program: lihd-rocketmq
 * @description:
 * @author: li_hd
 * @create: 2020-04-10 15:21
 **/
public class ProducerAsync {


    public void productAsync() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {

        //创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");

        //指定 NameServer 地址
        producer.setNamesrvAddr("192.168.160.130:9876");

        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();

        Message msg = new Message(
                "topic_example_java" /* 消息主题名 */,
                "Tag" /* 消息标签 */,
                (" 异步消息").getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
        );

        producer.send(msg, new SendCallback() {

            public void onSuccess(SendResult sendResult) {
                System.out.println("on success ");
            }

            public void onException(Throwable throwable) {
                System.out.println("on error ");
                throwable.printStackTrace();
            }
        });


        Thread.sleep(3000);

        producer.shutdown();


    }





}
