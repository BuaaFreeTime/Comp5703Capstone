package usyd.comp5703.capstone.entity;

public class StudentPreferenceEntity {
    String time;
    String sid;
    String name;
    String first;
    String second;
    String third;
    String semester;
    String group;

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

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
