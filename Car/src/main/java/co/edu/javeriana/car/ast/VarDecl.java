package co.edu.javeriana.car.ast;
import java.util.Map;

public class VarDecl implements ASTNode
{
	private String name;
	private ASTNode expression;
	
	public VarDecl(String name, ASTNode expression) 
	{
		this.name = name;
		this.expression = expression;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) 
	{
		if (expression == null)
		{
			symbolTable.put(name, new Object());
		}
		else
		{
			symbolTable.put(name, expression.execute(symbolTable));
		}

		return null;
	}
}
