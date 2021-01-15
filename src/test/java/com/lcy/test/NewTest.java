package com.lcy.test;

import com.lcy.autogenerator.entity.Skillcard.LifeGoalCard;
import com.lcy.autogenerator.mapper.Skillcard.LifeGoalCardMapper;
import com.lcy.autogenerator.service.Skillcard.ILifeGoalCardService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewTest {

    @Autowired
    ILifeGoalCardService lifeGoalCardService;

    @Autowired
    @SuppressWarnings("all")
    LifeGoalCardMapper lifeGoalCardMapper;

    @Test
    public void Card(){
        LifeGoalCard lifeGoalCard = new LifeGoalCard();
        System.out.println(lifeGoalCard);
        lifeGoalCard.setCardName("诚实");
        lifeGoalCard.setCardDescribe("诚实守信");
//        lifeGoalCard.setId(1);
//        System.out.println(lifeGoalCardService.remove(1));
    }

    @Test
    public void testMapper(){
        List<LifeGoalCard> lifeGoalCards = lifeGoalCardMapper.listAll();
        System.out.println(lifeGoalCards);
    }

    @Test
    public void testListCard(){

    }
}
