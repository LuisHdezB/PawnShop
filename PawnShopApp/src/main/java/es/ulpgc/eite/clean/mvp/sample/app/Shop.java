package es.ulpgc.eite.clean.mvp.sample.app;

public class Shop {
    private String name;
    private String code;
    private int zone;
    private String mail;
    private String calendar;

    public Shop(String name, String code, int zone, String mail, String calendar) {
        this.name = name;
        this.code = code;
        this.zone = zone;
        this.mail = mail;
        this.calendar = calendar;
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

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
