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
	public Object execute(Map<String, Object> symbolTable) 
	{
		if ((boolean)condition.execute(symbolTable))
		{
			for (ASTNode n: body)
			{
				n.execute(symbolTable);
			}
		}
		else
		{
			for (ASTNode n: elseBody)
			{
				n.execute(symbolTable);
			}
		}
		
		return null;
	}
}
