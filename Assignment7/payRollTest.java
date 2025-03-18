package Assignment7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class payRollTest {
    Organization organization = new Organization() ;
    PayRoll payRoll = new PayRoll();
    Department tech = new Department() ; 
    Department marketing = new Department() ; 

    @Test
    public void testorganization(){
        Developer developer  = new Developer(12 , "Mahesh", 23444) ; 
        SaleManagar saleManagar = new SaleManagar(13, "Rahul", 45945) ; 
        tech.join(developer) ; 
        marketing.join(saleManagar);
        organization.addDepartment(tech) ;
        organization.addDepartment(marketing) ; 

        assertEquals(2, organization.getAllEmployees().size()) ; 
        
    }

    @Test
    public void testDepartmentAddition(){
        Developer developer  = new Developer(12 , "Mahesh", 23444) ; 
        SaleManagar saleManagar = new SaleManagar(13, "Rahul", 45945) ; 
        tech.join(developer) ; 
        marketing.join(saleManagar);

        assertEquals(1, tech.getEmployees().size());
        assertEquals(1, marketing.getEmployees().size());
    }


}
