
<img src="cthulhu.jpg" alt="Banner do Projeto" width="100%" height="500">



# Echoes of the Abyss
![Build](https://img.shields.io/badge/build-gradle-green)
![Docs](https://img.shields.io/badge/docs-Javadoc-blue)
![Java](https://img.shields.io/badge/Java-25-red)
![Status](https://img.shields.io/badge/status-in%20development-yellow)
![License](https://img.shields.io/badge/license-academic-blue)

### A Horror Deckbuilder in Java.

Projeto desenvolvido para a disciplina MC322 - Programação Orientada a Objetos (POO) da Universidade Estadual de Campinas (UNICAMP).

Inspirado no gênero roguelike deckbuilder e tomando como referência o jogo *Slay the Spire*, **Echoes of the Abyss** transporta o jogador para um cenário de horror cósmico inspirado nas obras de H. P. Lovecraft.

O sistema foi desenvolvido de forma incremental ao longo das tarefas da disciplina, adicionando novas mecânicas, cartas e melhorias de arquitetura.

## 📖 Descrição do Projeto

Em Echoes of the Abyss, o jogador assume o papel de um investigador que se aventura em regiões esquecidas em busca de conhecimento proibido.

Durante sua jornada, ele enfrenta criaturas e entidades que desafiam a compreensão humana. Para sobreviver, o jogador utiliza um baralho de cartas místicas, cada uma representando uma ação, habilidade ou manifestação de conhecimento oculto.

## Funcionalidades Atuais
- Sistema de combate por turnos
- Baralho com compra, descarte e embaralhamento
- Sistema de energia (mana)
- Inimigos com intenções visíveis
- Sistema de efeitos contínuos (status)
- Aplicação de múltiplos efeitos simultâneos
- Documentação completa com **Javadoc**
- Build automatizado com **Gradle**

## 🧩 Arquitetura do Sistema

O projeto é dividido em componentes principais:

- **Entidades**: representam personagens do jogo (Herói e Inimigo)
- **Cartas**: encapsulam ações jogáveis com custo e efeito
- **Efeitos**: sistema modular baseado em interface
- **Gerenciador de Cartas**: controla fluxo de compra, descarte e uso

O sistema de efeitos segue um modelo semelhante ao padrão Observer,
onde as entidades mantêm uma coleção de efeitos ativos que são aplicados
automaticamente a cada turno.

## Tipos de Cartas
O jogo atualmente possui:

### Cartas Básicas
- **Carta de Dano** - causa dano direto ao inimigo
- **Carta de Escudo** - concede proteção ao jogador
- **Cura** – recupera vida
- **Energia** – recupera mana

### Cartas Especiais
- **Corneta de Guerra** - aplica vulnerabilidade ao inimigo (recebe mais dano)
- **Sacrifício** – causa alto dano com custo de vida
- **Magia (Chaos)** – aplica aleatoriamente efeitos como veneno, atordoamento ou queimadura. 

### Cartas de Efeito 

- **Veneno** - causa dano ao longo do turno e depois é removido
- **Atordoar** - faz o alvo perder o próximo turno
- **Queimadura** – dano contínuo por turnos

## Sistema de Efeitos
O jogo utiliza um sistema baseado em interface:

```java
public interface Efeito {
    void aplicar();
    void reduzirDuracao();
    boolean expirou();
}
```
Isso permite:
- Reutilização da lógica
- Extensão fácil para novos efeitos
- Aplicação dinâmica em qualquer entidade


Na primeira etapa do projeto, o sistema de combate será baseado em decisões estratégicas sobre quais cartas utilizar.

O objetivo do jogador é derrotar o inimigo antes que sua vitalidade ou sanidade se esgote, utilizando estratégia e gerenciamento de recursos.

## 🎮 Como Jogar

Durante o combate:

- O jogador possui um baralho com 30 cartas.
- No início de cada turno, o jogador compra 6 cartas do baralho.
- Cada carta possui um custo de energia para ser utilizada.
- O jogador pode usar cartas enquanto tiver mana suficiente.
- As cartas permitem causar dano, ganhar escudo ou utilizar habilidades especiais, como a Corneta de Guerra.
- Ao final do turno do jogador, sua mão é descartada.
- Em seguida, os inimigos realizam suas ações, atacando ou aplicando efeitos.
- Inimigo mostra a intenção do próximo turno.
  
O combate termina quando:

- O herói é derrotado, ou  
- Todos os inimigos são derrotados.

### Controles

- 1,2,... -> usar carta
- "99" -> encerra o turno.
- "0" -> desistir da batalha.

## 🏗 Estrutura do Projeto

```
Tarefa_MC322B/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── App
│   ├── build/
│   │   └── docs/
│   │       └── javadoc/
│   └── src/
│       ├── main/
│       │   └── java/
│       │       └── game/
│       │           └── echoes/
│       │               ├── App.java
│       │               ├── Carta.java
│       │               │    ├── CartaAtordoar.java
│       │               │    ├── CartaChama.java
│       │               │    ├── CartaCorneta.java
│       │               │    ├── CartaCura.java
│       │               │    ├── CartaDano.java
│       │               │    ├── CartaEnergetica.java
│       │               │    ├── CartaEscudo.java
│       │               │    ├── CartaMagica.java
│       │               │    └── CartaVeneno.java
│       │               ├── Efeito.java
│       │               │    ├── EfeitoAtordoar.java
│       │               │    ├── EfeitoQueimadura.java
│       │               │    └── EfeitoVeneno.java
│       │               ├── Entidade.java
│       │               │  ├── Heroi.java
│       │               │  └── Inimigo.java
│       │               └── GerenciamentoDeCartas.java
│       └── test/                
├── .gitignore
└── README.md
```
## ▶️ Como Executar

### Usando Gradle

```bash 
./gradlew run
```

## 📄 Documentação

A documentação completa do projeto foi gerada utilizando Javadoc.

Para acessá-la:

```bash
./gradlew javadoc
```

Em seguida abra no terminal: 

```bash
open build/docs/javadoc/index.html   # Mac
xdg-open build/docs/javadoc/index.html   # Linux
```
## 📝 Testes Unitários

O projeto utiliza testes unitários para validação das entidades e regras de jogo.

Para executar:

```bash
./gradlew test
```

## 🛠 Tecnologias Utilizadas

- Gradle
- Java 25
- Visual Studio Code
- Git
- GitHub

## 🧠 Conceitos Trabalhados

- Programação Orientada a Objetos (POO)
- Encapsulamento (cartas, jogador, inimigo)
- Herança
- Polimorfismo
- Padrão de projeto (Observer-like para efeitos)
- Modularização de código
- Estruturação de projetos com Gradle
- Documentação com Javadoc
- 
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
