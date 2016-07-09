package top.wuhaojie.servlet;

import top.wuhaojie.entities.MessageEntity;
import top.wuhaojie.socket.impl.SendThread;
import top.wuhaojie.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 21:31
 * Version: 1.0
 */
@WebServlet(name = "SendControlServlet")
public class SendControlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServletInputStream inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStr.append(line);
            }
            MessageEntity messageEntity = GsonUtils.fromJson(jsonStr.toString(), MessageEntity.class);
//           NodeDataOpt.sendMessage(messageEntity);
            SendThread.sendMessage(messageEntity);
            System.out.println("收到控制信息" + messageEntity.getText());
        } catch (IOException e) {
            response.setHeader("response", "error");
        }
        response.setHeader("response", "ok");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
