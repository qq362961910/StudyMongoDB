package dao.geo.query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.GeoNearOperation;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.util.Assert;

public class LbsAggregationOperation implements AggregationOperation {

    private final LbsNearQuery nearQuery;
    private final String distanceField;

    /**
     * Creates a new {@link GeoNearOperation} from the given {@link NearQuery} and the given distance field. The
     * {@code distanceField} defines output field that contains the calculated distance.
     *
     * @param query         must not be {@literal null}.
     * @param distanceField must not be {@literal null}.
     */
    public LbsAggregationOperation(LbsNearQuery nearQuery, String distanceField) {

        Assert.notNull(nearQuery, "NearQuery must not be null.");
        Assert.hasLength(distanceField, "Distance field must not be null or empty.");

        this.nearQuery = nearQuery;
        this.distanceField = distanceField;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.core.aggregation.AggregationOperation#toDBObject(org.springframework.data.mongodb.core.aggregation.AggregationOperationContext)
     */
    @Override
    public DBObject toDBObject(AggregationOperationContext context) {

        BasicDBObject command = (BasicDBObject) context.getMappedObject(nearQuery.toDBObject());
        command.put("distanceField", distanceField);

        return new BasicDBObject("$geoNear", command);
    }
}
