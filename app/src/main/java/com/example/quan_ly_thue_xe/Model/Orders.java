package com.example.quan_ly_thue_xe.Model;

public class Orders {
    private int id,vehicles_id,users_id,start_time,end_time,status,total,incutted;

    public Orders() {
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

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
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
