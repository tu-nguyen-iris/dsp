package io.dev.gateway.Service.User;

import io.dev.gateway.entity.User;
import io.dev.gateway.reponsitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${io.dev.secret}")
    private String JWT_SECRET ;
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

    public boolean createUser(User user) {
        User checkUser = userRepo.findByUsername(user.getUsername());
        if (checkUser == null) {
            userRepo.save(user);
            return true;
        }
        return false;
    }

}
