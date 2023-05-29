package com.trunh.service;

import com.trunh.entity.CartItem;
import com.trunh.repository.ICartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService{
    @Autowired
    private ICartItemRepository cartItemRepository;
    @Override
    public CartItem getCartItemById(int id) {
        return cartItemRepository.findById(id).get();
    };
    @Override
    public void createCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    };
    @Override
    public void updateCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    };
    @Override
    public void deleteCartItem(int id) {
        cartItemRepository.deleteById(id);
    };
    @Override
    public void deleteMultipleCartItems(List<Integer> ids) {
        cartItemRepository.deleteMultiCartItem(ids);
    };
    @Override
    public void deleteAllCartItems(Integer accountId) {cartItemRepository.deleteAllCartItem(accountId);};
}
