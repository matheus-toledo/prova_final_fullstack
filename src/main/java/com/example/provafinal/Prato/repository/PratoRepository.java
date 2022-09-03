package com.example.provafinal.Prato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.provafinal.Prato.entity.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PRATO_CARDAPIO WHERE id_prato = ?1", nativeQuery = true)
    void removerVinculoPrato(Long idPrato);


}
