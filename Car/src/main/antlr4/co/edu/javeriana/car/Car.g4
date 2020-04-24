grammar Car;
@parser::header
{
	import java.util.*;
	import co.edu.javeriana.car.ast.*; 
}
@parser::members 
{
	private List <ASTNode> body =  new ArrayList<ASTNode>();
	private List <ASTNode> funciones =  new ArrayList<ASTNode>();
	private Car car;
	private Map<String, Object> symbolTable = new HashMap<String, Object>();
	private List <Map<String, Object>> pila = new ArrayList<>();

	public CarParser(TokenStream input, Car car) 
	{
    	this(input);
    	this.car = car;
	}
	
		private ASTNode buscar(String nombre)
		{
			for (int i = 0; i < funciones.size(); i++)
			{
				Funcion f = (Funcion)funciones.get(i);
				if(nombre.equals(f.getNombre()))
				{
					return funciones.get(i);
				}	
			}
			
			return null;
		}
}
start: (sentence{body.add($sentence.node);})* 
	{
		pila.add(symbolTable);
		
		for (ASTNode n: body)
		{
			if(!(n instanceof Funcion))
			{
				n.execute(symbolTable, pila, null);	
			}
		}
	};
sentence returns[ASTNode node]: movef {$node = $movef.node;} 
	| moveb {$node = $moveb.node;}
	| turnl {$node = $turnl.node;}
	| turnr {$node = $turnr.node;}
	| set_color {$node = $set_color.node;} 
	| defv {$node = $defv.node;}
	| var_assing {$node = $var_assing.node;}
	| print {$node = $print.node;}
	| conditional {$node = $conditional.node;}
	| forsito {$node = $forsito.node;}
	| procedure {$node = $procedure.node;}
	| llamado {$node = $llamado.node;};
movef returns[ASTNode node]: MOVEF expression
	{
		$node = new MoveF($expression.node, car);			
	};
moveb returns[ASTNode node]: MOVEB expression
	{
		$node = new MoveB($expression.node, car);
	};
turnl returns[ASTNode node]: TURNL expression
	{
		$node = new TurnL($expression.node, car);
	};
turnr returns[ASTNode node]: TURNR expression
	{
		$node = new TurnR($expression.node, car);
	};
set_color returns[ASTNode node]: SETC v1=valor COMA v2=valor
	{	
		$node = new Color($v2.node, car, $v1.node);
	};
defv returns[ASTNode node]: DEFV NOMVAR (ASSING expression)?
	{
		try
		{
			$node = new VarDecl($NOMVAR.text, $expression.node);
		}
		catch (Exception e)
		{
			$node = new VarDecl($NOMVAR.text, null);
		}				
	};
var_assing returns[ASTNode node]: NOMVAR ASSING expression
	{
		$node = new VarAssign($NOMVAR.text, $expression.node);
	};
print returns[ASTNode node]: PRINT expression
	{
		$node = new Println($expression.node);
	};
conditional returns[ASTNode node]: IF PAR_OPEN expression PAR_CLOSE 
	{
		List<ASTNode> bodyT = new ArrayList<ASTNode>();
	}
	(s1=sentence {bodyT.add($s1.node);})* 
	ELSE?
	{
		List<ASTNode> elseBody = new ArrayList<ASTNode>();
	}
	(s2=sentence {elseBody.add($s2.node);})*
	ENDIF
	{	
		$node = new If($expression.node, bodyT, elseBody);
	};
forsito returns[ASTNode node]: WHILE PAR_OPEN expression PAR_CLOSE
	{
		List<ASTNode> bodyF = new ArrayList<ASTNode>();
	} 
	(s1=sentence {bodyF.add($s1.node);})*
	ENDWHILE
	{
		$node = new While($expression.node, bodyF);
	};	
procedure returns[ASTNode node]: PROC n1=NOMVAR 
	PAR_OPEN {Map<String, Object> symbolTableP = new HashMap<String, Object>();}
	(n2=NOMVAR COMA {symbolTableP.put($n2.text, new Object());})* 
	(n3=NOMVAR {symbolTableP.put($n3.text, new Object());})? 
	PAR_CLOSE 
	{
		List<ASTNode> bodyP = new ArrayList<ASTNode>();
	}
	(s1=sentence {bodyP.add($s1.node);})*
	END
	{
		$node = new Funcion(bodyP, $n1.text, symbolTableP);
		funciones.add($node);
	};
