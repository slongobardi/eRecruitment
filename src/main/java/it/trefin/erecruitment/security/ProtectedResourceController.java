package it.trefin.erecruitment.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ProtectedResourceController {

	@GetMapping("/protected")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String protectedResource() {
		
        return "This is a protected resource.";
    }
}