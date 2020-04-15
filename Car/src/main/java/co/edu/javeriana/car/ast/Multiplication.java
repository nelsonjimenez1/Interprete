package co.edu.javeriana.car.ast;
import java.util.Map;

public class Multiplication implements ASTNode
{
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Multiplication(ASTNode operand1, ASTNode operand2)
	{
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public Object execute(Map<String, Object> symbolTable) 
	{
		try
		{
			return (double)operand1.execute(symbolTable) * (double)operand2.execute(symbolTable);
		}
		catch (Exception e)
		{
			return (int)operand1.execute(symbolTable) * (int)operand2.execute(symbolTable);
		}
	}
}
