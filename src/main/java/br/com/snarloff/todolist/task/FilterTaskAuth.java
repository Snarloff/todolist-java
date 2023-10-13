package br.com.snarloff.todolist.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.snarloff.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            String encodedAuthorization = request.getHeader("Authorization").substring("Basic".length()).trim();// remove basic from the string
            byte[] decodedAuthorization = Base64.getDecoder().decode(encodedAuthorization);// decode the string
            var stringAuthorization = new String(decodedAuthorization);// convert the byte array to string

            String[] credentials = stringAuthorization.split(":");// split the string into an array
            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
                return;
            }

            var verifiedPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());

            if (!verifiedPassword.verified) {
                response.sendError(401);
                return;
            }

            request.setAttribute("idUser", user.getId());
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }


    }
}
