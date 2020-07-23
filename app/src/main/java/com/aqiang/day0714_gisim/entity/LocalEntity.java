package com.aqiang.day0714_gisim.entity;

public class LocalEntity {

    /**
     * id : 1
     * useraccount : sample string 2
     * lon : 3.1
     * lat : 4.1
     * ctime : sample string 5
     */

    private int id;
    private String useraccount;
    private double lon;
    private double lat;
    private String ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
