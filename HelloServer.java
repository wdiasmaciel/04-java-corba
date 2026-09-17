// arquivo HelloServer.java

import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
 
class HelloServant extends _HelloImplBase  {
    public String sayHello()
    { return "\nHello world !!\n"; }
}

public class HelloServer {
   public static void main(String args []) {
   try{
      // cria e inicializa ORB
      ORB orb = ORB.init (args, null);  
 
      // cria e registra objeto servidor 
      HelloServant helloRef = new HelloServant();
      orb.connect (helloRef);
 
      // obtém referencia para o root naming context
      org.omg.CORBA.Object obj = 
                             orb.resolve_initial_references  ("NameService");
      NamingContext ns = NamingContextHelper.narrow (obj);
      // bind the Object Reference in Naming
     NameComponent nc = new NameComponent ("Hello", "");
     NameComponent name [ ] = {nc};
     ns.rebind (name, helloRef);
 
     // espera requisições dos clientes
     java.lang.Object sync = new java.lang.Object ();
     synchronized (sync) { sync.wait (); }
   } catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
   }
  }
}
