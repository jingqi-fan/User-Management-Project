package com.example.webbackend1.controller;

import com.example.webbackend1.entity.*;
import com.example.webbackend1.service.AttendeeService;
import com.example.webbackend1.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
@CrossOrigin
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("")
    public AttendeeResult insertTenant(@RequestBody Tenant tenant) {
        return tenantService.insertTenant(tenant);
    }

    @DeleteMapping("{id}")
    public AttendeeResult deleteTenant(@PathVariable(value = "id") Integer id) {
        return tenantService.deleteTenant(id);
    }

    @DeleteMapping("")
    public AttendeeResult deleteTenantMore(@RequestBody List<Integer> ids) {
        return tenantService.deleteTenantMore(ids);
    }

    @PutMapping("")
    public AttendeeResult modifyTenant(@RequestBody Tenant tenant) {
        return tenantService.modifyTenant(tenant);
    }

    @PostMapping("{index}/{size}")
    public AttendeeResult findTenantList(@PathVariable(value = "index") Integer index,
                                           @PathVariable(value = "size") Integer size,
                                           @RequestBody(required = false) AttendeeQuery attendeeQuery) {
        return tenantService.findTenantList(index, size, attendeeQuery);
    }


    @PostMapping("{id}")
    public AttendeeResult getTenantInfo(@PathVariable(value = "id") Integer id) {
        return tenantService.getTenantInfoById(id);
    }



}

