import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Igre {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StiriVVrsto stiriVVrsto;

        while(true) {
            try {
                System.out.println();
                System.out.println("Vnesite: ");
                System.out.println("0 -izhod");
                System.out.println("1 -Stiri v vrsto (nova igra)");
                String a = br.readLine();
                switch(a) {
                    case "1":
                        System.out.println("**************");
                        System.out.println();
                        System.out.println("Stiri V Vrsto!");
                        stiriVVrsto = new StiriVVrsto();
                        stiriVVrsto.igraj();
                        break;
                    case "2":
                        return;
                }
            }
            catch (Exception e) {

            }
        }
    }
}