package top.wuhaojie.socket.impl;

import top.wuhaojie.utils.LogUtils;

import java.io.*;
import java.net.Socket;

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

    public NodeDataOpt(Socket socket) {
        mSocket = socket;
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
                System.out.println(reader.readLine());
            } catch (IOException e) {
                System.out.println("读取失败");
                canRunning = false;
            }
        }

    }
}
