package wink.gareth.aom.core.model;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order) throws Exception;

    Optional<Order> findById(ObjectId id) throws Exception;

    Optional<Order> findById(String id) throws Exception;

    List<Order> findAll() throws Exception;

    List<Order> findAllById(Iterable<ObjectId> ids) throws Exception;

    void delete(Order order) throws Exception;

    void deleteById(ObjectId id) throws Exception;

}
