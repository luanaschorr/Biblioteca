Projeto de sistema de biblioteca.
Linguagem de programação: Java
SGBD - PostgreSQL

Desenvolvedores:
Luana Schorr
Lucas A. Caetano
Henrique Dalmazo

---- Orientações gerais ----

Versão do java utilizada: 17
Versão do postgree: 16

Observação: o requisito 6.3.4 não foi atendido, pois nosso banco de dados contém uma tabela de generalização chamada "tb_exemplares", que se relaciona diretamente com as tabelas "tb_livros" e "tb_periodicos". Sendo assim, o SELECT feito neste ponto do código, abrange tanto os livros, quanto periódicos.
A listagem de 