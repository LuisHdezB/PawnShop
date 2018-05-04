package es.ulpgc.eite.clean.mvp.sample.app;

import java.util.ArrayList;

public class Shop {
    private int id;
    private String name;
    private String code;
    private int zone;
    private String mail;
    private ArrayList<Timetable> timetable;
    private double latitude;
    private double longitude;

    public Shop(String name, String code, int zone, String mail, ArrayList timetable, double latitude, double longitude) {
        this.name = name;
        this.code = code;
        this.zone = zone;
        this.mail = mail;
        this.timetable = timetable;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Shop (String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() { return name;}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shop){
            Shop item = (Shop) obj;

            if (item.getCode() == getCode()){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Timetable> getTimetable() {
        return timetable;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Timetable {
        private int idHour;
        private String hour;
        private boolean busy;

        public Timetable(int idHour, String hour) {
            this.idHour = idHour;
            this.hour = hour;
            this.busy = false;
        }

        public int getIdHour() {
            return idHour;
        }

        public void setIdHour(int idHour) {
            this.idHour = idHour;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public boolean isBusy() { return busy; }

        public void setBusy(boolean busy) { this.busy = busy; }
    }

}
