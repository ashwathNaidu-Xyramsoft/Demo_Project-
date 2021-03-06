package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
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
        System.out.println(book.getQuantity()); // getQuantity
        Cart cart = new Cart();
        cart.setUsers(userById);
        cart.setQuantity(quantity);
        cart.addBookToCart(book);
        return cartrepository.save(cart);
        /* if (quantity != 0 && book.getQuantity() >= quantity){

            Cart cart = new Cart();
            cart.setUsers(userById);
            cart.setQuantity(quantity);
            cart.addBookToCart(book);
            return cartrepository.save(cart);
        }
        throw new BookStoreException("Book is out stock !!! -> ( Enter the valid quantity )");
        *//*cart.setBooks(book);*//*
*/
    }

    @Override // need to work on BOOK // now it is effecting in DB but need to work
    public Cart addBooksToCartByCartID(Long cartId, Long bookId,Long quantity) {
        Book book = bookRepository.getBookByBookId(bookId);

        if (quantity != 0 && book.getQuantity() >= quantity){

            Cart cart = cartrepository.findById(cartId).get();
            Book bookByBookId = bookRepository.findByBookId(bookId);
            cart.setQuantity(quantity);
            cart.addBookToCart(bookByBookId);
            return cartrepository.save(cart);
        }
        throw new BookStoreException("Book is out stock !!! -> ( Enter the valid quantity )");
    }

    @Override // working

    public void removeCart(Long cartId) {
        cartrepository.deleteById(cartId);
    }

    @Override // working
    public Cart updateCart(Long cartId, Long quantity) {
        Cart cartData = cartrepository.getBookStoreDataByCartId(cartId);
        cartData.setQuantity(quantity);
        return cartrepository.save(cartData);
    }

    @Override
    public List<Cart> getAllCartItemsUser(String token) {
        String emailID = UserLoginServiceImpl.verifyToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        List<Cart> cart = cartrepository.findAllByUsersId(userByEmail.getId());
        return cart;
    }

    @Override
    public List<Cart> findCartsByUsers_Id(Long userId) {
        List<Cart> cartsByUsers_id = cartrepository.findCartsByUsers_Id(userId);
        return cartsByUsers_id;
    }

    @Override
    public List<Cart> getBookStoreDataByUsers_Id(Long users_id) {
        return cartrepository.getBookStoreDataByUsers_Id(users_id);
    }

    @Override
    public Long getUserByEmail(String token) {
        String email = UserLoginServiceImpl.verifyToken(token);
        User user = userRepository.getUserByEmail(email);
        Long userId = user.getId();
        return userId;
    }

    @Override
    public String deleteBookByBook_Id(Long books_book_id) {
        cartrepository.deleteBookByBook_Id(books_book_id);
        return "Deleted book with ID " + books_book_id;
    }

    @Override
    public List<Book> getBooksByToken(String token) { // this given me current selected data of books in databases
        String email = UserLoginServiceImpl.findSubByDecodeToken(token);
        User user = userRepository.getUserByEmail(email);
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < user.getCart().size(); i++) {
            List<Book> books = user.getCart().get(i).getBooks();
            for (int j = 0; j < books.size(); j++) {
                Book book = books.get(j);
                bookList.add(book);
            }
        }
        return bookList;
    }

    @Override // check this function
    public String deleteAllBooksByToken(String token) {
        String emailId = UserLoginServiceImpl.verifyToken(token);
        if (emailId != null) {
            User user = userRepository.getEmailIdByEmail(emailId);
            cartrepository.deleteAllByUsers_Id(user.getId());
            return "All carts are deleted";
        }
        log.error("Invalid token, Enter the valid email-Id and Password !!!");
        throw new BookStoreException("Enter the valid email-Id and Password !!!");
    }
}
