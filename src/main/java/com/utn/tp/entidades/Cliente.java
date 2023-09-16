package com.utn.tp.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cliente extends BaseEntidad {

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    //ONE TO MANY UNDIRECCIONAL
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_domicilio_id")


    @Builder.Default

    private List<Domicilio> domicilios = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){
        domicilios.add(domi);
    }

    public void mostrarDomicilio(){
        System.out.println("Domicilios de " + nombre + " " + apellido);

        for (Domicilio domicilio: domicilios){

            System.out.println("Calle " + domicilio.getCalle() + "-" + "numero " +domicilio.getNumero() + "Localidad " + domicilio.getLocalidad());
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_pedidos_id")
    @Builder.Default

    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido pedid){
        pedidos.add(pedid);
    }

    public void mostrarPedido(){
        System.out.println("Pedidos del cliente " + nombre);

        for (Pedido pedido: pedidos){
            System.out.println("Estado: " + pedido.getEstado() + " " +"Tipo de envio: " +" " +pedido.getTipoEnvio()+" "+"Fecha: " + pedido.getFecha());
            System.out.println("TOTAL: " + pedido.getTotal());
        }
    }
}
