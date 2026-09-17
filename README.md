# 04-java-corba

---

## Instalação no GitHub Codespaces

O `idlj` foi removido a partir do JDK 11. Neste Codespace, instale um JDK 8
com o SDKMAN e ative-o no terminal antes de compilar o exemplo:

```bash
sdk install java 8.0.504-amzn
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"
export PATH="$JAVA_HOME/bin:$PATH"
hash -r
```

- O primeiro comando precisa ser executado apenas uma vez. 
- O segundo deve ser substituido pelo bloco abaixo em cada novo terminal que for usado para este
projeto. 
- O `PATH` do Codespace pode colocar o Java 25 antes do SDKMAN, por isso o `JAVA_HOME` e o `PATH` são configurados explicitamente:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"
export PATH="$JAVA_HOME/bin:$PATH"
hash -r
java -version
javac -version
idlj -version
```

---

## Compilação da interface IDL:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
idlj -fclient -fserver -oldImplBase Hello.idl 
```

---

## Compilação do Cliente e do Servidor:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
javac *.java HelloApp/*.java 
```

---

## Execução:

---

### Servidor de Nomes (máquina localhost):

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
tnameserv -ORBInitialPort 1050
```

---

### Servidor de Aplicação
```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
java HelloServer -ORBInitialHost localhost -ORBInitialPort 1050
```

---

### Cliente 
 ```bash
 export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
java HelloClient -ORBInitialHost localhost -ORBInitialPort 1050
```

---

**OBS**:
`hash -r` é um comando interno do `Bash` que limpa o cache de localização dos executáveis. Ele não instala nem altera, apenas atualiza o cache de comandos do shell.

---
