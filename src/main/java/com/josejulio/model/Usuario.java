package com.josejulio.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_usuarios")
@SequenceGenerator(name = "usuarios_seq", sequenceName = "usuarios_seq", initialValue = 1, allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usuarios_seq")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 50)
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank
    @NotNull
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 20)
    @Column(length = 20, nullable = false)
    @Getter(onMethod = @__({ @JsonIgnore }))
    @Setter(onMethod = @__({ @JsonProperty }))
    private String senha;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 20)
    @Column(length = 20, nullable = false)
    @Getter(onMethod = @__({ @JsonIgnore }))
    @Setter(onMethod = @__({ @JsonProperty }))
    private String confirmacaoSenha;
}
