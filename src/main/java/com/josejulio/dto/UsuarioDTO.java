package com.josejulio.dto;

import org.hibernate.validator.constraints.Length;
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
public class UsuarioDTO {
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 50)
    private String nome;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 20)
    private String senha;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 20)
    private String confirmacaoSenha;
}
