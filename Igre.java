import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Igre {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            try {
                System.out.println();
                System.out.println("Vnesite: ");
                System.out.println("0 -izhod");
                System.out.println("1 -Stiri v vrsto (nova igra)");
                System.out.println("2 -Minolovec (nova igra)");
                String a = br.readLine();
                switch(a) {
                    case "0":
                    return;
                    case "1":
                        System.out.println("**************");
                        System.out.println();
                        System.out.println("Stiri V Vrsto!");
                        StiriVVrsto stiriVVrsto = new StiriVVrsto();
                        stiriVVrsto.igraj();
                        break;
                    case "2":
                        System.out.println("Izberi tezavnost: (1-3)");
                        int tezavnost = Integer.parseInt(br.readLine());
                        System.out.println("**************");
                        System.out.println();
                        System.out.println("Minolovec!");
                        Minolovec minolovec = new Minolovec(tezavnost);
                        minolovec.igraj();
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}