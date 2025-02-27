package com.flowerstore.dao;

import java.util.*;

public interface DAO<T> {

    public List<T> selectAll();

    public List<T> selectById(String id);
    
    public void insert (T ob);
    
    public void update (T ob);
    
    public void delete (String id);
}
