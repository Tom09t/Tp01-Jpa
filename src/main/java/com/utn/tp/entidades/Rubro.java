package com.utn.tp.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Rubro extends BaseEntidad{

    private String denominacion;

    //ONE TO MANY UNDIRECCIONAL
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default

    private List<Producto> productos = new ArrayList<>();

    public void agregarProductos(Producto produ){
        productos.add(produ);
    }

    public void mostrarProductos(){
        System.out.println("Productos del rubro " + denominacion);

        for (Producto producto: productos){
            System.out.println("Producto: " + producto.getDenominacion() +" "+ "Tipo: " + producto.getTipo());
        }
    }
}
