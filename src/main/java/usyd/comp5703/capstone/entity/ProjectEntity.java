package usyd.comp5703.capstone.entity;

public class ProjectEntity {

    String  id;
    String unit;
    String type;
    String name;
    String description;
    String Instruction;
    String client;
    String tutor;

    public ProjectEntity(){}

    public ProjectEntity(String id) {
        this.id = id;
        if (Integer.valueOf(id)% 3 == 0) {
            this.unit = "Comp5703";
            this.type = "Software Engineering";
            this.name = "CAPSTONE PM";
            this.description = "A capstone project management system";
            this.client = "Dr. Wu xi";
            this.tutor = "Omid";
        }
        if (Integer.valueOf(id) % 3 == 1) {
            this.unit = "Comp5708";
            this.type = "Data Science";
            this.name = "SPINGSTONE TECH";
            this.description = "To provide customers with the function of remote modification and reset of debit card transaction password";
            this.client = "Dr. Wang";
            this.tutor = "shuaibiwangyanpeng";
        }
        if (Integer.valueOf(id) % 3 == 2) {
            this.unit = "Comp5707";
            this.type = "Information System";
            this.name = "ZHIMINGDENG";
            this.description = "Service client";
            this.client = "Dr. Zhang";
            this.tutor = "shabiwangyanpeng";
        }
    }


    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public String getInstruction() {
        return Instruction;
    }

    public String getName() {
        return name;
    }

    public String getTutor() {
        return tutor;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }
}
