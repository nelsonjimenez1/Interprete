package co.edu.javeriana.car.ast;
import java.util.Map;
import co.edu.javeriana.car.Car;

public class MoveB implements ASTNode
{
	private Car car;
	private ASTNode data;
	
	public MoveB(ASTNode data, Car car) 
	{
		this.data = data;
		this.car = car;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) 
	{
		try
		{
			car.backwards((int)data.execute(symbolTable));
		}
		catch (ClassCastException e)
		{
			double d = (double)data.execute(symbolTable);
			float f = (float)d;
			car.backwards(f);
		}
		return null;
	}
}
