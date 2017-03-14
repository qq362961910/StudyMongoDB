package dao.impl;

import dao.LbsDao;
import entity.Lbs;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class LbsDaoImpl extends BaseDaoImpl<Lbs, ObjectId> implements LbsDao{

}
