package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public class EvaluarCond implements ASTNode
{
	private ASTNode operand1;
	private ASTNode operand2;
	private String operador;
	
	public EvaluarCond(ASTNode operand1, ASTNode operand2, String operador)
	{
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operador = operador;
	}
	
	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		if (operador.equals(">"))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) > (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) > (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals(">="))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) >= (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) >= (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals("<"))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) < (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) < (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals("<="))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) <= (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) <= (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals("="))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) == (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) == (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals("<>"))
		{
			try
			{
				if ((double)operand1.execute(symbolTable, pila, null) != (double)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
			catch (Exception e)
			{
				if ((int)operand1.execute(symbolTable, pila, null) != (int)operand2.execute(symbolTable, pila, null))
				{
					Object aux = true;
					return aux;
				}
				else
				{
					Object aux = false;
					return aux;
				}
			}
		}
		else if (operador.equals("&"))
		{
			if ((boolean)operand1.execute(symbolTable, pila, null) && (boolean)operand2.execute(symbolTable, pila, null))
			{
				Object aux = true;
				return aux;
			}
			else
			{
				Object aux = false;
				return aux;
			}
		}
		else if (operador.equals("|"))
		{
			if ((boolean)operand1.execute(symbolTable, pila, null) | (boolean)operand2.execute(symbolTable, pila, null))
			{
				Object aux = true;
				return aux;
			}
			else
			{
				Object aux = false;
				return aux;
			}
		}
		else if (operador.equals("!"))
		{
			if (!(boolean)operand2.execute(symbolTable, pila, null))
			{
				Object aux = true;
				return aux;
			}
			else
			{
				Object aux = false;
				return aux;
			}
		}
		return null;
	}
}
