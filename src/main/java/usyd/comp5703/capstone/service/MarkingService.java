package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;

import java.util.List;

@Service
public class MarkingService {
    @Autowired
    GroupDao groupDao;

    public void updateMarksClient(String id, String proposal, String progress, String Report,String presentation) {
        int marks = 0;
        marks = Integer.valueOf(proposal.split(",")[0]) + Integer.valueOf(progress.split(",")[0]);
        marks = marks + Integer.valueOf(Report.split(",")[0]) + Integer.valueOf(presentation.split(",")[0]);
        marks = marks / 4;
        groupDao.updateMarks(id, String.valueOf(marks));
    }

    public List<String> getMarkingName(String cid){
        return groupDao.getMarkingName(cid);
    }
}
