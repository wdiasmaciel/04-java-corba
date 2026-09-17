// Arquivo HelloClient.java

import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class HelloClient {
  public static void main(String args[]) {
    try {
      /*
       * Cria e inicializa o ORB. O segundo argumento é um objeto Properties 
       * com configurações adicionais do ORB. O null significa que nenhuma 
       * propriedade extra foi informada. O ORB usará apenas os argumentos 
       * da linha de comando (args: host e port).
       */
      ORB orb = ORB.init(args, null); 

      // Gera o root naming context:
      org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
      NamingContext ns = NamingContextHelper.narrow(obj);
      
      /* 
       * Resolve o Object Reference in Naming. O segundo parâmetro é a 
       * categoria ou tipo (kind) do nome. A string vazia significa que 
       * nenhum tipo foi especificado.
       */
      NameComponent nc = new NameComponent("Hello", "");
      NameComponent name[] = { nc };
      Hello helloRef = HelloHelper.narrow(ns.resolve(name));

      // Chama o objeto servidor Hello e imprime os resultados:
      String hello = helloRef.sayHello();
      System.out.println(hello);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
