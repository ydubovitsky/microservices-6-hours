package ru.ydubovitsky.inventoryservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.inventoryservice.dto.InventoryResponse;
import ru.ydubovitsky.inventoryservice.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@PathVariable List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
