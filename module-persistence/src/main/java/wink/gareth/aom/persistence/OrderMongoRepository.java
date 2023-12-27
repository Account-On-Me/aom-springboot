package wink.gareth.aom.persistence;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wink.gareth.aom.core.model.Order;

public interface OrderMongoRepository extends MongoRepository<Order, ObjectId> {
}
