package ru.ydubovitsky.inventoryservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import ru.ydubovitsky.inventoryservice.model.Inventory;
import ru.ydubovitsky.inventoryservice.repository.InventoryRepository;

import java.util.List;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadMockData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory iphone = Inventory.builder()
					.skuCode("iphone")
					.count(10)
					.build();

			Inventory samsung = Inventory.builder()
					.skuCode("samsung")
					.count(20)
					.build();

			inventoryRepository.saveAll(List.of(iphone, samsung));
			log.warn("Test inventories created");
		};
	}
}
