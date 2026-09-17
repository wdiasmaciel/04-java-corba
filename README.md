# 04-java-corba

## Instalacao no GitHub Codespaces

O `idlj` foi removido a partir do JDK 11. Neste Codespace, instale um JDK 8
com o SDKMAN e ative-o no terminal antes de compilar o exemplo:

```bash
sdk install java 8.0.504-amzn
sdk use java 8.0.504-amzn
```

O primeiro comando precisa ser executado apenas uma vez. O segundo deve ser
executado em cada novo terminal que for usado para este projeto. Confirme a
instalacao com:

```bash
java -version
idlj -version
```

## Compilação da interface IDL:

```bash
idlj -fclient -fserver -oldImplBase Hello.idl 
```