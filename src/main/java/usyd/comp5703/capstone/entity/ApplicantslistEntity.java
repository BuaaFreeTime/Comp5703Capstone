package usyd.comp5703.capstone.entity;

public class ApplicantslistEntity {
    public ProjectEntity project;
    public StudentEntity student;
    public String pid;
    public String pname;
    public String sid;
    public String ptype;
    public String sname;

    public ApplicantslistEntity(){}

    public ApplicantslistEntity(ProjectEntity projectEntity, StudentEntity studentEntity){
        this.project = projectEntity;
        this.student = studentEntity;
        this.pid = projectEntity.getId();
        this.sid = studentEntity.getSid();
        this.pname = projectEntity.getName();
        this.sname = studentEntity.getName();
        this.ptype = projectEntity.getType();
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPtype() {
        return ptype;
    }

    public String getSname() {
        return sname;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSid() {
        return sid;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
