package wink.gareth.aom.core.model;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface AccountRepository {
    Account save(Account account) throws Exception;

    List<Account> findAll() throws Exception;

    Optional<Account> findById(ObjectId id) throws Exception;

    Optional<Account> findById(String id) throws Exception;

    void delete(Account account) throws Exception;

    void deleteById(ObjectId id) throws Exception;

}
