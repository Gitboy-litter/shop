package com.chen.xbshop;

import com.chen.xbshop.dao.ShoppingCatDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApplicationTests {
    @Autowired
    private ShoppingCatDao shoppingCat;
    @Test
    void contextLoads() {
    }
    @Test
    public void test(){
    }
}
