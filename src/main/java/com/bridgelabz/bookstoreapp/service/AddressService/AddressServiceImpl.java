package com.bridgelabz.bookstoreapp.service.AddressService;

import com.bridgelabz.bookstoreapp.dto.AddressDTO;
import com.bridgelabz.bookstoreapp.entity.Address;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.AddressRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements IAddressService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAddressByToken(String token) {
        String emailId = UserLoginServiceImpl.verifyToken(token);
        User user = userRepository.getEmailIdByEmail(emailId);
        List<Address> addresses = addressRepository.getAddressesByUserId(user.getId());
        return addresses;
    }

    @Override
    public Address addAddress(AddressDTO addressDTO) {
        Long userId = addressDTO.getUserId();
        User user = userRepository.getById(userId);
        Address address  = new Address();
        address.setFullName(addressDTO.getFullName());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setPinCode(addressDTO.getPinCode());
        address.setAddress(addressDTO.getAddress());
        address.setLandMark(addressDTO.getLandMark());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setType(addressDTO.getType());
        address.setUser(user);
        /*Address map = modelMapper.map(addressDTO, Address.class);*/
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public Void deleteAddressByID() {
        return null;
    }
}
