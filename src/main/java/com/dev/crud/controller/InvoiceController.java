package com.dev.crud.controller;

import com.dev.crud.entity.Invoice;
import com.dev.crud.exception.InvoiceNotFoundException;
import com.dev.crud.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "registerInvoicePage";
    }

    @PostMapping("/save")
    public String saveInvoice(
            @ModelAttribute Invoice invoice,
            Model model
    ) {
        invoiceService.saveInvoice(invoice);
        Long id = invoice.getId();
        String message = "Record with id : '" + id + "' is saved successfully!";
        model.addAttribute("message", message);
        return "registerInvoicePage";
    }

    @GetMapping("/getAllInvoices")
    public String getAllInvoices(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("list", invoices);
        model.addAttribute("message", message);
        return "allInvoicesPage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
    ) {
        String page = null;
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            model.addAttribute("invoice", invoice);
            page = "editInvoicePage";
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:getAllInvoices";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateInvoice(
            @ModelAttribute Invoice invoice,
            RedirectAttributes attributes
    ) {
        invoiceService.updateInvoice(invoice);
        Long id = invoice.getId();
        attributes.addAttribute("message", "Invoice with id: '" + id + "' is updated successfully!");
        return "redirect:getAllInvoices";
    }

    @GetMapping("/delete")
    public String deleteInvoice(
            @RequestParam Long id,
            RedirectAttributes attributes
    ) {
        try {
            invoiceService.deleteInvoiceById(id);
            attributes.addAttribute("message", "Invoice with Id : '" + id + "' is deleted successfully!");
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllInvoices";
    }

    // New method to show a single invoice
    @GetMapping("/invoice/{id}")
    public String getInvoiceById(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            model.addAttribute("invoice", invoice);
            return "singleInvoicePage"; // Name of the Thymeleaf template for a single invoice
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            return "redirect:getAllInvoices"; // Redirect if not found
        }
    }
    @GetMapping("/test")
    public String testPage() {
        return "test"; // Reference without .html extension
    }

}
