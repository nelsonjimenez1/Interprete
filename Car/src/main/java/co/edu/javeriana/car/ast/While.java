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
	public Object execute(Map<String, Object> symbolTable) 
	{
		while ((boolean)condition.execute(symbolTable))
		{
			for (ASTNode n: body)
			{
				n.execute(symbolTable);
			}		
		}
		
		return null;
	}
}
