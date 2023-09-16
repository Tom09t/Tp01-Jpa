package com.utn.tp.entidades;

import com.utn.tp.enumeraciones.FormaDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura extends BaseEntidad {

    private int numero;

    private Date fecha;

    private double descuento;

    private FormaDePago formaPago;

    private int total;

    @OneToOne(mappedBy = "factura")
    private Pedido pedido;

}
