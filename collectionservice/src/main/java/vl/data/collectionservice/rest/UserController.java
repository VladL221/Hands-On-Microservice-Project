package vl.data.collectionservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vl.data.collectionservice.beans.AppUserBean;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.service.user.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    // To fetch all users http://localhost:8082/collection-service/api/users
    @GetMapping
    public ResponseEntity<List<AppUserBean>> getUsers(){
        try {
            return ResponseEntity.status(200).body(userService.getUsers());
        }catch (Exception e){
            log.error("Exception: {}",e.getMessage());
        }
        return null;
    }


    // To fetch 1 user by id http://localhost:8082/collection-service/api/users/1
    @GetMapping("/{userId}")
    public ResponseEntity<AppUserBean> getUser(@PathVariable Long userId){
        try {
            return ResponseEntity.status(200).body(userService.getAppUserById(userId));
        }catch (Exception e){
            log.error("Expcetion: {}", e.getMessage());
        }
        return null;
    }

    // For posting a new user http://localhost:8082/collection-service/api/users
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser){
     try {
         return ResponseEntity.status(201).body(userService.saveUser(appUser));
     } catch (Exception e){
         log.error("Excpetion: {}" , e.getMessage());
     }
     return null;
    }

    // For updating 1 user http://localhost:8082/collection-service/api/users/update
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody AppUser appUser){
        try {
            return ResponseEntity.status(201).body(userService.updateUser(appUser));
        }catch (Exception e){
            log.error("Exceptioopn: {}", e.getMessage());
        }
        return null;
    }



}
