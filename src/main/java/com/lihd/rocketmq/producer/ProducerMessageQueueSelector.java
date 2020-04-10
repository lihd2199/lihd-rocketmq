package com.lihd.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @program: lihd-rocketmq
 * @description: MessageQueueSelector
 * @author: li_hd
 * @create: 2020-04-10 15:34
 **/
public class ProducerMessageQueueSelector {


    public void producerMessageQueueSelector() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        //创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");

        //指定 NameServer 地址
        producer.setNamesrvAddr("192.168.160.130:9876");

        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();

        Message msg = new Message(
                "topic_example_java" /* 消息主题名 */,
                "Tag" /* 消息标签 */,
                (" MessageQueueSelector").getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
        );

        final SendResult send = producer.send(msg, new SelectMessageQueueByHash(), "1234567");

        System.out.println(send);

        producer.shutdown();


    }


}
