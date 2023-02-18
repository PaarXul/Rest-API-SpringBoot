package com.example.obrestdatajpa.controller;


import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import javax.persistence.Entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
// CRUD SOBRE la entidad Book
    //Atributos
    private BookRepository bookRepository;
    //Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * http://localhost:8080/api/books
     * @return
     */

    //Buscar todos los libros

    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();


    }
    //Buscar un solo libro en case de datos segun su ID

    @GetMapping("/api/books/{id}")
    @ApiOperation("Buscar un libro por la clave primaria")
    public ResponseEntity<Book> findOneById(@ApiParam("Clave primaria tipo LONG") @PathVariable Long id){

        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()){ //o es te comando -> return bookOpt.orElse(null);
            return ResponseEntity.ok(bookOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
        //Posible solucion     return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Crear un nuevo libro en base de datos

    /**
     *crear un nuevo libro en base de datos
     * @param book
     * @param headers
     * @return
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        //Guardar libros resividos por
        System.out.println(headers.get("User-Agent"));

        if(book.getId() != null){
            log.warn("tryyin to log");
            return ResponseEntity.badRequest().build();
        }
       Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //Actualizar libros existente en base de datos

    //Borrar un libro en base de datos

    @PutMapping("/api/books")
    public ResponseEntity<Book> update (@RequestBody Book book, @RequestHeader HttpHeaders headers){

        if (book.getId() == null ){
            log.warn("Tryging to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())){
            log.warn("Tryging to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if (!bookRepository.existsById(id)){
            log.warn("Tryging to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteALL(){

        log.info("Ejecutando metodo de borrar todo");
        bookRepository.deleteAll();
        return  ResponseEntity.noContent().build();
    }

}
