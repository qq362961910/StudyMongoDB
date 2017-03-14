package service.impl;

import entity.Lbs;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import service.LbsService;

@Service
public class LbsServiceImpl extends BaseServiceImpl<Lbs, ObjectId> implements LbsService {
}
