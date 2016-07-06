package top.wuhaojie.listener;

import top.wuhaojie.socket.impl.NodeSocketThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 14:21
 * Version: 1.0
 */
public class SocketLoader implements ServletContextListener {

    /**
     * 与树莓派节点交互的Socket线程
     */
    private NodeSocketThread mNodeSocketThread;

    /**
     * Context 初始化
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (mNodeSocketThread == null) {
            mNodeSocketThread = new NodeSocketThread();
            mNodeSocketThread.start();
        }

    }

    /**
     * 销毁服务
     *
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (mNodeSocketThread != null) {
            mNodeSocketThread.closeServer();
            mNodeSocketThread.interrupt();
        }
    }
}
