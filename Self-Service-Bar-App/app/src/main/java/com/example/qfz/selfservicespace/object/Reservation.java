package com.example.qfz.selfservicespace.object;

import java.io.Serializable;

public class Reservation implements Serializable{
    private String  begin_hour,       //预定起始小时 ,
                    end_hour,         //预定结束小时 ,
                    order_no,         //订单号 ,
                    people_num,       //人数
                    pay_channel,      //付款方式 ,
                    remark,           //用户备注 ,
                    scheduled_day,    //预定日期 ,
                    seat_ids,         //全部座位id拼接字符串 ,
                    status,           //订单状态 ,
                    bar_name,         //店名
                    bar_image_url,    //店铺图片
                    bail_amount,      //保证金
                    total_amount;    //订单总金额 ,


    public Reservation(String begin_hour, String end_hour, String order_no, String people_num, String pay_channel, String remark, String scheduled_day, String seat_ids, String status, String bar_name, String bar_image_url, String bail_amount, String total_amount) {
        this.begin_hour = begin_hour;
        this.end_hour = end_hour;
        this.order_no = order_no;
        this.people_num = people_num;
        this.pay_channel = pay_channel;
        this.remark = remark;
        this.scheduled_day = scheduled_day;
        this.seat_ids = seat_ids;
        this.status = status;
        this.bar_name = bar_name;
        this.bar_image_url = bar_image_url;
        this.bail_amount = bail_amount;
        this.total_amount = total_amount;
    }

    public String getBegin_hour() {
        return begin_hour;
    }

    public void setBegin_hour(String begin_hour) {
        this.begin_hour = begin_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPeople_num() {
        return people_num;
    }

    public void setPeople_num(String people_num) {
        this.people_num = people_num;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScheduled_day() {
        return scheduled_day;
    }

    public void setScheduled_day(String scheduled_day) {
        this.scheduled_day = scheduled_day;
    }

    public String getSeat_ids() {
        return seat_ids;
    }

    public void setSeat_ids(String seat_ids) {
        this.seat_ids = seat_ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBar_name() {
        return bar_name;
    }

    public void setBar_name(String bar_name) {
        this.bar_name = bar_name;
    }

    public String getBail_amount() {
        return bail_amount;
    }

    public void setBail_amount(String bail_amount) {
        this.bail_amount = bail_amount;
    }

    public String getBar_image_url() {
        return bar_image_url;
    }

    public void setBar_image_url(String bar_image_url) {
        this.bar_image_url = bar_image_url;
    }
}
