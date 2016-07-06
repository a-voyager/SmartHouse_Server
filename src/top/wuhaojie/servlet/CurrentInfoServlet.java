package top.wuhaojie.servlet;

import top.wuhaojie.dao.DataDao;
import top.wuhaojie.entities.InfoItem;
import top.wuhaojie.entities.ResponseEntity;
import top.wuhaojie.utils.GsonUtils;
import top.wuhaojie.utils.LogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 17:35
 * Version: 1.0
 */
@WebServlet(name = "CurrentInfoServlet")
public class CurrentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResponseEntity responseEntity = new ResponseEntity();
        try {
            InfoItem infoItem = DataDao.getInstance().queryLastInfo();
            responseEntity.addInfoItem(infoItem);
        } catch (SQLException e) {
            LogUtils.e("数据库查询错误");
            responseEntity.setError(true);
            responseEntity.setErrorType(1);
        }
        String jsonStr = GsonUtils.toGson(responseEntity);
        PrintWriter writer = response.getWriter();
        writer.print(jsonStr);
        writer.flush();
        writer.close();

    }
}
