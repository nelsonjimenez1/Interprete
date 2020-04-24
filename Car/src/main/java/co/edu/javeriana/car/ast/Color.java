package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;
import co.edu.javeriana.car.Car;

public class Color implements ASTNode
{
	private Car car;
	private ASTNode data;
	private ASTNode color;
	
	public Color(ASTNode data, Car car, ASTNode color) 
	{
		this.data = data;
		this.car = car;
		this.color = color;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		String color = String.valueOf(this.color.execute(symbolTable, pila, null));
		int red = getRojo(color);
		int green = getVerde(color);
		int blue = getAzul(color);
		
		try
		{
			int alpha = (int)data.execute(symbolTable, pila, null);
			car.color(red, green, blue, alpha*255);
		}
		catch (ClassCastException e)
		{
			double d = (double)data.execute(symbolTable, pila, null);
			float f = (float)d;
			car.color(red, green, blue, f*255);
		}
		return null;
	}
	
	private int getRojo(String rgba)
	{
		return Integer.valueOf(rgba.substring(1,3),16);
	}
	
	private int getVerde(String rgba)
	{
		return Integer.valueOf(rgba.substring(3,5),16);
	}
	
	private int getAzul(String rgba)
	{
		return Integer.valueOf(rgba.substring(5,7),16);
	}
}
