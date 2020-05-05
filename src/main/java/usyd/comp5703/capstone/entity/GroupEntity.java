package usyd.comp5703.capstone.entity;

import javax.swing.*;
import java.util.Date;

public class GroupEntity {
    String id;
    String marks;
    String student1;
    String student2;
    String student3;
    String student4;
    String student5;
    String presentation;



    public GroupEntity(String id){
        this.id = id;
        this.student1 = "Ryan Wang";
        this.student2 = "Zhaohui Wang";
        this.student3 = "Lenny Tan";
        this.student4 = "Jerry Wang";
        this.student5 = "Lei Zheng";
    }

    public String getPresentation() {
        return presentation;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getMarks() {
        return marks;
    }

    public GroupEntity(){ }

    public String getId() {
        return id;
    }

    public String getStudent1() {
        return student1;
    }

    public String getStudent2() {
        return student2;
    }

    public String getStudent3() {
        return student3;
    }

    public String getStudent4() {
        return student4;
    }

    public String getStudent5() {
        return student5;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setStudent1(String student1) {
        this.student1 = student1;
    }

    public void setStudent2(String student2) {
        this.student2 = student2;
    }

    public void setStudent3(String student3) {
        this.student3 = student3;
    }

    public void setStudent4(String student4) {
        this.student4 = student4;
    }

    public void setStudent5(String student5) {
        this.student5 = student5;
    }
}
