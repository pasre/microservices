package com.formacionbdi.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.items.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.items.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest productoClienteRest;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = productoClienteRest.listar();
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Producto producto = productoClienteRest.ver(id);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return productoClienteRest.crear(producto);
	}

	@Override
	public Producto modificar(Producto producto, Long id) {
		return productoClienteRest.modificar(producto, id);
	}

	@Override
	public void delete(Long id) {
		productoClienteRest.eliminar(id);
	}

}
