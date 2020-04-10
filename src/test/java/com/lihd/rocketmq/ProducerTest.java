package com.lihd.rocketmq;

import com.lihd.rocketmq.producer.ProducerAsync;
import com.lihd.rocketmq.producer.ProducerMessageQueueSelector;
import com.lihd.rocketmq.producer.ProducerSync;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @program: lihd-rocketmq
 * @description:
 * @author: li_hd
 * @create: 2020-04-10 10:17
 **/
public class ProducerTest {


    @Test
    public void testSync() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {

        ProducerSync producer = new ProducerSync();

        producer.productSync();

    }


    @Test
    public void testAsync() throws InterruptedException, RemotingException, MQClientException, UnsupportedEncodingException {

        ProducerAsync producerAsync = new ProducerAsync();

        producerAsync.productAsync();

    }

    @Test
    public void testMessageQueueSelector() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {

        ProducerMessageQueueSelector producerMessageQueueSelector = new ProducerMessageQueueSelector();

        producerMessageQueueSelector.producerMessageQueueSelector();

    }



}
