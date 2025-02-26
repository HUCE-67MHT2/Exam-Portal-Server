package com.examportal.server.Repositories;


import com.examportal.server.Entity.Option;

import java.util.List;

public interface OptionQuestionRepository {
    List<Option> getList();
    Option getOptionById(Long id);
    void save(Option option);
    void delete(Long id);

}
