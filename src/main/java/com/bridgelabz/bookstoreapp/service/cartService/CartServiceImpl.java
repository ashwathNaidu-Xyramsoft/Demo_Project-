package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cart> getAllCartItems() {
        return cartrepository.findAll();
    }

    public List<Cart> getBookStoreDataByUserId(Long userId) {
        return cartrepository.findCartsByUsersId(userId);
    }

    @Override // not using this method
    public Cart addToCart(Long bookId) {
        Book book = bookRepository.getBookByBookId(bookId);
        System.out.println(book);
        Cart cart = new Cart();
       /* cart.setBooks(book);*/
        cart.setQuantity(4L);
        cart.setUsers(null);
        return cartrepository.save(cart);
    }

    @Override // working 80% but need to still do
    public Cart addDataToCart(CartDTO cartDTO) {
        Long bookId = cartDTO.getBookId();
        Long userId = cartDTO.getUserId();
        Long quantity = cartDTO.getQuantity(); // quantity
        // getting book by book ID
        Book book = bookRepository.getBookByBookId(bookId);
        // getting user by user ID
        User userById = userRepository.getUserById(userId);

        Cart cart = new Cart();
        cart.setUsers(userById);
        cart.setQuantity(quantity);
        cart.addBookToCart(book);
        /*cart.setBooks(book);*/
        return cartrepository.save(cart);
    }

    @Override // need to work on BOOK // now it is effecting in DB but need to work
    public Cart addBooksToCartByCartID(Long cartId, Long bookId) {
        Cart cart = cartrepository.findById(cartId).get();
        Book bookByBookId = bookRepository.findByBookId(bookId);
        cart.addBookToCart(bookByBookId);
        return cartrepository.save(cart);
    }

    @Override // working
    public void removeCart(Long cartId) {
        Cart cart = cartrepository.deleteCartByCartId(cartId);
        //Cart cartData = this.getBookStoreDataById(bookId);
        cartrepository.delete(cart);
    }

    @Override // working
    public Cart updateCart(Long cartId, Long quantity) {
        Cart cartData = cartrepository.getBookStoreDataByCartId(cartId);
        cartData.setQuantity(quantity);
        // modelMapper.map(cartData,cartData);
        return cartrepository.save(cartData);
    }

    @Override
    public List<Cart> getAllCartItemsUser(String token) {
        String emailID = UserLoginServiceImpl.verifyToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        System.out.println("Its crossing this line");
        List<Cart> cart = cartrepository.findAllByUsersId(userByEmail.getId());
        System.out.println(cart);
        return cart;
    }
}
