package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator calculator;

    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(4,4,8),
                Arguments.of(0,4,4),
                Arguments.of(1,4,5)
        );
    }

    @ParameterizedTest(name="Test-{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(int a, int b, int sum){
        assertEquals(sum,calculator.add(a,b));
    }

    @BeforeAll
    public static void beforeAllTests(){
        calculator = new Calculator();
        System.out.println("@BeforeAll -> beforeAllTests()");
    }
    @BeforeEach
    public void setup (){
        //calculator = new Calculator();
        //System.out.println("@BeforeEach -> setup()");
    }

    @AfterEach
    public void  tearDown(){
        //calculator = null;
        //System.out.println("@AfterEach -> tearDown()");
    }

    @AfterAll
    public static void  afterAllTests(){
        calculator = null;
        System.out.println("@AfterAll -> afterAllTests()");
    }

    @Test
    public void calculatorNotNullTest(){
        assertNotNull(calculator, "Calculator is null.");
        System.out.println("@Test -> calculatorNotNullTest()");
    }

    @Test
    public void addAssertTest(){
        int result = 30;
        assertEquals(result,calculator.add(10,20));
        System.out.println("@Test -> addAssertTest()");
    }

    @Test
    public void assertTypesTest(){
        Integer n0 = null;
        Integer n1 = 1,n2 = 2;
        Integer n3 = n1;

        //Equals
        assertEquals("matt","matt");
        assertNotEquals("sara","Sara");
        //Delta is error range
        assertEquals(1,1.4,0.5);
        assertNotEquals(1,1.6,0.5);

        //Conditions
        assertTrue(1 == 1);
        assertFalse(1 == 2);

        //Nulls
        assertNull(n0);
        assertNotNull(n1);

        //Same objects
        assertSame(n1,n3);
        assertNotSame(n1,n2);
    }

    @Test
    public void AddValidInputValidExpectedTest(){
        assertEquals(11,calculator.add(7,4));
    }
    @Disabled
    @Test
    public void SubtractValidInputValidExpectedTest(){
        assertEquals(3,calculator.subtract(7,4));
    }

    @Test
    public void DivideValidInputValidExpectedTest(){
        assertEquals(2,calculator.divide(4,2));
    }

    @Test
    public void DivideInValidInputValidExpectedExeptionTest(){
        assertThrows(ArithmeticException.class,() -> calculator.divide(2,0),"Expected Divide by Zero");
    }
    @DisplayName("Method divide -> Funciona")
    @Test
    public void DivideValidInputValidExpectedWithNameTest(){
        assertEquals(2,calculator.divide(4,2));
    }

    @Test
    public void addAssertAllTest(){
        assertAll(
                () -> assertEquals(32,calculator.add(12,20)),
                () -> assertEquals(31,calculator.add(11,20)),
                () -> assertEquals(30,calculator.add(10,20))
        );
    }

    @Nested
    class AddTest {
        @Test
        public void AddPositiveTest(){
            assertEquals(30,calculator.add(15,15));
        }
        @Test
        public void AddNegativeTest(){
            assertEquals(-30,calculator.add(-15,-15));
        }
        @Test
        public void AddZeroTest(){
            assertEquals(0,calculator.add(0,0));
        }
    }

    @Test
    public void timeOutTest(){
        //assertTimeout(Duration.ofMillis(999),() -> calculator.longTaskOperation());
        assertTimeout(Duration.ofMillis(2000),() -> calculator.longTaskOperation());
    }

    @Test
    public void toFahrenheitTest(){
        assertEquals(33.8,calculator.toFahrenheit(1),0.1);
    }

}