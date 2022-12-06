package com.example.quan_ly_thue_xe.Model;

public class Orders {
    private int id,vehicles_id,status,total,incutted;
    private String start_time,end_time,date,users_id,giotraxe;

    public Orders() {
    }

    public String getGiotraxe() {
        return giotraxe;
    }

    public void setGiotraxe(String giotraxe) {
        this.giotraxe = giotraxe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicles_id() {
        return vehicles_id;
    }

    public void setVehicles_id(int vehicles_id) {
        this.vehicles_id = vehicles_id;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIncutted() {
        return incutted;
    }

    public void setIncutted(int incutted) {
        this.incutted = incutted;
    }
}
