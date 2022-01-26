#language:pt

@Regressivo
Funcionalidade: Consultar acoes no Google
  Como um investidor
  Quero saber o valor das ações do dia no mercado financeiro
  Para saber se houve valorização ou desvalorização.

  @AcaoEspecifica
  Cenario: Buscar somente uma acao especifica

    Dado que esteja na pagina "https://www.google.com.br/"
    Quando preencho o campo pesquisa com "ITSA4"
    E clico em Pesquisa Google
    Entao exibe o valor da acao

  @esquema
  Esquema do Cenario: Busca acao <cenario>

    Dado que esteja na pagina "https://www.google.com.br/"
    Quando preencho o campo pesquisa com "<acoes>"
    E clico em Pesquisa Google
    Entao exibe o valor da acao

    Exemplos:
      | cenario         | acoes |
      | Itaúsa          | ITSA4 |
      | Petrobras       | PETR4 |
      | Ambev           | ABEV3 |
      | Banco do Brasil | bbas3 |