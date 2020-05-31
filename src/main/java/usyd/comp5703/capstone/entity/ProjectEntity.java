package usyd.comp5703.capstone.entity;

public class ProjectEntity {

    String  id;
    String unit;
    String type;
    String name;
    String description;
    String Instruction;
    String client;
    String clientid;
    String tutor;
    String gnumber;
    String semester;
    String approve;
    String feedback;
    String group;

    public ProjectEntity(){
        this.gnumber = "4";
        this.approve = "no";
        this.semester = "2020 Semester 1";
        this.feedback = "Not yet feedback";
    }

    public ProjectEntity(String id) {
        this.id = id;
        if (Integer.valueOf(id)% 3 == 0) {
            this.unit = "Comp5709";
            this.type = "Software Engineering";
            this.name = "project"+id;
            this.description = "A capstone project management system";
            this.client = "Dr. Wu xi";
            this.clientid = "client1";
            this.tutor = "Omid";
            this.gnumber = "4";
            this.approve = "no";
            this.semester = "2020 Semester 1";
            this.feedback = "Not yet feedback";
            this.group = "no";
        }
        if (Integer.valueOf(id) % 3 == 1) {
            this.unit = "Comp5703";
            this.type = "Data Science";
            this.name = "project"+id;
            this.description = "To provide customers with the function of remote modification and reset of debit card transaction password";
            this.client = "Dr. Wang";
            this.tutor = "Zhaohui";
            this.clientid = "client2";
            this.gnumber = "4";
            this.approve = "no";
            this.semester = "2020 Semester 1";
            this.feedback = "Not yet feedback";
            this.group = "yes";
        }
        if (Integer.valueOf(id) % 3 == 2) {
            this.unit = "Comp5703";
            this.type = "Information System";
            this.name = "project"+id;
            this.description = "Service client";
            this.client = "Dr. Wu xi";
            this.tutor = "Jerry";
            this.gnumber = "4";
            this.clientid = "client1";
            this.approve = "no";
            this.semester = "2020 Semester 1";
            this.feedback = "Not yet feedback";
            this.group = "yes";
        }
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprove() {
        return approve;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setGnumber(String gnumber) {
        this.gnumber = gnumber;
    }

    public String getGnumber() {
        return gnumber;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
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
