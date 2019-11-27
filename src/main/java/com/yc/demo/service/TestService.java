package com.yc.demo.service;


import com.yc.demo.model.RePos;
import com.yc.demo.model.User;

import java.util.List;

public interface TestService {

    List<RePos> getList();

    Integer edit(User user);

    Integer add(User user);

    Integer delete(Long id);

    User getDetail(Long id);
}
