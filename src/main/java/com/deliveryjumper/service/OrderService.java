package com.deliveryjumper.service;

import com.deliveryjumper.domain.Member;
import com.deliveryjumper.domain.Order;
import com.deliveryjumper.domain.OrderStatus;
import com.deliveryjumper.domain.Store;
import com.deliveryjumper.repository.ItemRepository;
import com.deliveryjumper.repository.MemberRepository;
import com.deliveryjumper.repository.OrderRepository;
import com.deliveryjumper.repository.StoreRepository;
import com.deliveryjumper.vo.OrderRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 8:21
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(OrderRequest request){
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow();
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow();

        Order order = new Order.Builder()
            .orderStatus(OrderStatus.ORDER)
            .phoneNumber(request.getPhoneNumber())
            .address(request.getAddress())
            .member(member)
            .store(store)

        orderRepository.save(order);
        return order.getId();
    }

    public Order findById(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow();
        return order;
    }
}
