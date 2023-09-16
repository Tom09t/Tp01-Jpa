package com.utn.tp;

import com.utn.tp.entidades.*;
import com.utn.tp.enumeraciones.Estado;
import com.utn.tp.enumeraciones.FormaDePago;
import com.utn.tp.enumeraciones.Tipo;
import com.utn.tp.enumeraciones.TipoEnvio;
import com.utn.tp.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TpApplication {

	@Autowired
	RubroRepositorio rubroRepositorio;
	@Autowired
	ClienteRepositorio clienteRepositorio;


	public static void main(String[] args) {

		SpringApplication.run(TpApplication.class, args);
		System.out.println("Estoy funcionando");

	}

	@Bean
	CommandLineRunner init(RubroRepositorio rubroRepositorio, ClienteRepositorio clienteRepositorio){
		return args -> {
		//creamos un objeto tipo rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Ensalada")
					.build();
		//Creamos un objeto tipo Producto
			Producto producto1 = Producto.builder()
					.tipo(Tipo.Insumo)
					.tiempoEstimadoCocina(20)
					.denominacion("Ensalada Rusa")
					.precioCompra(300)
					.precioVenta(900)
					.stockActual(55)
					.stockMinimo(20)
					.unidadMedida("kg")
					.receta("lavamos todo y mezclamos, revolviendo durante 10 min con mayonesa")
					.build();

			Producto producto2 = Producto.builder()
					.tipo(Tipo.Insumo)
					.tiempoEstimadoCocina(20)
					.denominacion("Ensalada de tomate con lechuga")
					.precioCompra(200)
					.precioVenta(800)
					.stockActual(45)
					.stockMinimo(20)
					.unidadMedida("kg")
					.receta("lavamos todo y mezclamos, revolviendo durante 10 min con mayonesa")
					.build();

		//asociamos los productos al rubro

			rubro1.agregarProductos(producto1);
			rubro1.agregarProductos(producto2);

		//guardamos en la bd

			rubroRepositorio.save(rubro1);


			//Creamos un objeto de Cliente

			Cliente cliente = Cliente.builder()
					.nombre("Tomas")
					.apellido("Neufel")
					.email("Tomasjneuf@gmail.com")
					.telefono("2613058444")
					.build();

			//Creamos un objeto Domicilio

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Lago Olmos")
					.numero(265)
					.localidad("Godoy Cruz")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Laguna llancanelo")
					.numero(330)
					.localidad("Godoy Cruz")
					.build();

			//Asociamos los domicilios con cliente

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);


			//Creamos objeto pedido
			//configuracion fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			// Parsear la cadena en un objeto Date
			Date fecha = formatoFecha.parse(fechaString);
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.estado(Estado.Preparacion)
					.tipoEnvio(TipoEnvio.Delivery)
					.total(300)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.total(300)
					.build();

			//Asociamos un pedido a un cliente
			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);


			//Creamos objeto Factura

			Factura factura = Factura.builder()
					.numero(356)
					.fecha(fecha)
					.descuento(300)
					.total(6092)
					.formaPago(FormaDePago.MercadoPago)
					.build();


			//Asociamos una factura a un pedido

			pedido1.setFactura(factura);

			//Creamos un Detalle Pedido

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(3)
					.subtototal(3432)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(2)
					.subtototal(2660)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(6)
					.subtototal(2432)
					.build();
			DetallePedido detallePedido4 = DetallePedido.builder()
					.cantidad(2)
					.subtototal(1660)
					.build();
			//Asociamos los detalles de pedido a pedido
			pedido1.agregarDetalle(detallePedido1);
			pedido1.agregarDetalle(detallePedido2);
			pedido2.agregarDetalle(detallePedido3);
			pedido2.agregarDetalle(detallePedido4);
			//asociamos el detalle con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			detallePedido4.setProducto(producto2);



			//guardamos en la bd
			clienteRepositorio.save(cliente);

			//MOSTRAMOS LOS DATOS-Obtenemos los datos

			Cliente clienteRecuperado = clienteRepositorio.findById(cliente.getId()).orElse(null);

			if(clienteRecuperado != null){
				System.out.println("CLIENTE: " + clienteRecuperado.getNombre() + " " + clienteRecuperado.getApellido());
				System.out.println("-------------------------------------------------------");
				clienteRecuperado.mostrarDomicilio();
				System.out.println("-------------------------------------------------------");
				clienteRecuperado.mostrarPedido();
				System.out.println("-------------------------------------------------------");
			}

			//Vemos los rubros recuperandolos de la BD
			Rubro rubroRecuperado = rubroRepositorio.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Rubro: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}



		};


	};
}
