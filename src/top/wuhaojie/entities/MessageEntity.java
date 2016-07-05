package top.wuhaojie.entities;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 15:33
 * Version: 1.0
 */
public class MessageEntity {

    private int id;
    private String text;

    public MessageEntity(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new MessageEntity(id, text);
    }
}
