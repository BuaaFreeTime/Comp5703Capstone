package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;

import java.util.List;

@Service
public class MarkingService {
    @Autowired
    GroupDao groupDao;

    public void updateMarksClient(String id,  String feedback) {

        if (!feedback.equals("")) groupDao.updateFeedback(id, feedback);
    }

    public List<String> getMarkingName(String cid){
        return groupDao.getMarkingName(cid);
    }
}
