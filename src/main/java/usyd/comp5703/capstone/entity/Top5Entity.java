package usyd.comp5703.capstone.entity;

public class Top5Entity {
    String name;
    String num;

    public Top5Entity(){
    }

    public Top5Entity(String name, String num){
        this.name = name;
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}
