import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Assignment4q1Test {
    @Test
    public void idTest(){
        MathematicalOperation m = new MathematicalOperation() ; 
        assertEquals(60, m.findLCM(12, 15));
        assertEquals(1, m.findLCM(1, 1));
        
        
    }

    @Test
    public void idTest1(){
        MathematicalOperation m = new MathematicalOperation() ; 
        assertEquals(5, m.findHcf(5, 10));
        assertEquals(3, m.findHcf(12, 15));
    }
}
