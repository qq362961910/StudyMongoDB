package service.impl;

import dao.LbsDao;
import entity.Lbs;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LbsService;

import java.util.List;

@Service
public class LbsServiceImpl extends BaseServiceImpl<Lbs, ObjectId> implements LbsService {

    @Autowired
    private LbsDao lbsDao;

    @Override
    public List<Lbs> searchNear(LbsDao.LbsQueryParam param) {
        return lbsDao.searchNear(param);
    }
}
