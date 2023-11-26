package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ItemServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    @Autowired ItemService itemService;

    @Test
    public void save() throws Exception {
        // given
        Item item = new Book();
        item.setName("book1");
        item.setPrice(1000);


        // when
        Long saveId = itemService.saveItem(item);

        //then
        em.flush();
        Assertions.assertEquals(item, itemRepository.findOne(saveId));

    }

    @Test
    public void findAll() throws Exception {
        // given
        Item item = new Book();
        item.setName("book1");
        item.setPrice(1000);
        Item item2= new Book();
        item2.setName("book2");
        item2.setPrice(1000);

        // when
        itemService.saveItem(item);
        itemService.saveItem(item2);

        //then
        Assertions.assertEquals(item2, itemService.findItems().get(1));

    }

}