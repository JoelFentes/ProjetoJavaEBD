# Sistema de Gestão EBD

Este projeto é um sistema de gestão para uma Escola Bíblica Dominical (EBD) desenvolvido em Java. O sistema permite gerenciar professores, salas, alunos, aulas, despesas e receitas, além de gerar relatórios financeiros e enviar notificações.

## Funcionalidades

- **Gestão de Professores**: Adicionar, listar, e excluir professores.
- **Gestão de Salas**: Cadastrar e listar salas.
- **Gestão de Alunos**: Cadastrar e listar alunos.
- **Gestão de Aulas**: Agendar e listar aulas.
- **Gestão de Despesas e Receitas**: Registrar e visualizar despesas e receitas.
- **Relatórios Financeiros**: Gerar relatórios sobre despesas e receitas.
- **Notificações**: Enviar notificações para alunos e sobre eventos para salas.

## Tecnologias Utilizadas

- Java 17
- Arquivos de texto para persistência de dados

## Estrutura do Projeto

### Model

Contém as classes que representam os modelos de dados do sistema:

- **Professor**
- **Sala**
- **Aluno**
- **Aula**
- **Despesa**
- **Receita**
- **RelatorioFinanceiro**
- **NotificacaoAluno**
- **NotificacaoEvento**

### View

Contém as classes responsáveis pela interface com o usuário:

- **ProfessorView**
- **SalaView**
- **AlunoView**
- **AulaView**
- **DespesaView**
- **ReceitaView**
- **RelatorioFinanceiroView**
- **NotificacaoAlunoView**
- **NotificacaoEventoView**
- **MainView**: Visão principal que integra todas as outras visões.

### Controller

Contém as classes que gerenciam a lógica de negócio e a interação entre as visões e os modelos:

- **ProfessorController**
- **SalaController**
- **AlunoController**
- **AulaController**
- **DespesaController**
- **ReceitaController**
- **RelatorioFinanceiroController**
- **NotificacaoAlunoController**
- **NotificacaoEventoController**

### Main

A classe principal do projeto que inicializa e executa o sistema:

- **Main**: Configura e inicializa as visões, controladores e listas de dados, e exibe o menu principal.

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/SEU_USUARIO/gestao-ebd.git
