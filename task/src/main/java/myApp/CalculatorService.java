package myApp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ejbs.Calculator;
@Stateless
@Path("/MyCalculator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorService {
    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;
    @POST
    @Path("post")
    public Response performCalculation(Calculator calculation) 
    {
        int result = 0;
        switch (calculation.getOperation()) 
        {
            case "-":
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            case "+":
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            case "*":
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            case "/":
                if (calculation.getNumber2() == 0) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Division by zero is not allowed")
                            .build();
                }
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            default:
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Unsupported operation")
                        .build();
        }
        entityManager.persist(calculation);
        return Response.ok(result).build();
    }
    @GET
    @Path("get")
    public Response getCalculations() 
    {
        try
        {
            String simpleQuery = "SELECT c FROM Calculation c";
            TypedQuery<Calculator> query = entityManager.createQuery(simpleQuery, Calculator.class);
            return Response.ok(query.getResultList()).build();
        } 
        catch (RuntimeException err) 
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(err.getMessage())
                    .build();
        }
    }
}