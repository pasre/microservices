package com.formacionbdi.springboot.app.items.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.items.models.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

	public Producto save(Producto producto);

	public Producto modificar(Producto producto, Long id);

	public void delete(Long id);
}
