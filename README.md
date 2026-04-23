# T5 - Coloracao de Grafos com DSatur

Implementacao em **Java** da base inicial do Trabalho Pratico 5 da disciplina
**Resolucao de Problemas com Grafos**.

## Video


Link do video explicativo: https://youtu.be/RtajTBTqYJc

## Estrutura

```text
T5/
├── README.md
├── T5.md
├── dados/
│   ├── brasil.txt
│   ├── teste-caminho4.txt
│   ├── teste-ciclo4.txt
│   ├── teste-ciclo5.txt
│   └── teste-triangulo.txt
└── src/
    ├── Bag.java
    ├── Graph.java
    ├── GraphColoringDSatur.java
    ├── In.java
    ├── Main.java
    ├── Stack.java
    ├── StdIn.java
    └── StdOut.java
```

## Compilacao

Execute o comando a seguir para entrar no diretório /src:

```bash
cd src
```

Execute (no src) o comando a seguir para compilar o código:

```bash
javac -d ../out *.java
```

## Execucao

Comando para executar o programa com a entrada oficial:

```bash
java -cp ../out Main ../dados/brasil.txt
```
> **Obs. (PowerShell):** Caso os acentos e caracteres especiais não apareçam corretamente no terminal, execute o comando abaixo antes de rodar o programa para forçar o encoding UTF-8:
> ```bash
> [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
> ```


Comandos para executar o programa com as fixtures de teste:

```bash
java -cp ../out Main ../dados/teste-triangulo.txt
java -cp ../out Main ../dados/teste-caminho4.txt
java -cp ../out Main ../dados/teste-ciclo4.txt
java -cp ../out Main ../dados/teste-ciclo5.txt
```
