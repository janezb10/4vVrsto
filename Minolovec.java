import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Minolovec {
    int STEVILO_MIN;
    int STEVILO_STOLPCEV;
    int STEVILO_VRSTIC;
    char[][] polja;
    int[][] skritaPolja;

    public Minolovec(int tezavnost) {
        switch(tezavnost) {
            case 1:
                this.STEVILO_MIN = 10;
                this.STEVILO_STOLPCEV = 10;
                this.STEVILO_VRSTIC = 8;
                break;
            case 2:
                this.STEVILO_MIN = 40;
                this.STEVILO_STOLPCEV = 18;
                this.STEVILO_VRSTIC = 14;
                break;
            case 3:
                this.STEVILO_MIN = 99;
                this.STEVILO_STOLPCEV = 24;
                this.STEVILO_VRSTIC = 20;
                break; 
        }

        this.polja = new char[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
        this.skritaPolja = new int[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
    }

    public void igraj() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        postaviMine();

        while(true) {
            try {
                // izpisiSkritaPolja();
                izpisiPolja();

                System.out.println();
                System.out.println("Kam igras? (vrstica stolpec npr: \"0 12\")");
                String kam = br.readLine();
                int vrstica = Integer.parseInt(kam.split(" ")[0]);
                int stolpec = Integer.parseInt(kam.split(" ")[1]);
                
                if(this.skritaPolja[vrstica][stolpec] == 9) {
                    System.out.println("KONEC IGRE - zadel si mino!");
                    return;
                }
                odpriObmocjeRekurzivno(vrstica, stolpec);
                if(preveriZmago()) {
                    izpisiPolja();
                    System.out.println();
                    System.out.println("ČESTITAM!! - nasel si vse mine!");
                    return;
                }
            }
            catch(Exception e) {

            }
        }
    }

    private void izpisiPolja() {
        System.out.println();
        System.out.print("   |");
        for(int i=0; i<this.STEVILO_STOLPCEV; i++) {
            System.out.print((i%10) + "|");
        }
        System.out.println();
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            String stevilkaVrstice = i / 10 >= 1 ? (i + " |") : (" " + i + " |");
            System.out.print(stevilkaVrstice);
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                if(this.polja[i][j] == '\u0000') this.polja[i][j] = '#';
                if(this.polja[i][j] == '0') {
                    System.out.print('_' + "|");
                }
                else {
                    System.out.print(this.polja[i][j] + "|");
                }
            }
            System.out.print(" "+i);
            System.out.println();
        }
        System.out.print("   |");
        for(int i=0; i<this.STEVILO_STOLPCEV; i++) {
            System.out.print((i%10) + "|");
        }
        System.out.println();
    }

    private void postaviMine() {
        Random rand = new Random();
        
        int steviloVsehPolj = this.STEVILO_STOLPCEV * this.STEVILO_VRSTIC;
        int o=0;
        while(o<this.STEVILO_MIN) {
            int randInt = rand.nextInt(steviloVsehPolj);
            int vrstica = randInt / this.STEVILO_STOLPCEV;
            int stolpec = randInt % this.STEVILO_STOLPCEV;

            if(this.skritaPolja[vrstica][stolpec] == 9) continue;
            this.skritaPolja[vrstica][stolpec] = 9;
            o++;
        }
        // Vpisi stevilke
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                if(this.skritaPolja[i][j] == 9) continue;
                int steviloMinVOkolici =0;
                for(int k=i-1; k<=i+1; k++) {
                    for(int m=j-1; m<=j+1; m++) {
                        if(k<0 || m<0 || i>=this.STEVILO_VRSTIC-1 || m>=this.STEVILO_STOLPCEV-1){
                            continue;
                        }
                        if(this.skritaPolja[k][m] == 9) {
                            steviloMinVOkolici++;
                        }
                    }
                }
                this.skritaPolja[i][j] = steviloMinVOkolici;  
            }
        }
    }

    private void izpisiSkritaPolja() {
        System.out.println();
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                System.out.print(this.skritaPolja[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }


    private void odpriObmocjeRekurzivno(int i, int j) {
        if(i<0 || j<0 || i >= this.STEVILO_VRSTIC || j>= this.STEVILO_STOLPCEV || this.skritaPolja[i][j] == 9 || this.polja[i][j] != '#') {
            return;
        }
        this.polja[i][j] = (char)(this.skritaPolja[i][j]+'0');
        if(this.skritaPolja[i][j] == 0) {
            odpriObmocjeRekurzivno(i+1, j+1);
            odpriObmocjeRekurzivno(i+1, j-1);
            odpriObmocjeRekurzivno(i-1, j+1);
            odpriObmocjeRekurzivno(i-1, j-1);
            odpriObmocjeRekurzivno(i+1, j);
            odpriObmocjeRekurzivno(i-1, j);
            odpriObmocjeRekurzivno(i, j+1);
            odpriObmocjeRekurzivno(i, j-1);
        }
    }

    private boolean preveriZmago() {
        int steviloNeodkritihPolj = 0;
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                if(this.polja[i][j] == '#') steviloNeodkritihPolj++;
            }
        }
        if(steviloNeodkritihPolj == this.STEVILO_MIN) {
            return true;
        }
        return false;
    }

}
