package usyd.comp5703.capstone.entity;

public class StudentPreferenceEntity {
    String sid;
    String name;
    String first;
    String second;
    String third;

    public void setName(String name) {
        this.name = name;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }
}
