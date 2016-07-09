package top.wuhaojie.socket.impl;

import top.wuhaojie.dao.DataDao;
import top.wuhaojie.entities.InfoItem;
import top.wuhaojie.utils.GsonUtils;
import top.wuhaojie.utils.LogUtils;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
//    private static Queue<MessageEntity> mMessageEntities = new ConcurrentLinkedDeque<>();

    public NodeDataOpt(Socket socket) {
        mSocket = socket;
    }


//    public static void sendMessage(MessageEntity entity) {
////        mMessageEntities.add(entity);
//        mMessageEntities.add(entity);
//    }

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

        new SendThread(writer).start();

        while (canRunning) {
            try {
                // 读取一行数据
                String line = reader.readLine();
                InfoItem item = GsonUtils.fromJson(line, InfoItem.class);
                int id = 0;
                if (item != null)
                    id = item.getNodeId();
                if (item != null)
                    System.out.println(item.toString());

                // 存入数据库
                if (item != null) {
                    insert2DataBase(item);
                }

//                // 写入消息
//                if (!mMessageEntities.isEmpty()) {
//                    MessageEntity messageEntity = mMessageEntities.poll();
//                    if (id == messageEntity.getId()) {
//                        writer.write(messageEntity.getText());
//                        writer.newLine();
//                        writer.flush();
//                    }
//                }

            } catch (IOException e) {
                LogUtils.e("读取失败");
                canRunning = false;
            }
        }

    }

    private List<InfoItem> mInfoItems = new LinkedList<>();

    private void insert2DataBase(InfoItem item) {
        mInfoItems.add(item);
        if (mInfoItems.size() >= 5) {
            try {
                DataDao.getInstance().insertNewInfoList(mInfoItems);
            } catch (SQLException e) {
                LogUtils.e("数据库写入最新" + mInfoItems.size() + "条数据有错");
            }
            mInfoItems.clear();
        }
    }
}
