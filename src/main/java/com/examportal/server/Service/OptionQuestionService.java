package com.examportal.server.Service;

import com.examportal.server.Entity.Option;

import java.util.List;

public interface OptionQuestionService {
    List<Option> getList();
    Option getOptionById(Long id);
    void save(Option option);
    void delete(Long id);
}
