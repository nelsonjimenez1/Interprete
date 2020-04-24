package co.edu.javeriana.car.ast;
import java.util.*;

public class Llamado implements ASTNode
{
	private ASTNode llamado;
	private List <ASTNode> parametros;
	
	public Llamado(ASTNode llamado, List <ASTNode> parametros) 
	{
		this.llamado = llamado;
		this.parametros = parametros;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{
		List<Object> param = new ArrayList<Object>();
		
		for (int i = 0; i < this.parametros.size(); i++)
		{
			param.add(this.parametros.get(i).execute(symbolTable, pila, null));
		}
		
		llamado.execute(symbolTable, pila, param);
		return null;		
	}	
}
