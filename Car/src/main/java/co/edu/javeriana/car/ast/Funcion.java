package co.edu.javeriana.car.ast;
import java.util.*;

public class Funcion implements ASTNode
{
	private List <ASTNode> body;
	private String nombre;
	private Map<String, Object> symbolTableP;
	
	public Funcion(List<ASTNode> body, String nombre,  Map<String, Object> symbolTableP) 
	{
		this.body = body;
		this.nombre = nombre;
		this.symbolTableP = symbolTableP;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros) 
	{	
		List <Map<String, Object>> pilaC =  new ArrayList<>();
		Map<String, Object> symbolTableP = new HashMap<String, Object>(this.symbolTableP);
		int i = 0;
		
		for (Map.Entry<String, Object> e : symbolTableP.entrySet()) 
		{
       		String key = e.getKey();
       		symbolTableP.put(key, parametros.get(i));
       		i++;
		}
		
		pilaC.add(pila.get(0));
		pilaC.add(symbolTableP);
		
		for (ASTNode n: body)
		{
			n.execute(symbolTableP, pilaC, null);
		}
		
		return null;		
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public Map<String, Object> getSymbolTableP()
	{
		return this.symbolTableP;
	}
	
	public void setSymbolTableP(Map<String, Object> symbolTableP)
	{
		this.symbolTableP = symbolTableP;
	}
}
