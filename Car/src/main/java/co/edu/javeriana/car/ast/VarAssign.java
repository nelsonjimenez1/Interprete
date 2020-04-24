package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class VarAssign implements ASTNode
{
	private String name;
	private ASTNode expression;
	
	public VarAssign(String name, ASTNode expression) 
	{
		this.name = name;
		this.expression = expression;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		Object o = symbolTable.get(name);
		int pos = -1;
		
		if (o == null)
		{
			for (int i = pila.size()-1; i >= 0; i--)
			{
				o = pila.get(i).get(name);
				
				if (o != null)
				{
					pos = i;
					break;
				}
			}
		}
		if(pos == -1)
		{
			symbolTable.put(name, expression.execute(symbolTable, pila, null));
		}
		else
		{
			pila.get(pos).put(name, expression.execute(symbolTable, pila, null));
		}
		
		return null;
	}
}
