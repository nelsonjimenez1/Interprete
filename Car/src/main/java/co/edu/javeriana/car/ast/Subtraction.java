package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class Subtraction implements ASTNode
{
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Subtraction(ASTNode operand1, ASTNode operand2)
	{
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		try
		{
			return (double)operand1.execute(symbolTable, pila, null) - (double)operand2.execute(symbolTable, pila, null);
		}
		catch (Exception e)
		{
			int x = (int)operand1.execute(symbolTable, pila, null);
			int y = (int)operand2.execute(symbolTable, pila, null);
			return x - y;
		}		
	}
}
