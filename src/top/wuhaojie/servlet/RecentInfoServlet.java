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
import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 20:37
 * Version: 1.0
 */
@WebServlet(name = "RecentInfoServlet")
public class RecentInfoServlet extends HttpServlet {

    private String mCount;
    private String mPeriod;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mCount = request.getParameter("count");
        mPeriod = request.getParameter("period");
        if (mCount == null || mPeriod == null) return;
        int count = Integer.valueOf(mCount);
        int period = Integer.valueOf(mPeriod);


        ResponseEntity responseEntity = new ResponseEntity();
        try {
            List<InfoItem> infoItems = DataDao.getInstance().queryRecentInfo(count, period);
            responseEntity.setInfoItems(infoItems);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
