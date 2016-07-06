package top.wuhaojie.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 20:05
 * Version: 1.0
 */
public class ResponseEntity {
    private boolean mIsError = false;
    private int mErrorType = 0;
    private String mErrorMessage = "";
    private List<InfoItem> mInfoItems;

    public ResponseEntity() {
    }

    public ResponseEntity(boolean isError, int errorType, String errorMessage) {
        mIsError = isError;
        mErrorType = errorType;
        mErrorMessage = errorMessage;
    }

    public ResponseEntity(boolean isError, int errorType, String errorMessage, List<InfoItem> infoItems) {
        mIsError = isError;
        mErrorType = errorType;
        mErrorMessage = errorMessage;
        mInfoItems = infoItems;
    }

    public boolean isError() {
        return mIsError;
    }

    public void setError(boolean error) {
        mIsError = error;
    }

    public int getErrorType() {
        return mErrorType;
    }

    public void setErrorType(int errorType) {
        mErrorType = errorType;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public List<InfoItem> getInfoItems() {
        return mInfoItems;
    }

    public void setInfoItems(List<InfoItem> infoItems) {
        mInfoItems = infoItems;
    }

    public void addInfoItem(InfoItem infoItem) {
        if (mInfoItems == null)
            mInfoItems = new LinkedList<>();
        mInfoItems.add(infoItem);
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "mIsError=" + mIsError +
                ", mErrorType=" + mErrorType +
                ", mErrorMessage='" + mErrorMessage + '\'' +
                ", mInfoItems=" + mInfoItems +
                '}';
    }
}
