package lexico;
import static lexico.Tokens.*;
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
while {lecturaLexica=yytext(); return Reservada;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {lecturaLexica=yytext();return Operador;}
"+" {lecturaLexica=yytext();return Operador;}
"%" {lecturaLexica=yytext();return Operador;}
"(" {lecturaLexica=yytext();return Delimitador;}
")" {lecturaLexica=yytext();return Delimitador;}
"-" {lecturaLexica=yytext();return Operador;}
">" {lecturaLexica=yytext();return Operador;}
"<" {lecturaLexica=yytext();return Operador;}
"*" {lecturaLexica=yytext();return Operador;}
"/" {lecturaLexica=yytext();return Operador;}
"{" {lecturaLexica=yytext();return Delimitador;}
"}" {lecturaLexica=yytext();return Delimitador;}
{L}({L}|{D})* {lecturaLexica=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lecturaLexica=yytext(); return Numero;}
 . {return ERROR;}
