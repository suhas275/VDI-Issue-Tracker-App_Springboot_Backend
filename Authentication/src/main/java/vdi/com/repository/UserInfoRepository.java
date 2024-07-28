package vdi.com.repository;


import vdi.com.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.Optional;
 
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
 
    Optional<UserInfo> findByuserName(String userName);
    boolean existsByUserName(String userName); 
    boolean existsByEmail(String email); 
    UserInfo findByUserName(String username);
 
}

