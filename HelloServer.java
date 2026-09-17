// Arquivo HelloServer.java

import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

class HelloServant extends _HelloImplBase {
  public String sayHello() {
    return "\nHello world !!\n";
  }
}

public class HelloServer {
  public static void main(String args[]) {
    try {
      /*
       * Cria e inicializa o ORB. O segundo argumento é um objeto Properties 
       * com configurações adicionais do ORB. O null significa que nenhuma 
       * propriedade extra foi informada. O ORB usará apenas os argumentos 
       * da linha de comando (args: host e port).
       */
      ORB orb = ORB.init(args, null);

      // Cria e registra o objeto servidor:
      HelloServant helloRef = new HelloServant();
      orb.connect(helloRef);

      // Obtém uma referencia para o root naming context:
      org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
      NamingContext ns = NamingContextHelper.narrow(obj);

      /* 
       * Associa a Object Reference em Naming:
       * Resolve o Object Reference in Naming. O segundo parâmetro é a 
       * categoria ou tipo (kind) do nome. A string vazia significa que 
       * nenhum tipo foi especificado.
       */
      NameComponent nc = new NameComponent("Hello", "");
      NameComponent name[] = { nc };
      ns.rebind(name, helloRef);

      // Espera requisições dos clientes:
      java.lang.Object sync = new java.lang.Object();
      synchronized(sync) {
        sync.wait();
      }
    } catch(Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}
