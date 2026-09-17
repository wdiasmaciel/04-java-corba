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
      // Cria e inicia o ORB:
      ORB orb = ORB.init(args, null);

      // Cria e registra o objeto servidor:
      HelloServant helloRef = new HelloServant();
      orb.connect(helloRef);

      // Obtém uma referencia para o root naming context:
      org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
      NamingContext ns = NamingContextHelper.narrow(obj);

      // Associa a Object Reference em Naming:
      NameComponent nc = new NameComponent("Hello", "");
      NameComponent name[] = { nc };
      ns.rebind(name, helloRef);

      // Espera requisições dos clientes:
      Object sync = new Object();
      synchronized (sync) {
        sync.wait();
      }
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}
