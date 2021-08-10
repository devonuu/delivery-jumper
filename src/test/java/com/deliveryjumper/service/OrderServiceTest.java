package com.deliveryjumper.service;

import com.deliveryjumper.domain.Address;
import com.deliveryjumper.domain.Category;
import com.deliveryjumper.domain.Item;
import com.deliveryjumper.domain.ItemStatus;
import com.deliveryjumper.domain.Member;
import com.deliveryjumper.domain.Order;
import com.deliveryjumper.domain.OrderStatus;
import com.deliveryjumper.domain.Role;
import com.deliveryjumper.domain.Store;
import com.deliveryjumper.domain.StoreStatus;
import com.deliveryjumper.repository.CategoryRepository;
import com.deliveryjumper.repository.ItemRepository;
import com.deliveryjumper.repository.MemberRepository;
import com.deliveryjumper.repository.OrderItemRepository;
import com.deliveryjumper.repository.OrderRepository;
import com.deliveryjumper.repository.StoreRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 8:43
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderRepository orderRepository;
    @Autowired OrderItemRepository orderItemRepository;

    @Autowired MemberRepository memberRepository;
    @Autowired StoreRepository storeRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired ItemRepository itemRepository;

    @BeforeEach
    void init(){
        Member host = new Member.Builder()
            .name("host")
            .email("host@host.com")
            .role(Role.ROLE_HOST)
            .password("1234")
            .address(new Address("431", "host", "a host 123"))
            .build();
        memberRepository.save(host);

        Store store = new Store.Builder(host, "딜리버리 점퍼", new Address("1010", "가게", "2층"))
            .storeTel("02-123-4567")
            .minimumOrderAmount(15000L)
            .storeStatus(StoreStatus.OPEN)
            .build();
        storeRepository.save(store);

        Category category = new Category.Builder()
            .store(store)
            .title("전체")
            .build();
        categoryRepository.save(category);

        Item item1 = new Item(category, "떡볶이", 2500, ItemStatus.ON);
        Item item2 = new Item(category, "피자", 8000, ItemStatus.ON);
        Item item3 = new Item(category, "순대", 3000, ItemStatus.ON);
        Item item4 = new Item(category, "오징어 튀김", 1000, ItemStatus.ON);
        Item item5 = new Item(category, "김말이 튀김", 1000, ItemStatus.ON);

        List<Item> items = Arrays.asList(item1, item2, item3, item4, item5);
        itemRepository.saveAll(items);

        Member member = new Member.Builder()
            .name("userA")
            .email("test@test.com")
            .role(Role.ROLE_CUSTOMER)
            .password("1234")
            .address(new Address("1234", "city", "a road 123"))
            .build();
        memberRepository.save(member);
    }

    @Test
    void saveOrder(){
        Member member = memberRepository.findByEmail("test@test.com").get();
        Store store = storeRepository.findByStoreName("딜리버리 점퍼").get(0);

        Order order = new Order.Builder()
            .orderStatus(OrderStatus.ORDER)
            .address(new Address("169270", "order1", "detail1"))
            .member(member)
            .store(store)
            .phoneNumber("010-2284-5638")
            .build();

        Order save = orderRepository.save(order);
        Optional<Order> findOrder = orderRepository.findById(save.getId());
        Assertions.assertThat(save).isEqualTo(findOrder.get());
    }
}