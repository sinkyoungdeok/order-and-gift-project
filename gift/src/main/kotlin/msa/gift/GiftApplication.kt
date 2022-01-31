package msa.gift

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GiftApplication

fun main(args: Array<String>) {
	runApplication<GiftApplication>(*args)
}
