package cs.hku.hk.whenwhere.model;

public class Activities {
    private int id;
    private String name;
    private String place;
    private String time;

    public Activities(){

    }

    public Activities(int id, String name, String place, String time){
        this.id=id;
        this.name=name;
        this.place=place;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
