package com.examportal.server.Service;
import com.examportal.server.Entity.Option;
import com.examportal.server.Repositories.OptionQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OptionQuestionServiceImpl implements OptionQuestionService {

    @Autowired
    private OptionQuestionRepository optionQuestionRepository;

    @Override
    public List<Option> getList() {
        return optionQuestionRepository.getList();
    }

    @Override
    public Option getOptionById(Long id) {
        return optionQuestionRepository.getOptionById(id);
    }

    @Override
    public void save(Option option) {

        optionQuestionRepository.save(option);
    }

    @Override
    public void delete(Long id) {

        optionQuestionRepository.delete(id);
    }
}
