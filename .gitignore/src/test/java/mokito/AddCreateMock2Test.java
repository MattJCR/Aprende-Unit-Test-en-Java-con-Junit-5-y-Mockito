package mokito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AddCreateMock2Test {

    //Se le insertan los Mock
    @InjectMocks
    private Add add;
    //Usado como Mock
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setup(){
        //Inicializa los mocks y los injecta
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){
        add.add(3,2);
        Mockito.verify(validNumber).check(3);
    }

}