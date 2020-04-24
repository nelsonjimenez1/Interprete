package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;
import co.edu.javeriana.car.Car;

public class TurnR implements ASTNode
{
	private Car car;
	private ASTNode data;
	
	public TurnR(ASTNode data, Car car) 
	{
		this.data = data;
		this.car = car;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{	
		try
		{
			car.right((int)data.execute(symbolTable, pila, null));
		}
		catch (ClassCastException e)
		{
			double d = (double)data.execute(symbolTable, pila, null);
			float f = (float)d;
			car.right(f);
		}
		return null;
	}
}
