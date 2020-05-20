package usyd.comp5703.capstone.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.SemesterDao;
import usyd.comp5703.capstone.entity.SemesterEntity;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    SemesterDao semesterDao;

    public List<SemesterEntity> getAllSemester() {
        return semesterDao.getAllSemester();
    }

    public void addSemester(String name) {
        SemesterEntity semesterEntity = new SemesterEntity();
        semesterEntity.setName(name);
        semesterDao.addSemester(semesterEntity);
    }
}
