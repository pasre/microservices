package com.formacionbdi.springboot.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	@GetMapping(value = "/listar")
	public List<Producto> listar();

	@GetMapping(value = "/ver/{id}")
	public Producto ver(@PathVariable Long id);

	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);

	@PutMapping("/modificar/{id}")
	public Producto modificar(@RequestBody Producto producto, @PathVariable Long id);

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
}
