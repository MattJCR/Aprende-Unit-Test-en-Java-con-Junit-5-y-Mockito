package mokito;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest {
    //Se le insertan los Mock
    @InjectMocks
    private Add add;
    //Usado como Mock
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Captor
    private ArgumentCaptor<Integer> captor;

    @BeforeEach
    public void setup(){
        //Inicializa los mocks y los injecta
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){

        //Mockeamos para cuando el valor sea 3 devuelva true
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        System.out.println("checkNumber 3: " + checkNumber);
        assertEquals(true,validNumber.check(3));

        when(validNumber.check(2)).thenReturn(true);
        checkNumber = validNumber.check(2);
        System.out.println("checkNumber 2: " + checkNumber);
        assertEquals(true,validNumber.check(2));

        checkNumber = validNumber.check(5);
        System.out.println("checkNumber 5: " + checkNumber);
        assertEquals(false,validNumber.check(5));
    }

    @Test
    public void addMockExceptionTest(){
        when(validNumber
                .checkZero(0))
        .thenThrow(
                new ArithmeticException("No podemos aceptar 0")
        );
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addRealMethodTest(){
        when(validNumber
                .check(3))
        .thenCallRealMethod();
        assertEquals(true,validNumber.check(3));
        when(validNumber
                .check("3"))
        .thenCallRealMethod();
        assertEquals(false,validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){
        //Devuelve una respuesta predefinida
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };
        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14,add.addInt(7.7,7.7));
    }

    //Arrange
    //Act
    //Assert
    @Test
    public void patterTest(){
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        //Assert
        assertEquals(9,result);
    }

    //Given
    //When
    //Then
    @Test
    public void patterBDDTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9,result);
    }

    @Test
    public void argumentMatcherTest(){
        //Given
        given(validNumber.check(anyInt())).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9,result);
    }

    @Test
    public void addPrintTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //add.addPrint(4,4);
        //Then
        verify(validNumber).check(4);
        //verify(validNumber,times(2)).check(4);
        verify(validNumber,never()).check(6);
        verify(validNumber,atLeast(1)).check(5);
        verify(validNumber,atMost(1)).check(5);

        verify(print).soutMessage(9);
        verify(print,never()).showError();

    }

    @Test
    public void captorTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(print).soutMessage(captor.capture());
        assertEquals(captor.getValue().intValue(),9);
    }

    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        //Valida aun sin moquear el metodo size()
        assertEquals(2,spyList.size());
    }

    @Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        //No valida a no ser que se moquee el metodo size()
        given(mockList.size()).willReturn(2);
        assertEquals(2,mockList.size());
    }


}