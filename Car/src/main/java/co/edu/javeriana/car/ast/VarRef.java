package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class VarRef implements ASTNode
{
	private String name;
	
	public VarRef(String name) 
	{
		this.name = name;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		Object o = symbolTable.get(name);
		
		if (o == null)
		{
			for (int i = pila.size()-1; i >= 0; i--)
			{
				o = pila.get(i).get(name);
				
				if (o != null)
				{
					break;
				}
			}
		}
		
		return o;
	}
}
