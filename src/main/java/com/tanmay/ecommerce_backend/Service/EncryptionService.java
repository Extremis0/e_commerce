package com.tanmay.ecommerce_backend.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
     @Value("${encryption.salt.rounds}")
    private  int saltRounds;

     private  String salt;

     @PostConstruct
     public  void postConstruct(){
         salt = BCrypt.gensalt(saltRounds);
     }
     public String encryptPassword(String Password){
         return BCrypt.hashpw(Password,salt);
     }
     public  boolean verifyPassword(String Password ,String hash){
         return BCrypt.checkpw(Password,hash);
     }
}
