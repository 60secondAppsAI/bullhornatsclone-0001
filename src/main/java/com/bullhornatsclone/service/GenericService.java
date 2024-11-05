package com.bullhornatsclone.service;

import com.bullhornatsclone.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}