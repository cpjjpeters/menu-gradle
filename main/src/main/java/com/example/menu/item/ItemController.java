package com.example.menu.item;

/* carlpeters created on 20/04/2024 inside the package - com.example.menu.item */


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/menu/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    // ✨ New! GET controller methods
    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> find(@PathVariable("id") Long id) {
        Optional<Item> item = service.find(id);
        return ResponseEntity.of(item);
    }
    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Optional<Item>  postItem = Optional.ofNullable(service.create(item));
        return ResponseEntity.of(postItem);
    }

    // ✨ New! 👇 PUT definition ✨
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(
            @PathVariable("id") Long id,
            @RequestBody Item updatedItem) {

        Optional<Item> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Item created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}