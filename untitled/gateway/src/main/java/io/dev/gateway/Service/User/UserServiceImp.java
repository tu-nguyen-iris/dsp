package io.dev.gateway.Service.User;

import io.dev.gateway.entity.User;
import io.dev.gateway.reponsitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }

    public void createUser(User user) {
        User checkUser = userRepo.findByUsername(user.getUsername());
        System.out.println(checkUser);
        if (checkUser == null) {
            userRepo.save(user);
        } else throw new IllegalArgumentException();
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetail(user);
    }
}
