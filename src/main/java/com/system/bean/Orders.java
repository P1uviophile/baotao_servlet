package com.system.bean;

import java.sql.Date;

public class Orders {
    private Integer client_id;
    private Integer commodity_id;
    private Integer order_id;
    private String finish;
    private Integer count;
    private Integer price;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "client_id=" + client_id +
                ", commodity_id=" + commodity_id +
                ", order_id=" + order_id +
                ", finish='" + finish + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
