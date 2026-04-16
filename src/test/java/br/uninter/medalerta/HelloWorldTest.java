package br.uninter.medalerta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

    @Test
    void deveRetornarHelloWorld() {
        String mensagem = "Hello, World!";
        assertEquals("Hello, World!", mensagem);
        System.out.println(mensagem);
    }
}
