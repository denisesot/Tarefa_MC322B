# Tarefa_MC322B

 Horror Deckbuilder em Java

Projeto desenvolvido para a disciplina MC322 - Programação Orientada a Objetos (POO) da Universidade estadual de Campinas (UNICAMP).

O objetivo desse projeto é implementar, de forma incremental, um jogo inspirado no gênero ** roguelike deckbuilder **, tomando como referência o jogo Slay the Spire. Durante as tarefas da disciplina, o sistema será expandido gradualmente com novos recursos e melhorias de arquitetura.

## 📖 Descrição do Projeto

O jogo consiste em um sistema baseado em cartas, onde o jogador (Herói) enfrenta um inimigo utilizando um baralho com diferentes ações.

Cada carta possui efeitos específicos, como:

- Causa dano;
- Aplicar efeitos;
- Modificar atributos do jogador ou inimigo.

Inicialmente, para a primeira parte do projeto, começaremos com jogador tomar decisões estratégias sobre quais cartas utilizar, sendo elas limitadas a carta de dano, escudo e corneta de guerra.

O projeto tem como foco principal:

- Aplicação de Programcação Orientada a objetos
- Modelagem e classes
- Boas práticas de desenvolvimento em Java

## 🧠 Conceitos Trabalhados

- Programação Orientada a Objetos (POO)
- Encapsulamento (cartas, jogador, inimigo)
- Modelagem de entidades de jogo

## 🏗 Estrutura do Projeto

```
Tarefa_MC322B/
├── src/
|   ├── App.java
|   └── Herói.java
|   └── Inimigo.java
|   └── CartaDano.java
|   └── CartaEscudo.java
├── bin/
├── lib/
├── .gitignore
└── README.md
```
## ▶️ Como Executar o Projeto 

1. Clonar o repositório
``` </> Bash
git clone https://github.com/denisesot/Tarefa_MC322B.git
```

2. Entrar na pasta do projeto
``` </> Bash
cd Tarefa_MC322B
```

3. Compilar o código
``` </> Bash
javac src/App.java
```
4. Executar
``` </> Bash
java App.java
```
## 👨‍🏫 Disciplina

MC322 - Programação Orientada a Objetos
Instituto de Computação - UNICAMP

Professor: Marcelo da Silva Reis

## 📌 Observação

Este projeto possui fins educacionais e foi desenvolvido como parte das atividades avaliativas da disciplina.
