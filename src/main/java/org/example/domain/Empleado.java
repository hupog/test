package org.example.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empleado {
    @Min(value = 0)
    private long id;

    @NotEmpty
    private String nombre;

    @Email(message = "Debe tener formato email valido")
    private String email;

    private Double salario;

    private boolean enActivo;

    private Genero genero;
}
