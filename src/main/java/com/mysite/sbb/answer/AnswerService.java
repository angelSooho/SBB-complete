package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.user.UserRepository;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;


    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer(content, question, author);
        question.getAnswerList().add(answer);
        answerRepository.save(answer);
        return answer;
    }
    
    public Answer getAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        Optional<Answer> byId = answerRepository.findById(answer.getAnswer_id());

        if (byId.isPresent()) {
            answer.modify(content);
        }
//        this.answerRepository.save(answer);
    }
    
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
    
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}