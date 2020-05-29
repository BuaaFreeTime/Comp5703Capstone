package usyd.comp5703.capstone.entity;

public class StudentEntity {
    String sid;
    String name;
    String email;
    String groupId;
    String projectId;
    String semester;

    public StudentEntity(){
    }

    public StudentEntity(String sid, String name, String email, String gid, String pid, String semester){
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.groupId = gid;
        this.projectId = pid;
        this.semester = semester;
    }

    public StudentEntity(String sid, String name, String email, String semester){
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.groupId = "";
        this.projectId = "";
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getSid() {
        return sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

}
