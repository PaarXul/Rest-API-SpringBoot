package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {


    @Test
    void calculatePrice() {

        //Configuracion de la prueba
        Book book = new Book(1L,"LIBRO","Gian",600,30.00, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        //Ejecucion de la prueba
        double price =   calculator.calculatePrice(book);

        System.out.println(price);

        assertTrue(price >0);
        //assertEquals(7,price);


    }
}