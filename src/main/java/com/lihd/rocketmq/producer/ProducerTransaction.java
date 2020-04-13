package com.lihd.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: lihd-rocketmq
 * @description: 事务消息
 * @author: li_hd
 * @create: 2020-04-13 10:33
 **/
public class ProducerTransaction {


    public void producerTransaction() throws MQClientException, InterruptedException {

        final TransactionMQProducer producer = new TransactionMQProducer("transaction_producer_group");

        producer.setNamesrvAddr("192.168.160.130:9876");

        TransactionListener transactionListener = new TransactionListenerImpl();

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });


        producer.setExecutorService(executorService);

        producer.setTransactionListener(transactionListener);

        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("topic_example_java", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);

                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);

            } catch (MQClientException | UnsupportedEncodingException | InterruptedException e) {

                e.printStackTrace();

            }
        }

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();


    }


}
