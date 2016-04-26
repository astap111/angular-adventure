package org.itechart.controller;

import org.itechart.entity.jpa.LifecycleStatus;
import org.itechart.entity.jpa.user.UserRole;
import org.itechart.entity.mongo.Consignment;
import org.itechart.service.mongo.ConsignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/consignments")
public class ConsignmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsignmentController.class);

    @Autowired
    private ConsignmentService consignmentService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN', 'ROLE_DISPATCHER')")
    public Page<Consignment> getConsignments(@RequestParam int page, @RequestParam int pageSize, HttpServletRequest request) {
        if (request.isUserInRole(UserRole.ROLE_SYSTEM_ADMIN.name())) {
            return consignmentService.findAll(page, pageSize);
        } else if (request.isUserInRole(UserRole.ROLE_DISPATCHER.name())) {
            return consignmentService.findAll(page, pageSize, LifecycleStatus.REGISTERED);
        }
        throw new AuthenticationException("Unauthorized Access") {
        };
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Consignment getConsignment(@PathVariable Long id) {
        return consignmentService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createConsignment(@RequestBody Consignment consignment) {
        consignmentService.save(consignment);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateConsignment(@RequestBody Consignment consignment) {
        consignmentService.update(consignment);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
