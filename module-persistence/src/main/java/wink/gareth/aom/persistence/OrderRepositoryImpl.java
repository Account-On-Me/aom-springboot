package wink.gareth.aom.persistence;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import wink.gareth.aom.core.model.Order;
import wink.gareth.aom.core.model.OrderRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderMongoRepository orderMongoRepository;

    @Override
    public Order save(Order order) throws Exception {
        if (order.getId() == null) throw new Exception("Saving an order with no id.");
        return orderMongoRepository.save(order);
    }

    @Override
    public Optional<Order> findById(ObjectId id) throws Exception {
        return orderMongoRepository.findById(id);
    }

    @Override
    public Optional<Order> findById(String id) throws Exception {
        ObjectId oid = new ObjectId(id);
        return orderMongoRepository.findById(oid);
    }

    @Override
    public List<Order> findAll() throws Exception {
        return orderMongoRepository.findAll();
    }

    @Override
    public List<Order> findAllById(Iterable<ObjectId> ids) throws Exception {
        return orderMongoRepository.findAllById(ids);
    }

    @Override
    public void delete(Order order) throws Exception {
        orderMongoRepository.deleteById(order.getId());
    }

    @Override
    public void deleteById(ObjectId id) throws Exception {
        orderMongoRepository.deleteById(id);
    }
}
