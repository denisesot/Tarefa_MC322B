
<img src="cthulhu.jpg" alt="Banner do Projeto" width="100%" height="500">



# Echoes of the Abyss
![Java](https://img.shields.io/badge/Java-25-red)
![Status](https://img.shields.io/badge/status-in%20development-yellow)
![License](https://img.shields.io/badge/license-academic-blue)

### A Horror Deckbuilder in Java.

Projeto desenvolvido para a disciplina MC322 - Programação Orientada a Objetos (POO) da Universidade Estadual de Campinas (UNICAMP).

Inspirado no gênero roguelike deckbuilder e tomando como referência o jogo *Slay the Spire*, **Echoes of the Abyss** transporta o jogador para um cenário de horror cósmico inspirado nas obras de H. P. Lovecraft.

Durante as tarefas da disciplina, o sistema será desenvolvido de forma incremental, adicionando novas mecânicas, cartas e melhorias de arquitetura.

## 📖 Descrição do Projeto

Em Echoes of the Abyss, o jogador assume o papel de um investigador que se aventura em regiões esquecidas em busca de conhecimento proibido.

Durante sua jornada, ele enfrenta criaturas e entidades que desafiam a compreensão humana. Para sobreviver, o jogador utiliza um baralho de cartas místicas, cada uma representando uma ação, habilidade ou manifestação de conhecimento oculto.

Cada carta pode possuir efeitos específicos, como:

- Causar dano ao inimigo  
- Aplicar efeitos especiais durante o combate  
- Modificar atributos do jogador ou do inimigo  

Na primeira etapa do projeto, o sistema de combate será baseado em decisões estratégicas sobre quais cartas utilizar.

Inicialmente, o jogador terá acesso a cartas básicas como:

- **Carta de Dano** – ataques diretos contra o inimigo  
- **Carta de Escudo** – proteção contra ataques recebidos  
- **Corneta de Guerra** – habilidade especial que fortalece temporariamente o herói

O objetivo do jogador é derrotar o inimigo antes que sua vitalidade ou sanidade se esgote, utilizando estratégia e gerenciamento de recursos.

## 🎮 Como Jogar

Durante o combate:

- O jogador possui um baralho de cartas com diferentes habilidades.
- No início de cada turno, 6 cartas são compradas para a mão do jogador.
- Cada carta possui um custo de energia para ser utilizada.
- O jogador pode usar cartas enquanto possuir energia disponível.
- Após o turno do jogador, os inimigos realizam suas ações, atacando ou aplicando efeitos.

O combate termina quando:

- O herói é derrotado, ou  
- Todos os inimigos são derrotados.

## 🧠 Conceitos Trabalhados

- Programação Orientada a Objetos (POO)
- Encapsulamento (cartas, jogador, inimigo)
- Modelagem de entidades de jogo

## 🏗 Estrutura do Projeto

```
Tarefa_MC322B/
├── src/
│   ├── App.java
│   ├── Herói.java
│   ├── Inimigo.java
│   ├── CartaDano.java
│   └── CartaEscudo.java
├── bin/
├── lib/
├── .gitignore
└── README.md
```
## ▶️ Como Executar

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
java App
```

## 🛠 Tecnologias Utilizadas

- Java 25
- Visual Studio Code
- Git
- GitHub

## 👨‍🏫 Disciplina

MC322 - Programação Orientada a Objetos
Instituto de Computação - UNICAMP

Professor: Marcelo da Silva Reis

## 👥 Autores

Projeto desenvolvido por:

- **Caio Dominiguetti Velloso** - RA253448
- **Denise Tuda** - RA299429

## 📌 Observação

Este projeto possui fins educacionais e foi desenvolvido como parte das atividades avaliativas da disciplina.
