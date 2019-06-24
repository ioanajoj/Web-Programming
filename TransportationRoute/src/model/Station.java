package model;

/**
 * @author joj on 5/8/2019
 **/
public class Station {
    private int sid;
    private String sName;

    public Station(int sid, String sName) {
        this.sid = sid;
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Station{" +
                "sid=" + sid +
                ", sName='" + sName + '\'' +
                '}';
    }

    public String getStationButton() {
        return "<button class='btn btn-info' onclick='nextStation(" + sid + ")'>" +
                sName +
                "</button>";
    }

    public String getStationOption() {
        return "<option value=" + sid + ">" + sName + "</option>";
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
