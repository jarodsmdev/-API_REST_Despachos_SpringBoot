package com.citt.controller;

import com.citt.exceptions.DespachoNotFoundException;
import com.citt.persistence.entity.Despacho;
import com.citt.persistence.services.DespachoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/despachos")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @PostMapping
    public ResponseEntity<Despacho> crearDespacho(
            @RequestBody Despacho despacho){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idDespacho}")
                .buildAndExpand(despacho.getIdDespacho())
                .toUri();
        despachoService.saveDespacho(despacho);
        return ResponseEntity.created(location).body(despacho);
    }

    @PutMapping("/{idDespacho}")
    public ResponseEntity<Despacho> actualizarDespacho(
            @PathVariable Long idDespacho,
            @Valid @RequestBody Despacho despacho) throws DespachoNotFoundException {
        Despacho despachoActualizado = despachoService.updateDespacho(idDespacho, despacho);
        return ResponseEntity.ok(despachoActualizado);
    }

    @GetMapping
    public ResponseEntity<List<Despacho>> getAllDespachos() {
        return ResponseEntity.ok(despachoService.findAllDespachos());
    }

    @GetMapping("/{idDespacho}")
    public ResponseEntity<Despacho> obtenerDespacho(
            @PathVariable Long idDespacho) throws DespachoNotFoundException {
        Despacho despacho = despachoService.findById(idDespacho);
        return ResponseEntity.ok(despacho);
    }

    @DeleteMapping("/{idDespacho}")
    public ResponseEntity<Void> eliminarDespacho(@PathVariable Long idDespacho) throws DespachoNotFoundException {
        despachoService.deleteDespacho(idDespacho);
        return ResponseEntity.noContent().build();
    }
}
