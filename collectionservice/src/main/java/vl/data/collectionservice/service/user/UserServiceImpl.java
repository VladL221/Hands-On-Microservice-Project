package vl.data.collectionservice.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.beans.AppUserBean;
import vl.data.collectionservice.beans.TodoBean;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;
import vl.data.collectionservice.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final AppUserRepository appUserRepository;


    @Override
    public List<AppUserBean> getUsers() {
        try {
            List<AppUser> appUsers = appUserRepository.findAll();
            List<AppUserBean> appUserBeans = appUsers.stream().map(AppUtils::appUserEntityToDto).collect(Collectors.toList());
            return appUserBeans;
        }catch (Exception e){
            log.error("Exception: {}", e.getMessage());
            return null;
        }
    }


    @Override
    public AppUser saveUser(AppUser appUser) {
        try {
            return appUserRepository.save(appUser) ;
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }


    @Override
    public AppUser updateUser(AppUser appUser) {
        try {
            appUser.setId(appUserRepository.findByUsername(appUser.getUsername()).getId());
            appUserRepository.save(appUser);
            return appUser;
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }

    }

    @Override
    public AppUserBean getAppUserById(Long id) {
        try {
            AppUserBean appUserBean = AppUtils.appUserEntityToDto(appUserRepository.findById(id).orElse(null));
            return appUserBean;
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return null;
        }
    }


}
