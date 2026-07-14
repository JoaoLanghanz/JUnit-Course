import com.langhanz.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTest {

    private Calculadora calc = new Calculadora();

    @Test
    public void testSomar(){
 
        Assertions.assertTrue(calc.soma(2,3) == 5);
        Assertions.assertEquals(5, calc.soma(2,3));
    }

    @Test
    public void assertivas(){

        Assertions.assertEquals("Casa", "Casa");
        Assertions.assertNotEquals("Casa", "casa");
        Assertions.assertTrue("casa".equalsIgnoreCase("Casa"));
        Assertions.assertTrue("Casa".endsWith("sa"));
        Assertions.assertTrue("Casa".startsWith("Ca"));

        List<String> s1 = new ArrayList<>();
//        s1.add("a");
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        Assertions.assertEquals(s1, s2);
        Assertions.assertSame(s1,s1);
        Assertions.assertNull(s3);
        Assertions.assertNotNull(s1);
//        Assertions.fail();

    }

    @Test
    public void deveRetornarNumeroInteiroNaDivisao(){

        float resultado = calc.dividir(6,2);
        Assertions.assertEquals(3, resultado);
    }

    @Test
    public void deveRetornarNumeroNegativoNaDivisao(){

        float resultado = calc.dividir(6, -2);
        Assertions.assertEquals(-3, resultado);
    }

    @Test
    public void deveRetornarNumeroDecimalNaDivisao(){

        float resultado = calc.dividir(10, 3);
//        Assertions.assertEquals(3.33, resultado);
        Assertions.assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao(){

        float resultado = calc.dividir(0,2);
        Assertions.assertEquals(0, resultado);
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUnit4(){
        try{
            float resultado = 10 / 0;
            Assertions.fail("Deveria ter lançado uma exceção na divisão.");
        }catch (ArithmeticException e){
            Assertions.assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUnit5(){
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            float resultado = 10 / 0;
        });
        Assertions.assertEquals("/ by zero", exception.getMessage());
    }
}
