# Sync_Revenue

Projeto para avaliação DBC // SiCredi, codigo por Falcão.

###### Objetivos do projeto

0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.

Formato CSV:
agencia,conta,saldo,status
0101,12225-6,100.00,A
0101,12226-8,3200.50,A
3202,40011-1,-35.12,I
3202,54001-2,0.00,P
3202,00321-2,34500.00,B



###### O que foi feito

Consegui criar uma aplicação mas na maior parte do tempo tive problemas com a execução via Gradle, algumas das dependencias de JUnit que achei precisar usar foram me atrapalharam na hora de rodar o teste, mas ao fim do processo de desenvolvimento as tasks do gradle funcionam corretamente.
No quesito do codigo acabei usando a dependencia SuperCsv para processar o arquivo, na raiz do projeto foi gerado um csv com as informações passadas no desafio.
> notar que precisei fazer alterações no formato do texto do csv, uma vez que o SuperCsv usava a virgula (,) para determinar entre um elemento e outro do csv, originalmente acredito que era um ponto e virgula (;)
Foi criada a função readCsvFile, recebendo os parametros passados pelo metodo original atualizarConta, com um parametro adicional que é o nome/path do csv. Com isso a função se encarrega de ler o csv com as informações originais e armazenar essas informações em ArrayLists de seus determinados tipos, incluindo os parametros do que foi imputado quando a aplicação é executada.
Depois de juntas as informações antigas e as novas nos Arrays é chamada a segunda função, writeCsvFile, encarregada de pegar essas informações e escrever no csv, a estrutura do uso das funções do SuperCsv são basicamente as mesmas, além de ser rápida e simples de entender.
O Depois da escrita o arquivo é atualizado com a nova linha que foi inclusa com os parametros passados no main da aplicação.
Por fim foram feitos alguns testes unitários simples, perdi algum tempo ainda tentando desenvolver alguns outros testes unitários que disparassem as excessões porem não tive sucesso, e já não me restava muito tempo na entrega.


###### Nota de Mudança no AtualizarConta

Foi preciso fazer uma adaptação na linha 36 do ReceitaService, uma vez que a comparação com o tamanho da conta, originalmente era 6 porem é preciso contar o caractere que representa o traço (-) na conta, assim chegando a 7. Sem isso a aplicação ao escrever no arquivo sairia um pouco do padrão, alteração foi feita para manter esse padrão na hora da escrita.

###### Considerações Finais

Sinto que consegui atingir o objetivo co desafio, obviamente seria possível fazer o objetivo com mais maestria mas o meu conhecimento com a linguaguem me permitiu chegar com essa conclusão, sinto que dava para colocar mais testes unitários mas realmente não consegui implementar alguns mais complexos.
Algumas mudanças tiveram que ser feita mas espero pode debater essa implementação se for possível em uma proxima entrevista.

Desde já, obrigado! :)


