package ejbs;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Stateless
@Entity
public class Calculator 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private int number1;
    private int number2;
    private String operation;
    private int result;

      public Calculator() 
      {
      }

    public Calculator(int number1, int number2, String operation, int result) 
    {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
        this.result = result;
    }
    
    public void setId(int id) 
    {
        this.id = id;
    }

    public int getId() 
    {
        return id;
    }

    public void setNumber1(int number1) 
    {
        this.number1 = number1;
    }

    public int getNumber1() 
    {
        return number1;
    }

    public void setNumber2(int number2) 
    {
        this.number2 = number2;
    }

    public int getNumber2() 
    {
        return number2;
    }

    public void setOperation(String operation) 
    {
        this.operation = operation;
    }
    
    public String getOperation() 
    {
        return operation;
    }

    public void setResult(int result) 
    {
        this.result = result;
    }

    public int getResult() 
    {
        return result;
    }

   
}
