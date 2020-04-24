package co.edu.javeriana.car.ast;
import java.util.*;

public class If implements ASTNode
{
	private ASTNode condition;
	private List <ASTNode> body;
	private List <ASTNode> elseBody;
	
	public If(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) 
	{
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		Map symbolTableP = new HashMap<String, Object>();
		pila.add(symbolTableP);
		
		if ((boolean)condition.execute(symbolTableP, pila, null))
		{
			for (ASTNode n: body)
			{
				n.execute(symbolTableP, pila, null);
			}
		}
		else
		{
			for (ASTNode n: elseBody)
			{
				n.execute(symbolTableP, pila, null);
			}
		}
		
		pila.remove(pila.size()-1);
		return null;
	}
}
