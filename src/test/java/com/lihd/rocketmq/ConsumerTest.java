package com.lihd.rocketmq;

import com.lihd.rocketmq.consumer.Consumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;

/**
 * @program: lihd-rocketmq
 * @description:
 * @author: li_hd
 * @create: 2020-04-10 11:14
 **/
public class ConsumerTest {

    @Test
    public void test() throws MQClientException, InterruptedException {

        Consumer consumer = new Consumer();
        consumer.customer();

    }


}
