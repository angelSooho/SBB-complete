package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.QuestionService;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;


    @Test
    @Transactional
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            questionService.create(subject, content, null);
        }
    }

    @Test
    @Transactional
    void testJpa2() {
        Question question = questionRepository.findById(1L).get();
        question.modify("hi","h2");

        Assertions.assertThat(question.getSubject()).isEqualTo("hi");
    }

}