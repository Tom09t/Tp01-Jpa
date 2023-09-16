package com.utn.tp.entidades;

import com.utn.tp.enumeraciones.Estado;
import com.utn.tp.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido extends BaseEntidad {


    private Estado estado;

    private Date fecha;

    private TipoEnvio tipoEnvio;

    private double total;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default

    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetalle(DetallePedido detallePedi){detallePedidos.add(detallePedi);}
    public void mostrarDetalle(){
        System.out.println("El pedido de la fecha: " + getFecha() + "de la factura nro " + factura.getNumero());

        for (DetallePedido detallePedido: detallePedidos){
            System.out.println("La cantidad es de: " + detallePedido.getCantidad());
            System.out.println("La cantidad es de: " + detallePedido.getSubtototal());
        }
    }

}
