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
    private double longitud;

    public Shop(String name, String code, int zone, String mail, ArrayList timetable, double latitude, double longitud) {
        this.name = name;
        this.code = code;
        this.zone = zone;
        this.mail = mail;
        this.timetable = timetable;
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public Shop (String name, double latitude, double longitud){
        this.name = name;
        this.latitude = latitude;
        this.longitud = longitud;
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

    public void setTimetable(ArrayList<Timetable> timetable) {
        this.timetable = timetable;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
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

        public Timetable(int idHour, String hour) {
            this.idHour = idHour;
            this.hour = hour;
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
    }

}
