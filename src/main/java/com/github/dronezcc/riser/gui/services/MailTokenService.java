package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.Token;
import com.github.dronezcc.riser.gui.domain.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailTokenService {

    @Autowired
    TokenRepository tokenRepository;


    public Long generateToken(long userId){

        Date date = new Date();
        System.out.println( date.toString() );


        Token token = new Token();
        token.setUserId(userId);
        token.setValidTo(new Date().toString());
        Token savedToken = tokenRepository.save(token);

        return savedToken.getIdentity();

    }


    public boolean findByToken(String token) {
        Long lToken = Long.parseLong(token);
        Token t =  tokenRepository.findByIdentity(lToken);

        return t!=null;

    }
}
