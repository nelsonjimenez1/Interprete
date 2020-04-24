package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class Println implements ASTNode
{
	private ASTNode data;
	
	public Println(ASTNode data) 
	{
		this.data = data;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		System.out.println(data.execute(symbolTable, pila, null));
		return null;
	}
}