llamado returns[ASTNode node]: n1=NOMVAR 
	{
		ASTNode nodoF = buscar($n1.text);
		Funcion f = (Funcion)nodoF;
		List <ASTNode> parametros =  new ArrayList<ASTNode>();
	}
	PAR_OPEN (n2=expression COMA {parametros.add($n2.node);})*
	(n3=expression {parametros.add($n3.node);})? 
	PAR_CLOSE
	{
		$node =  new Llamado(f, parametros);
	};
expression returns [ASTNode node]:
	t1=expressionBoolean {$node = $t1.node;}; 
expressionBoolean returns [ASTNode node]:
	t1=expressionAux {$node = $t1.node;} 
	(OR t2=expressionAux {$node = new EvaluarCond($node, $t2.node, "|");})*;
expressionAux returns [ASTNode node]:
	t1=expressionAritmetica {$node = $t1.node;} 
	(AND t2=expressionAritmetica {$node = new EvaluarCond($node, $t2.node, "&");})*;
expressionAritmetica returns[ASTNode node]:
	t1= factorAritmetica {$node = $t1.node;} 
	(PLUS t2=factorAritmetica {$node = new Addition($node, $t2.node);}
 	| MINUS t3=factorAritmetica {$node = new Subtraction($node, $t3.node);}
    | MAY t4=factorAritmetica {$node = new EvaluarCond($node, $t4.node, ">");}
 	| MAYE	t5=factorAritmetica {$node = new EvaluarCond($node, $t5.node, ">=");}
 	| MEN t6=factorAritmetica {$node = new EvaluarCond($node, $t6.node, "<");}
 	| MENE t7=factorAritmetica {$node = new EvaluarCond($node, $t7.node,"<=");}
 	| IGUAL t8=expressionAritmetica {$node = new EvaluarCond($node, $t8.node,"=");}
 	| DIFERENTE t9=expressionAritmetica {$node = new EvaluarCond($node, $t9.node,"<>");})*;	
factorAritmetica returns[ASTNode node]: 
	t1=valor {$node = $t1.node;}  
	(MULT t2=valor {$node = new Multiplication($node, $t2.node);}
	| DIV t3=valor {$node = new Division($node, $t3.node);})*;
valor returns[ASTNode node]:
	NUMERO {$node = new Constant(Integer.parseInt($NUMERO.text));} 
	| DECIMAL {$node = new Constant(Double.parseDouble($DECIMAL.text));}
	| BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));}
	| STRING 
	{
		String s = $STRING.text;
		String res = s.substring(1,s.length()-1);  
		$node = new Constant(res);
	}
	| COLOR {$node = new Constant($COLOR.text);}
	| NOMVAR {$node = new VarRef($NOMVAR.text);}
	| PAR_OPEN e1=expression{$node = $e1.node;} PAR_CLOSE
	| NOT PAR_OPEN e2=expressionBoolean{$node = new EvaluarCond($node, $e2.node,"!");} PAR_CLOSE;

PRINT: 'echo';

MOVEF: 'move_fw';
MOVEB: 'move_bk';
TURNL: 'turn_lt';
TURNR: 'turn_rt';
SETC: 'set_rgba';

IF: 'if';
ELSE: 'else';
ENDIF: 'endif';

WHILE: 'while';
ENDWHILE: 'endwhile';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

AND: 'and';
OR: 'or';
NOT: 'not';

MAY: '>';
MEN: '<';
MAYE: '>=';
MENE: '<=';
IGUAL: '=';
DIFERENTE: '<>';

PROC: 'proc';
END: 'end';

PAR_OPEN: '(';
PAR_CLOSE: ')';
COMA: ',';

DECIMAL: [0-9]*'.'[0-9]+;
NUMERO: [0-9]+;
BOOLEAN: 'true'|'false';
STRING: ('"')~["''"]*('"');
COLOR:  '#'[0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f];

DEFV: 'def_var';
NOMVAR: [a-zA-Z_][a-zA-Z0-9_]*;
ASSING: ':=';

WS: [ \t\r\n]+ -> skip;