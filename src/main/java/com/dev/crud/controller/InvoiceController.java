package com.dev.crud.controller;

import com.dev.crud.entity.Invoice;
import com.dev.crud.exception.InvoiceNotFoundException;
import com.dev.crud.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    // Home page (can be removed for API-only services)
    @GetMapping("/")
    public String showHomePage() {
        return "homePage"; // For testing purposes, remove if not required
    }

    // Register invoice page (can be removed for API-only services)
    @GetMapping("/register")
    public String showRegistration() {
        return "registerInvoicePage"; // For testing purposes, remove if not required
    }

    // Save invoice (returns a response message in JSON format)
    @PostMapping("/save")
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return new ResponseEntity<>("Invoice with ID: '" + invoice.getId() + "' saved successfully!", HttpStatus.CREATED);
    }

    // Get all invoices (returns a list of invoices as JSON)
    @GetMapping("/getAllInvoices")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // Edit invoice (returns the invoice if found, else an error message)
    @GetMapping("/edit")
    public ResponseEntity<Invoice> getInvoiceByRequestParam(@RequestParam Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Update invoice (returns a response message in JSON format)
    @PostMapping("/update")
    public ResponseEntity<String> updateInvoice(@RequestBody Invoice invoice) {
        invoiceService.updateInvoice(invoice);
        return new ResponseEntity<>("Invoice with ID: '" + invoice.getId() + "' updated successfully!", HttpStatus.OK);
    }

    // Delete invoice (returns a response message in JSON format)
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteInvoice(@RequestParam Long id) {
        try {
            invoiceService.deleteInvoiceById(id);
            return new ResponseEntity<>("Invoice with ID: '" + id + "' deleted successfully!", HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get a single invoice by ID (returns a single invoice as JSON)
    @GetMapping("/invoice/{id}")
    public ResponseEntity<Invoice> getInvoiceByPathVariable(@PathVariable("id") Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
