package co.edu.javeriana.car.ast;
import java.util.List;
import java.util.Map;

public interface ASTNode 
{
	public Object execute(Map<String, Object> symbolTable, List <Map<String, Object>> pila, List<Object> parametros);
}
