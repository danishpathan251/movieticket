package com.movieticket.services;


import com.movieticket.dao.VoucherRepository;
import com.movieticket.entities.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoucherService {

    @Autowired
    public VoucherRepository voucherRepository;

    public void updateMobile(Long id, String image) {
        Optional<Voucher> VoucherOptional = voucherRepository.findById(id);

        if (VoucherOptional.isPresent()) {
            Voucher voucher = VoucherOptional.get();
            if (image != null) {
                voucher.setImage(image);
            }

            voucherRepository.save(voucher);


        } else {
            // Handle the case where the user with the given ID is not found
        }
    }
}
