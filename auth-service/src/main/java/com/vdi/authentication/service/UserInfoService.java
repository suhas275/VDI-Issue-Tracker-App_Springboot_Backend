package com.vdi.authentication.service;


import com.vdi.authentication.entity.UserInfo;
import com.vdi.authentication.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByuserName(username);
        System.out.print(userInfo);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }
    public boolean existsByUsername(String username) { 
      	return userInfoRepository.existsByUserName(username); 
       } 
	  public boolean existsByEmail(String email) {
	        return userInfoRepository.existsByEmail(email);
	    }

    public UserInfo addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoRepository.save(userInfo);
//        return "User added successfully";
    }
    public List<UserInfo> getAllUser(){
         return userInfoRepository.findAll();
    }
   
//    public boolean deleteUser(Integer id) {       
//    	if (userInfoRepository.existsById(id)) {       
//    		userInfoRepository.deleteById(id);      
//    		return true;         } 
//    	else { 
//    		return false; }}
    
    public UserInfo getUser(Integer id) {
        return userInfoRepository.findById(id).orElse(null);
    }


    public UserInfo updateUser(UserInfo userInfo) {
    	
        Optional<UserInfo> existingUserOpt = userInfoRepository.findById(userInfo.getId());
        if (existingUserOpt.isPresent()) {
            UserInfo existingUser = existingUserOpt.get();
            existingUser.setAssociateId(userInfo.getAssociateId());
            existingUser.setUserName(userInfo.getUserName());
            existingUser.setEmail(userInfo.getEmail());
            existingUser.setContactNo(userInfo.getContactNo());
            // Add other fields as necessary
            return userInfoRepository.save(existingUser);
        } else {
            throw new UsernameNotFoundException("User not found with id: " + userInfo.getId());
        }
    }

}