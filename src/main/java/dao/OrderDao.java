package dao;

import entity.Item;
import entity.Order;

import java.util.List;

public interface OrderDao<T> extends ItemDao {

    //依照 customer id取商品
    public List<T> findByCustomerId(int customerId) throws Exception;

    //依照 item id取商品
    public List<T> findByItemId(int itemId) throws Exception;
}
