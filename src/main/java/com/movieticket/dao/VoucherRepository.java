package com.movieticket.dao;

import com.movieticket.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {


    Optional<Voucher> findByCode(String code);
}
