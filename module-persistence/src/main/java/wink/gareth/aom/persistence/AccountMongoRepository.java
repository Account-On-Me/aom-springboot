package wink.gareth.aom.persistence;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wink.gareth.aom.core.model.Account;

public interface AccountMongoRepository extends MongoRepository<Account, ObjectId> {
}
