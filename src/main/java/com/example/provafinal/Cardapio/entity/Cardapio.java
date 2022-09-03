package com.example.provafinal.Cardapio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.provafinal.Prato.entity.Prato;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CARDAPIO")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cardapio")
    private Long id;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRATO_CARDAPIO",joinColumns = @JoinColumn(name = "id_cardapio"), inverseJoinColumns = @JoinColumn(name = "id_prato"))
    private List<Prato> pratos;

    @NotNull(message = "O cardápio deve ter uma disponibilidade!")
    @Column(name = "disponibilidade", nullable = false)
    private Boolean disponibilidade;

    @Length(max = 300, message = "O cardápio deve ter no máximo 300 caracteres!")
    @Column(name = "descricao", length = 300)
    private String descricao;
    
}
