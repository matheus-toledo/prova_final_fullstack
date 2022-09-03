package com.example.provafinal.Prato.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRATO")
public class Prato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prato")
    private Long id;
    
    @NotEmpty(message = "A descrição deverá ser informada!")
    @Length(max = 300, min = 10, message = "A descrição deve ter de 10 a 300 caracteres!")
    @Column(name = "descricao", length = 300, nullable = false)
    private String descricao;

    @NotNull(message = "O prato deve ter uma disponibilidade!")
    @Column(name = "disponibilidade", nullable = false)
    private Boolean disponibilidade;

    @Column(name = "imagem")
    private String imagem;

    @NotNull(message = "O prato deve ter um preço!")
    @Column(name = "preco", nullable = false)
    private Double preco;
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Prato prato))
            return false;

        return prato.getId().equals(this.getId());
    }
}
