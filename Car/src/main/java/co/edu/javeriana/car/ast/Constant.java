package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class Constant implements ASTNode
{
	private Object value;
	
	public Constant(Object value) 
	{
		this.value = value;	
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		return value;
	}
}
