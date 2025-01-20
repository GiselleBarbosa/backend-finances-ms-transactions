package br.com.barbosa.client;

import br.com.barbosa.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-finances-users", url = "localhost:8100")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable String id);
}