package es.ulpgc.eite.clean.mvp.sample.app;

import java.util.ArrayList;

public class Shop {
    private int id;
    private String name;
    private String code;
    private int zone;
    private String mail;
    private ArrayList<Calendar> calendar;
    private long latitud;
    private long longitud;

    public Shop(String name, String code, int zone, String mail, ArrayList calendar, long latitud, long longitud) {
        this.name = name;
        this.code = code;
        this.zone = zone;
        this.mail = mail;
        this.calendar = calendar;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Shop (String name, long latitud, long longitud){
        this.name = name;
        this.latitud = latitud;
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

    public ArrayList<Calendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Calendar> calendar) {
        this.calendar = calendar;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
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

    public static class Calendar{
        private int idHour;
        private String hour;

        public Calendar(int idHour, String hour) {
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
