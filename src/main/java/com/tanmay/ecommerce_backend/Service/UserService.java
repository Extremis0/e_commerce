package com.tanmay.ecommerce_backend.Service;

import com.tanmay.ecommerce_backend.Exception.UserAlreadyExistsException;
import com.tanmay.ecommerce_backend.api.model.RegistrationBody;
import com.tanmay.ecommerce_backend.model.DAO.LocalUserDAO;
import com.tanmay.ecommerce_backend.model.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private LocalUserDAO localUserDAO;

   @Autowired
    private EncryptionService encryptionService;

    public LocalUser registeruser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if ( localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                 || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent())
        {
         throw  new UserAlreadyExistsException();
        }
        LocalUser user =new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setPassword(  encryptionService.encryptPassword(registrationBody.getPassword()));

        return localUserDAO.save(user);
    }
}
