package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.Token;
import com.github.dronezcc.riser.gui.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MailTokenService {

    @Autowired
    TokenRepository tokenRepository;


    public UUID generateToken(long userId){

        //Check if token already exists

       List<Token> tokenList =  tokenRepository.findByUserIdAndUsed(userId, false);

       if(tokenList.size() > 0){
           tokenList.forEach(token -> {
               token.setUsed(true);
               tokenRepository.save(token);
           });
       }

       // Create a new token

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        Token token = new Token();
        token.setUserId(userId);
        token.setValidTo(tomorrow.toString());
        Token savedToken = tokenRepository.save(token);

        return savedToken.getIdentity();

    }

    public boolean isTokenValid(String token) {
        UUID lToken = UUID.fromString(token);
        Token t =  tokenRepository.findByIdentity(lToken);

        LocalDateTime ldt = LocalDateTime.parse(t.getValidTo());
        boolean isDateValid = ldt.isAfter(LocalDateTime.now());

        return t!=null && !t.isUsed() && isDateValid;
    }

    public Token findTokenById(String token) {
        UUID uuid = UUID.fromString(token);
        return tokenRepository.findByIdentity(uuid);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Iterable<Token> findAll() {
        return tokenRepository.findAll();
    }
}
