package com.utn.tp.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetallePedido extends BaseEntidad {

    private int cantidad;

    private double subtototal;
    @ManyToOne
    @JoinColumn(name = "detallePedido_id")

    private Producto producto;
}
