package top.wuhaojie.servlet;

import top.wuhaojie.dao.DataDao;
import top.wuhaojie.entities.InfoItem;
import top.wuhaojie.entities.ResponseEntity;
import top.wuhaojie.utils.LogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/7 13:50
 * Version: 1.0
 */
@WebServlet(name = "MostValueServlet")
public class MostValueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");


        ResponseEntity responseEntity = new ResponseEntity();
        List<InfoItem> infoItems = null;
        try {
            infoItems = DataDao.getInstance().queryAllInfo();
        } catch (SQLException e) {
            LogUtils.e("数据库查询错误");
            responseEntity.setError(true);
            responseEntity.setErrorType(1);
        }
        int max = 0, min = 0;
        for (InfoItem item : infoItems) {
            switch (type) {
                case "temperature":
                    max = Integer.valueOf(item.getTemperature()) > max ? Integer.valueOf(item.getTemperature()) : max;
                    min = Integer.valueOf(item.getTemperature()) < min ? Integer.valueOf(item.getTemperature()) : min;
                    break;
                case "humidity":
                    max = Integer.valueOf(item.getHumidity()) > max ? Integer.valueOf(item.getHumidity()) : max;
                    min = Integer.valueOf(item.getHumidity()) < min ? Integer.valueOf(item.getHumidity()) : min;
                    break;
                default:
                    break;
            }
        }

        PrintWriter writer = response.getWriter();
        writer.println(max);
        writer.println(min);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
