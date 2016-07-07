package top.wuhaojie.entities;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/7 17:45
 * Version: 1.0
 */
public class MostValueItem {
    private int max;
    private int min;

    public MostValueItem(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
