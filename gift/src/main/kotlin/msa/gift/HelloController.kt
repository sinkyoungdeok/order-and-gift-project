package msa.gift

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/")
class HelloController {

    @GetMapping
    fun hello(): Flux<String> {
        return Flux.just("hello")
    }
}