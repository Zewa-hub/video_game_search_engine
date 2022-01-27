package fr.lernejo.search.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @GetMapping("/api/hello")
    HelloMessage sayHello() {
        return new HelloMessage("Hello random wanderer of the internetS (plural).");
    }

    record HelloMessage(String message) {
    }
}
