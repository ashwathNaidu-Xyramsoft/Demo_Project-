package com.bridgelabz.bookstoreapp.service.wishList;

import com.bridgelabz.bookstoreapp.service.userService.IUserLoginService;
import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import com.bridgelabz.bookstoreapp.dto.WishListDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.entity.WishList;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.repository.WishListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class WishListServiceImpl implements IWishListService{

    @Autowired
    private IUserLoginService iUserLoginService;

    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public Long getUserByEmail(String token) {

        return null;
    }

    @Override
    public List<Book> getBooksByToken(String token) {
        String email = UserLoginServiceImpl.findSubByDecodeToken(token);
        User user = userRepository.getUserByEmail(email);
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < user.getWishLists().size(); i++) {
            List<Book> books = user.getWishLists().get(i).getBooks();
            for (int j = 0; j < books.size(); j++) {
                Book book = books.get(j);
                bookList.add(book);
            }
        }
        return bookList;

        /* for (int i = 0; i < user.getCart().size(); i++) {
            List<Book> books = user.getCart().get(i).getBooks();
            for (int j = 0; j < books.size(); j++) {
                Book book = books.get(j);
                bookList.add(book);
            }
        }*/
    }

    @Override //done
    public WishList addBookToWishList(WishListDTO wishListDTO) {
        Long bookId = wishListDTO.getBookId();
        Long userId = wishListDTO.getUserId();
        // getting book by book ID
        Book book = bookRepository.getBookByBookId(bookId);
        // getting user by user ID
        User userById = userRepository.getUserById(userId);
        WishList wishList = new WishList();
        wishList.setUser(userById);
        wishList.addBookToWishList(book);
        return wishListRepository.save(wishList);
    }

    @Override
    public Cart addBooksToCartByCartID(Long cartId, Long bookId, Long quantity) {
        return null;
    }

    @Override //done
    public String deleteBookByBook_Id(Long books_book_id) {
        wishListRepository.deleteBookByBook_Id(books_book_id);
        return "Deleted book with ID " + books_book_id;
    }
}
