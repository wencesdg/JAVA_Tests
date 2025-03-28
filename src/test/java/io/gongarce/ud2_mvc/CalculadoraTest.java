package io.gongarce.ud2_mvc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gonzalo
 */
public class CalculadoraTest {
    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(5, 3);
        assertEquals(8, resultado, "La suma debe ser 8");
    }
}
