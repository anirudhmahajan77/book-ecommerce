package com.book.store.Service;

import com.book.store.Model.Address;
import com.book.store.Model.ApplicationUser;
import com.book.store.Model.Book;
import com.book.store.Model.RequestModel.AddAddress;
import com.book.store.Model.RequestModel.AddWishlist;
import com.book.store.Model.RequestModel.NewUser;
import com.book.store.Model.Role;
import com.book.store.Repository.AddressRepository;
import com.book.store.Repository.RoleRepository;
import com.book.store.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddressRepository addressRepository;

    public void registerUser(NewUser dummy) {
        Set<Integer> list = dummy.getAuthorities();
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

    public void addUserAddress(AddAddress newAddress) {

        ApplicationUser user = loadUserByUsername(newAddress.getUsername());
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

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public void addBookToWishlist(AddWishlist addWishlist){
        ApplicationUser user = userRepository.findUserByUsername(addWishlist.getUsername());
        Book book = bookService.getBookById(addWishlist.getBookId());
        user.setWishlist(book);
        userRepository.save(user);
    }
}
