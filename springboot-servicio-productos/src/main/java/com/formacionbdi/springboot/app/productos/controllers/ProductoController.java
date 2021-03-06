package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private Environment environment;

	@Value("${server.port}")
	private Integer port;

	@GetMapping("/listar")
	public List<Producto> listar() {
		List<Producto> productos = productoService.findAll();
		// Integer port =
		// Integer.parseInt(environment.getProperty("local.server.port"));
		productos = productos.stream().map(p -> {
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
		return productos;
	}

	@GetMapping("/ver/{id}")
	public Producto ver(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		// Integer port =
		// Integer.parseInt(environment.getProperty("local.server.port"));
		producto.setPort(port);

		/*
		 * try { Thread.sleep(2000L); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping("/modificar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto modificar(@PathVariable Long id, @RequestBody Producto producto) {
		Producto productoBBDD = productoService.findById(id);
		productoBBDD.setNombre(producto.getNombre());
		productoBBDD.setPrecio(producto.getPrecio());
		return productoService.save(productoBBDD);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void elimianr(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
