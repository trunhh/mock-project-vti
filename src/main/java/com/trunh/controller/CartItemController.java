package com.trunh.controller;

import com.trunh.dto.CartItemDTO;
import com.trunh.entity.CartItem;
import com.trunh.form.CartItemCreateForm;
import com.trunh.form.CartItemUpdateForm;
import com.trunh.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/cart-item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public CartItemDTO getCartItemById(Pageable pageable, @RequestParam("id") int id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        CartItemDTO cartItemDTO= modelMapper.map(cartItem, CartItemDTO.class);
        return cartItemDTO;
    };
    @PostMapping
    public ResponseEntity<?> createCartItem(@RequestBody @Valid CartItemCreateForm form) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        CartItem cartItem = modelMapper.map(form, CartItem.class);
        cartItemService.createCartItem(cartItem);
        return ResponseEntity.ok().body("Add item to cart successfully");
    }
    @PutMapping
    public ResponseEntity<?> updateCartItem(@RequestParam(name = "id") int id, @RequestBody @Valid CartItemUpdateForm form) {
        CartItem cartItem = modelMapper.map(form, CartItem.class);
        cartItem.setId(id);
        cartItemService.updateCartItem(cartItem);
        return ResponseEntity.ok().body("Update cart item succcessfully");
    }
    @DeleteMapping
    public ResponseEntity<?> deleteCartItem(@RequestParam(name="id") int id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().body("Deleted product successfully");
    }
    @DeleteMapping("/multiple")
    public ResponseEntity<?> deleteMultipleCartItem(@RequestBody List<Integer> ids) {
        cartItemService.deleteMultipleCartItems(ids);
        return ResponseEntity.ok().body("Deleted products successfully");
    }
    @DeleteMapping("/clear")
    public ResponseEntity<?> deleteAllCartItem(@RequestParam(name="accountId") int accountId) {
        cartItemService.deleteAllCartItems(accountId);
        return ResponseEntity.ok().body("Deleted products successfully");
    }
}
