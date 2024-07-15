package com.example.customermanagementsystem.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
public class SyncController {
    @Autowired
    private SyncService syncService;

    @PostMapping
    public void sync() {
        syncService.syncCustomers();
    }
}
