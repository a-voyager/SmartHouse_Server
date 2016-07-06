package top.wuhaojie.entities;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 22:01
 * Version: 1.0
 */
public class InfoItem {

    /**
     * 自增ID字段
     */
    private int mId = 0;
    /**
     * 节点号字段
     */
    private int mNodeId = 0;
    /**
     * 温度字段
     */
    private String mTemperature = "";
    /**
     * 湿度字段
     */
    private String mHumidity = "";
    /**
     * 风速字段
     */
    private String mWindSpeed = "";
    /**
     * 风向字段
     */
    private String mWindDirection = "";
    /**
     * 窗帘状态字段
     */
    private String mCurtainState = "";
    /**
     * 是否安全字段
     */
    private String mIsSafe = "";
    /**
     * 烟雾字段
     */
    private String mSmoke = "";
    /**
     * 超声波字段
     */
    private String mUltrasonicWave = "";
    /**
     * 时间戳字段
     */
    private long mTimeStamp;


    public InfoItem(int id, int nodeId, String temperature, String humidity, long timeStamp) {
        mId = id;
        mNodeId = nodeId;
        mTemperature = temperature;
        mHumidity = humidity;
        mTimeStamp = timeStamp;
    }

    public InfoItem(int id, int nodeId, String temperature, String humidity, String curtainState, String isSafe, String smoke, long timeStamp) {
        mId = id;
        mNodeId = nodeId;
        mTemperature = temperature;
        mHumidity = humidity;
        mCurtainState = curtainState;
        mIsSafe = isSafe;
        mSmoke = smoke;
        mTimeStamp = timeStamp;
    }

    public InfoItem(int id, int nodeId, String temperature, String humidity, String windSpeed, String windDirection, String curtainState, String isSafe, String smoke, String ultrasonicWave, long timeStamp) {
        mId = id;
        mNodeId = nodeId;
        mTemperature = temperature;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
        mWindDirection = windDirection;
        mCurtainState = curtainState;
        mIsSafe = isSafe;
        mSmoke = smoke;
        mUltrasonicWave = ultrasonicWave;
        mTimeStamp = timeStamp;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNodeId() {
        return mNodeId;
    }

    public void setNodeId(int nodeId) {
        mNodeId = nodeId;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        mTemperature = temperature;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }

    public String getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        mWindSpeed = windSpeed;
    }

    public String getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(String windDirection) {
        mWindDirection = windDirection;
    }

    public String getCurtainState() {
        return mCurtainState;
    }

    public void setCurtainState(String curtainState) {
        mCurtainState = curtainState;
    }

    public String getIsSafe() {
        return mIsSafe;
    }

    public void setIsSafe(String isSafe) {
        mIsSafe = isSafe;
    }

    public String getSmoke() {
        return mSmoke;
    }

    public void setSmoke(String smoke) {
        mSmoke = smoke;
    }

    public String getUltrasonicWave() {
        return mUltrasonicWave;
    }

    public void setUltrasonicWave(String ultrasonicWave) {
        mUltrasonicWave = ultrasonicWave;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "InfoItem{" +
                "mId=" + mId +
                ", mNodeId=" + mNodeId +
                ", mTemperature='" + mTemperature + '\'' +
                ", mHumidity='" + mHumidity + '\'' +
                ", mWindSpeed='" + mWindSpeed + '\'' +
                ", mWindDirection='" + mWindDirection + '\'' +
                ", mCurtainState='" + mCurtainState + '\'' +
                ", mIsSafe='" + mIsSafe + '\'' +
                ", mSmoke='" + mSmoke + '\'' +
                ", mUltrasonicWave='" + mUltrasonicWave + '\'' +
                ", mTimeStamp='" + mTimeStamp + '\'' +
                '}';
    }


}
