package com.deliveryjumper.service;

import com.deliveryjumper.domain.Item;
import com.deliveryjumper.domain.Member;
import com.deliveryjumper.domain.Order;
import com.deliveryjumper.domain.OrderItem;
import com.deliveryjumper.domain.OrderStatus;
import com.deliveryjumper.domain.Store;
import com.deliveryjumper.repository.ItemRepository;
import com.deliveryjumper.repository.MemberRepository;
import com.deliveryjumper.repository.OrderItemRepository;
import com.deliveryjumper.repository.OrderRepository;
import com.deliveryjumper.repository.StoreRepository;
import com.deliveryjumper.vo.OrderItemRequest;
import com.deliveryjumper.vo.OrderRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Long save(OrderRequest request){

        Member member = memberRepository.findById(request.getMemberId())
            .orElseThrow();
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow();

        List<Long> itemIds = createItemIds(request.getOrderItemRequests());
        List<Item> itemList = itemRepository.findAllByIds(itemIds);

        Map<Long, Item> items = new HashMap<>();
        itemList.stream().forEach(item -> items.put(item.getId(), item));

        Order order = new Order.Builder()
            .orderStatus(OrderStatus.ORDER)
            .phoneNumber(request.getPhoneNumber())
            .address(request.getAddress())
            .member(member)
            .store(store)
            .build();
        orderRepository.save(order);

        List<OrderItem> orderItems = createOrderItems(items, order, request.getOrderItemRequests());
        orderItemRepository.saveAll(orderItems);
        return order.getId();
    }

    private List<OrderItem> createOrderItems(Map<Long, Item> items, Order order, List<OrderItemRequest> itemRequests) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < itemRequests.size(); i++) {
            OrderItemRequest orderItemRequest = itemRequests.get(i);
            OrderItem orderItem = new OrderItem.Builder()
                .item(items.get(orderItemRequest.getItemId()))
                .title(items.get(orderItemRequest.getItemId()).getTitle())
                .order(order)
                .orderItemPrice(orderItemRequest.getOrderItemPrice())
                .orderItemQuantity(orderItemRequest.getOrderItemQuantity())
                .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private List<Long> createItemIds(List<OrderItemRequest> orderItemRequests) {
        List<Long> itemIds = new ArrayList<>();
        for (int i = 0; i < orderItemRequests.size(); i++) {
            itemIds.add(orderItemRequests.get(i).getItemId());
        }
        return itemIds;
    }

    public Order findById(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow();
        return order;
    }
}
