package com.utn.tp.entidades;

import com.utn.tp.enumeraciones.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class Producto extends BaseEntidad {


    private Tipo tipo;

    private int tiempoEstimadoCocina;

    private String denominacion;

    private double precioVenta;

    private double precioCompra;

    private int stockActual;

    private int stockMinimo;

    private String unidadMedida;

    private String receta;

}
