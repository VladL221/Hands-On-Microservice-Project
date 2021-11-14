package vl.data.collectionservice.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import vl.data.collectionservice.beans.AppUserBean;
import vl.data.collectionservice.entities.user.AppUser;

import java.util.List;

public interface UserService {

    List<AppUserBean> getUsers();
    AppUser saveUser(AppUser appUser);
    AppUser updateUser(AppUser appUser);
    AppUserBean getAppUserById(Long id);


}
