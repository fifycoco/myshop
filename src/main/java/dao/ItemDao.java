package dao;

import entity.Item;

import java.util.List;

public interface ItemDao<T> {
    //更新商品
    public boolean update(T item);

    //上架(需檢查是否有相同商品)
    public boolean add(T item);

    //檢查商品(是否存在)
    public boolean check(T item);

    //取得目前商品
    public List<T> findAll();

    //依照id取商品
    public T findById(int id);

}
