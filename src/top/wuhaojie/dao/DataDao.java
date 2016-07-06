package top.wuhaojie.dao;


import top.wuhaojie.constant.Constants;
import top.wuhaojie.entities.InfoItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/4/20 7:30
 * Version: 1.0
 */
public class DataDao {

    private Connection connection;

    private DataDao() {
        Properties properties = new Properties(); // 读取配置文件
        try {
            properties.load(new FileInputStream(new File(Constants.CONFIG_FILE_PATH)));
            System.out.println(properties.getProperty("SQLDriver"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            Class.forName(properties.getProperty("SQLDriver"));
        } catch (ClassNotFoundException e) {
            System.out.println("MySql load failed.");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("SQLAddr"),
                    properties.getProperty("SQLUserName"),
                    properties.getProperty("SQLUserPwd"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private volatile static DataDao dao;

    public static DataDao getInstance() {
        if (dao == null) {
            synchronized (DataDao.class) {
                if (dao == null) {
                    dao = new DataDao();
                }
            }
        }
        return dao;
    }


    private void closeStatement(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                statement = null;
            }
    }

    /**
     * 释放资源
     */
    private void dispose() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
            dao = null;
        }
    }


    public void insertNewInfoList(List<InfoItem> itemList) throws SQLException {

        long lastTime = System.currentTimeMillis();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `smarthouse`.`info` " +
                "(`id`, `nodeid`, `temperature`, `humidity`, `wind_speed`, `wind_direction`, `curtain_state`, `is_safe`, `smoke`, `ultrasonic_wave`, `time_stamp`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        int size = itemList.size();
        int i = 0;
        for (InfoItem p : itemList) {

            preparedStatement.setInt(1, p.getId());
            preparedStatement.setInt(2, p.getNodeId());
            preparedStatement.setString(3, p.getTemperature());
            preparedStatement.setString(4, p.getHumidity());
            preparedStatement.setString(5, p.getWindSpeed());
            preparedStatement.setString(6, p.getWindDirection());
            preparedStatement.setString(7, p.getCurtainState());
            preparedStatement.setString(8, p.getIsSafe());
            preparedStatement.setString(9, p.getSmoke());
            preparedStatement.setString(10, p.getUltrasonicWave());
            preparedStatement.setString(11, p.getTimeStamp());

            preparedStatement.execute();

            final int finalI = i;
            listeners.forEach((l) -> l.onProgress(finalI, size));
            i++;

        }
        closeStatement(preparedStatement);

        long deltaTime = System.currentTimeMillis() - lastTime;
        System.out.println("deltaTime" + deltaTime);
        listeners.forEach((l) -> l.onEventCompleted(deltaTime, size));
    }


    public void deleteAllInfo() throws SQLException {
        long lastTime = System.currentTimeMillis();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM smarthouse.info;");
        closeStatement(statement);
        listeners.forEach((l) -> l.onEventCompleted(System.currentTimeMillis() - lastTime, 0));
    }

    public List<InfoItem> queryAllInfo() throws SQLException {
        long lastTime = System.currentTimeMillis();
        List<InfoItem> itemList = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM smarthouse.info;");
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            int nodeId = resultSet.getInt("nodeid");
            String temperature = resultSet.getString("temperature");
            String humidity = resultSet.getString("humidity");
            String windSpeed = resultSet.getString("wind_speed");
            String windDirection = resultSet.getString("wind_direction");
            String curtainState = resultSet.getString("curtain_state");
            String isSafe = resultSet.getString("is_safe");
            String smoke = resultSet.getString("smoke");
            String ultrasonicWave = resultSet.getString("ultrasonic_wave");
            String timeStamp = resultSet.getString("time_stamp");

            itemList.add(new InfoItem(id, nodeId, temperature, humidity, windSpeed, windDirection, curtainState, isSafe, smoke, ultrasonicWave, timeStamp));
        }
        closeStatement(statement);
        listeners.forEach((l) -> l.onEventCompleted(System.currentTimeMillis() - lastTime, itemList.size()));
        return itemList;
    }


    public static void main(String[] args) {
        try {
            DataDao.getInstance().setOnStatusChangedListener(new OnStatusChangedListener() {
                @Override
                public void onEventCompleted(long deltaTime, long eventMount) {
                    System.out.println("耗时: " + deltaTime);
                }

                @Override
                public void onProgress(long curr, long size) {

                }
            });
//            LinkedList<InfoItem> infoItems = new LinkedList<>();
//            Random random = new Random(System.currentTimeMillis());
//            for (int i = 0; i < 5000; i++) {
//                InfoItem infoItem = new InfoItem(0, 0, "" + random.nextInt(30), "" + random.nextInt(60), "" + System.currentTimeMillis());
//                infoItems.add(infoItem);
//            }
//            DataDao.getInstance().insertNewInfoList(infoItems);

            DataDao.getInstance().deleteAllInfo();
            List<InfoItem> itemList = DataDao.getInstance().queryAllInfo();
            int count = 0;
            for (InfoItem p : itemList) {
                System.out.println(p.toString());
                count++;
            }
            System.out.println("------" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface OnStatusChangedListener {
        void onEventCompleted(long deltaTime, long eventMount);

        void onProgress(long curr, long size);
    }

    private List<OnStatusChangedListener> listeners = new ArrayList<>();

    public void setOnStatusChangedListener(OnStatusChangedListener listener) {
        listeners.add(listener);
    }

}
