package usyd.comp5703.capstone.entity;

public class ClientEntity {
    String cid;
    String name;
    String email;
    String age;

    public ClientEntity(){
    }

    public ClientEntity(String cid, String name, String email, String age){
        this.cid = cid;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }
}
