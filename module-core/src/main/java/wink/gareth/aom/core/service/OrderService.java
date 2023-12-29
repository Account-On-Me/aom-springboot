package wink.gareth.aom.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wink.gareth.aom.core.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    public List<AbstractOrderInfo> getAllAbstractOrderInfo() throws Exception{
        return orderRepository.findAll().stream().map(AbstractOrderInfo::new).toList();
    }

    public List<Order> getAllOrders() throws Exception{
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Order order) throws Exception{
        // process and save order
        orderRepository.save(order);
        order = orderRepository.findById(order.getId()).orElseThrow(() -> new Exception("Order not being saved."));
        log.warn("order payer: {}", order.getPayer().getName());

        order.computePaychecksTotal();

        // process and save paychecks to all candidates
        for (Account candidate : order.getCandidates()) {
            // skip if candidate is the payer(determined by id)
            if (candidate.getId().equals(order.getPayer().getId())) {
                continue;
            }
            // add paycheck to candidate
            candidate.addPaycheck(new Paycheck(candidate,
                    order.getPaychecksTotal()
                            .stream()
                            .filter(paycheck -> paycheck.getCandidate().getId().equals(candidate.getId()))
                            .findFirst()
                            .map(Paycheck::getShouldPay)
                            .orElseGet(() -> 0f)
            ));
            accountRepository.save(candidate);
        }
        return orderRepository.save(order);
    }

    public Order getOrderById(String id) throws Exception{
        return orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
    }

    public void deleteOrderById(String id) throws Exception{
        orderRepository.deleteById(new ObjectId(id));
    }

    public Order test() throws Exception{
        Account payer = accountRepository.findById("658e5e190aca874ffb524391").orElseThrow(() -> new Exception("gg"));
        List<OrderItem> items = new ArrayList<>();
        List<Paycheck> paychecks = new ArrayList<>();
        paychecks.add(new Paycheck(payer, 12.23f));
        OrderItem item1 = new OrderItem("Apple", "", "ITEM", "EQUAL", 14.3f, 4, false, paychecks);
        items.add(item1);
        Order order = new Order("WALMART", payer, items, new ArrayList<>(Arrays.asList(payer)));
        return orderRepository.save(order);
    }
}
