package top.wuhaojie.socket.impl;

import top.wuhaojie.constant.Constants;
import top.wuhaojie.socket.ISocketThread;
import top.wuhaojie.utils.LogUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * 与树莓派节点交互的Socket线程
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 14:31
 * Version: 1.0
 */
public class NodeSocketThread extends Thread implements ISocketThread {


    /**
     * 服务端Socket
     */
    private ServerSocket mServerSocket;
    /**
     * 是否能够运行标志
     */
    private boolean canRunning = true;
    /**
     * 数据处理线程链表
     */
    private List<Thread> mThreads = new LinkedList<>();

    @Override
    public void run() {
        super.run();

        try {
            // 开启端口监听
            mServerSocket = new ServerSocket(Constants.DEFAULT_NODE_PORT);
            LogUtils.d("服务启动, 开始监听" + mServerSocket.getLocalPort() + "端口");
            while (canRunning) {
                // 阻塞式监听新访客
                Socket accept = mServerSocket.accept();
                LogUtils.d("连接上新节点: " + accept.getInetAddress().getHostName() + "@" + accept.getInetAddress().getHostAddress());
                // 启动一个新线程处理事件
                Thread thread = new Thread(new NodeDataOpt(accept));
                // 开启线程
                thread.start();
                // 添加到链表便于结束线程
                mThreads.add(thread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void closeServer() {
        canRunning = false;
        try {
            mServerSocket.close();
        } catch (IOException e) {
            mServerSocket = null;
        }
        for (Thread thread : mThreads) {
            thread.interrupt();
        }
    }
}
