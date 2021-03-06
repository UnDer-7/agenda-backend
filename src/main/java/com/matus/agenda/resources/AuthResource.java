package com.matus.agenda.resources;

import com.matus.agenda.security.JWTUtil;
import com.matus.agenda.security.UserSS;
import com.matus.agenda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    @Autowired
    JWTUtil jwtUtil;
    //Endpoint proteced por autenticação
    @RequestMapping(value="/refresh_token", method= RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        //Pega usuario logado
        UserSS user = UserService.authnticated();
        //Gera novo token com o usuario
        String token = jwtUtil.generateToken(user.getUsername());
        //Adiciona o token na resposta da requisicao
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
}
