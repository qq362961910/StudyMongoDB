package service;

import dao.LbsDao;
import entity.Lbs;
import org.bson.types.ObjectId;

import java.util.List;

public interface LbsService extends BaseService<Lbs, ObjectId> {
    List<Lbs> searchNear(LbsDao.LbsQueryParam param);
}
