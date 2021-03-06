package com.formacionbdi.springboot.app.items.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier(value = "serviceFeign")
	private ItemService itemService;

	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping(value = "/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(value = "/ver/{id}/{cantidad}")
	public Item ver(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
		return new Item(new Producto(), cantidad);
	}

	@GetMapping(value = "/getConfigTexto")
	public ResponseEntity<? extends Object> getConfigTexto(@Value("${server.port}") String puerto) {
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);

		log.info(texto);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.save(producto);
	}

	@PutMapping("/modificar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto modificar(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.modificar(producto, id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}
}
