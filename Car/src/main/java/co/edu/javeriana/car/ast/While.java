package co.edu.javeriana.car.ast;
import java.util.*;

public class While implements ASTNode
{
	private ASTNode condition;
	private List <ASTNode> body;
	
	public While(ASTNode condition, List<ASTNode> body) 
	{
		this.condition = condition;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		Map symbolTableP = new HashMap<String, Object>();
		pila.add(symbolTableP);
		
		while ((boolean)condition.execute(symbolTableP, pila, null))
		{
			for (ASTNode n: body)
			{
				n.execute(symbolTableP, pila, null);
			}	
		}
		
		pila.remove(pila.size()-1);
		return null;
	}
}
