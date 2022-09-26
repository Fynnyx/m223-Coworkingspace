package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.controller.dto.LoginData;
import ch.zli.m223.model.User;
import ch.zli.m223.service.AuthorizationService;
import io.smallrye.jwt.build.Jwt;

@Path("/auth")
public class AuthoraziationController {
    @Inject
    AuthorizationService authorizationService;
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public String login(LoginData loginData) {
        User user = authorizationService.getUserByEmail(loginData.getEmail());
        if (user.getPassword().equals(loginData.getPassword())) {
            String jwt = Jwt.issuer("https://zli.ch/issuer")
                    .upn(user.getEmail())
                    .groups("user")
                    .claim("firstname", user.getFirstname())
                    .claim("lastname", user.getLastname())
                    .claim("id", user.getId())
                    .sign();
            return jwt;
        }   
        return "Hello ";
    }

}
