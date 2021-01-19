package com.curso;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Por qué varios tipos de pruebas.
 * La idea es que cuando una prueba falle, sepa identificar univocamente, qué falla
 * Las pruebas unitarias, deben SOLO probar 
 * funciones muy simples.
 *   - Mocks (Mockito):
 *          - Simular un servicio WEB :8081/miservweb1 <<<< json (PREFIJADO)
 *          - Simular una BD :8081/miservweb1 <<<< DATOS (PREFIJADO)
 * Las pruebas de integración:
 *   - Base de datos
 *   - EJB: Tome los datos de la sesión correctamente desde WAS
 */
public class CalculadoraTest 
{

    @Test
    public void testSumarPositivos()
    {
        int resultadoEsperado=14;
        int resultado=Calculadora.suma(9,5);
        assertEquals(resultadoEsperado,resultado );
    }

    @Test
    public void testSumarNegativos()
    {
        int resultadoEsperado=4;
        int resultado=C
        alculadora.suma(9,-5);
        assertEquals(resultadoEsperado,resultado );
    }
    @Test
    public void testSumarCero()
    {
        int resultadoEsperado=9;
        int resultado=Calculadora.suma(9,0);
        assertEquals(resultadoEsperado,resultado );
    }
    @Test
    public void testSumarTodoNegativos()
    {
        int resultadoEsperado=-14;
        int resultado=Calculadora.suma(-9,-5);
        assertEquals(resultadoEsperado,resultado );
    }
}
