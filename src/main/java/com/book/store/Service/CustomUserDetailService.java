package com.book.store.Service;

import com.book.store.Model.*;
import com.book.store.Model.RequestModel.AddAddress;
import com.book.store.Model.RequestModel.AddToCart;
import com.book.store.Model.RequestModel.NewUser;
import com.book.store.Repository.*;
import com.book.store.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartProductRepository cartProductRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    //@Autowired
    //OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    public void registerUser(NewUser dummy) {
        Set<Long> list = dummy.getAuthorities();
        Set<Role> roles = new HashSet<>();
        roles.addAll(roleRepository.findAllById(list));

        ApplicationUser user = new ApplicationUser(dummy.getUsername(),
                passwordEncoder.encode(dummy.getPassword()),
                roles,
                dummy.getPhone(),
                dummy.getImageId());
        userRepository.save(user);
    }

    @Override
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findUserByUsername(username);
        return user;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUserAddress(AddAddress newAddress, String token) {
        String username = jwtUtil.extractUsername(token);
        ApplicationUser user = loadUserByUsername(username);
        Address address = Address.builder()
                .houseNumber(newAddress.getHouseNumber())
                .country(newAddress.getCountry())
                .title(newAddress.getTitle())
                .state(newAddress.getState())
                .locality(newAddress.getLocality())
                .number(newAddress.getNumber())
                .pinCode(newAddress.getPinCode())
                .build();

        addressRepository.save(address);
        user.setAddresses(address);
        userRepository.save(user);
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public void updateUserAddress(Long id, AddAddress newAddress) {
        Address address = Address.builder()
                .id(id)
                .houseNumber(newAddress.getHouseNumber())
                .country(newAddress.getCountry())
                .title(newAddress.getTitle())
                .state(newAddress.getState())
                .locality(newAddress.getLocality())
                .number(newAddress.getNumber())
                .pinCode(newAddress.getPinCode())
                .build();
        addressRepository.save(address);
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).get();
    }

    public void deleteAddress(Long id, String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        ApplicationUser user = userRepository.findUserByUsername(username);
        Address address = addressRepository.findById(id).get();
        user.getAddresses().remove(address);
        userRepository.save(user);
        addressRepository.deleteById(id);
    }

    public void addBookToWishlist(Long id, String token){
        String username = jwtUtil.extractUsername(token);
        ApplicationUser user = userRepository.findUserByUsername(username);
        Book book = bookService.getBookById(id);
        user.setWishlist(book);
        userRepository.save(user);
    }

    public List<Book> getWishlist(String token) {
        String username = jwtUtil.extractUsername(token);
        List<Book> result = userRepository.findUserByUsername(username).getWishlist();
        return result;
    }

    public void deleteBookFromWishlist(Long id, String token) {
        String username = jwtUtil.extractUsername(token);
        ApplicationUser user = userRepository.findUserByUsername(username);
        Book book = bookService.getBookById(id);
        user.getWishlist().remove(book);
        userRepository.save(user);
    }

    public void moveWishlistToCart(AddToCart cart, String token) {
        String username = jwtUtil.extractUsername(token);
        ApplicationUser user = userRepository.findUserByUsername(username);
        //Book book = bookService.getBookById(id);
        CartProduct cartProduct = CartProduct.builder()
                .bookId(cart.getBookId())
                .quantity(cart.getQuantity())
                .build();
        cartProductRepository.save(cartProduct);
        user.setCartProduct(cartProduct);
        this.deleteBookFromWishlist(cart.getBookId(), token);
        userRepository.save(user);
    }

    public void placeOrder(Long id, String token) {
        String username = jwtUtil.extractUsername(token);
        ApplicationUser user = userRepository.findUserByUsername(username);

        List<CartProduct> products = user.getCartProduct();
        //products.forEach(elem -> System.out.println(elem.toString()));
        AtomicReference<Float> bill = new AtomicReference<>((float) 0);
        AtomicReference<Float> price = new AtomicReference<>((float) 0);

        List<OrderProduct> order = new ArrayList<>();

        products.forEach(elem -> {
            Long bookId = elem.getBookId();
            int quantity = elem.getQuantity();
            OrderProduct product = OrderProduct.builder()
                    .bookId(bookId)
                    .quantity(quantity)
                    .build();
            orderProductRepository.save(product);

            Book book = bookService.getBookById(bookId);
            int newStock = book.getStock()-quantity;
            if(newStock<0){
                try {
                    throw new Exception("Out of Stock!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            book.setStock(newStock);
            bookService.updateBook(book);
            price.set(book.getPrice()-(book.getPrice()/100)*book.getDiscount());
            bill.updateAndGet(v -> (float) (v + price.get() * elem.getQuantity()));
            order.add(product);

        });
        float amountAfterDiscount = bill.get();
        float amountAfterTax = amountAfterDiscount+((amountAfterDiscount / 100) * 12);
        float amountToPay = amountAfterTax + 60;

        OrderHistory history = OrderHistory.builder()
                .orderProducts(order)
                .price(amountToPay)
                .addressId(id)
                .orderState(OrderState.PROCESSING)
                .orderDate(new Date()).build();


        orderHistoryRepository.save(history);
        user.setOrderHistory(history);
        user.getCartProduct().clear();
        userRepository.save(user);


    }
}
