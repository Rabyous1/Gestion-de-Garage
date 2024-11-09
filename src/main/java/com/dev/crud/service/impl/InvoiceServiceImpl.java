package com.dev.crud.service.impl;

import com.dev.crud.entity.Invoice;
import com.dev.crud.exception.InvoiceNotFoundException;
import com.dev.crud.repo.InvoiceRepository;
import com.dev.crud.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {

        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()){
            return invoice.get();
        }else {
            throw new InvoiceNotFoundException("Invoice with Id : '"+id+"' is not found");
        }
    }

    @Override
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
