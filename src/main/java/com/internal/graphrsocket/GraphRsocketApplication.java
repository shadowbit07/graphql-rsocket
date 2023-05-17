package com.internal.graphrsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootApplication
public class GraphRsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphRsocketApplication.class, args);
	}

}

@Controller
class EmployeeController {

	@SubscriptionMapping
	Flux<Employee> employees () {
		AtomicInteger counter = new AtomicInteger(1);
		return Flux.fromStream(Stream.generate(() -> new Employee("Employee Name: " + counter.getAndIncrement())))
				.delayElements(Duration.ofSeconds(1))
				.take(10);
	}

	@QueryMapping
	Employee employee () {
		return new Employee("Steven");
	}
}

record Employee (String name) {}
