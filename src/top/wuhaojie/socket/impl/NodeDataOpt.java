package top.wuhaojie.socket.impl;

import top.wuhaojie.entities.MessageEntity;
import top.wuhaojie.utils.LogUtils;

import java.io.*;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 14:46
 * Version: 1.0
 */
public class NodeDataOpt implements Runnable {

    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private boolean canRunning = true;
    private static Queue<MessageEntity> mMessageEntities = new LinkedBlockingDeque<>();

    public NodeDataOpt(Socket socket) {
        mSocket = socket;
    }


    public static void sendMessage(MessageEntity entity) {
        mMessageEntities.add(entity);
    }

    @Override
    public void run() {
        try {
            mInputStream = mSocket.getInputStream();
            mOutputStream = mSocket.getOutputStream();
        } catch (IOException e) {
            LogUtils.e("连接失败");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mOutputStream));
        LogUtils.d("连接成功");

        while (canRunning) {
            try {
                // 读取一行数据
                String line = reader.readLine();
                String[] split = line.split("#");
                int id = Integer.parseInt(split[0]);
                String message = split[1];
                System.out.println(message);

                // 写入消息
                if (!mMessageEntities.isEmpty()) {
                    MessageEntity messageEntity = mMessageEntities.poll();
                    if (id == messageEntity.getId()) {
                        writer.write(messageEntity.getText());
                        writer.newLine();
                        writer.flush();
                    }
                }

            } catch (IOException e) {
                LogUtils.e("读取失败");
                canRunning = false;
            }
        }

    }
}
