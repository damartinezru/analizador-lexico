package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lecturaLexica;
%}
%%
int |
if |
else |
while {lecturaLexica=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return Igual;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
{L}({L}|{D})* {lecturaLexica=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lecturaLexica=yytext(); return Numero;}
 . {return ERROR;}
