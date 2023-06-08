import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Minolovec {
    int STEVILO_MIN;
    int STEVILO_STOLPCEV;
    int STEVILO_VRSTIC;
    char[][] polja;
    int[][] skritaPolja;

    public Minolovec() {
        this.STEVILO_MIN = 99;
        this.STEVILO_STOLPCEV = 30;
        this.STEVILO_VRSTIC = 16;
        this.polja = new char[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
        this.skritaPolja = new int[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
    }

    public void igraj() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        this.postaviMine();

        while(true) {
            try {
                this.izpisiPolja();
                this.izpisiSkritaPolja();

                System.out.println("Kam igras? (vrstica stolpec npr: \"0 12\")");
                String kam = br.readLine();
                int vrstica = Integer.parseInt(kam.split(" ")[0]);
                int stolpec = Integer.parseInt(kam.split(" ")[1]);
                
                if(this.skritaPolja[vrstica][stolpec] == 9) {
                    System.out.println("KONEC IGRE - zadel si mino!");
                    return;
                }
                odpriObmocje(vrstica, stolpec);

            }
            catch(Exception e) {

            }
        }
    }

    public void izpisiPolja() {
        System.out.println();
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            String stevilkaVrstice = i / 10 >= 1 ? (i + " |") : (" " + i + " |");
            System.out.print(stevilkaVrstice);
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                if(this.polja[i][j] == '\u0000') this.polja[i][j] = '#';
                System.out.print(this.polja[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("   |");
        for(int i=0; i<this.STEVILO_STOLPCEV; i++) {
            System.out.print((i%10) + "|");
        }
        System.out.println();
    }

    public void postaviMine() {
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
                if(i==0 && j==0) {
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j+1] == 9) steviloMinVOkolici++;
                }
                else if(i==this.STEVILO_VRSTIC-1 && j==0) {
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                }
                else if(i==0 && j==this.STEVILO_STOLPCEV-1) {
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                }
                else if(i==this.STEVILO_VRSTIC-1 && j==this.STEVILO_STOLPCEV-1) {
                    if(this.skritaPolja[i-1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                }
                else if(i==0) {
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j+1] == 9) steviloMinVOkolici++;
                }
                else if(j==0) {
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j+1] == 9) steviloMinVOkolici++;
                }
                else if(i==this.STEVILO_VRSTIC-1) {
                    if(this.skritaPolja[i-1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                }
                else if(j==this.STEVILO_STOLPCEV-1) {
                    if(this.skritaPolja[i-1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                }
                else {
                    if(this.skritaPolja[i-1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i-1][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i][j+1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j-1] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j] == 9) steviloMinVOkolici++;
                    if(this.skritaPolja[i+1][j+1] == 9) steviloMinVOkolici++;
                }
                this.skritaPolja[i][j] = steviloMinVOkolici;
                
            }
        }
    }

    public void izpisiSkritaPolja() {
        System.out.println();
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                System.out.print(this.skritaPolja[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void odpriObmocje(int i, int j) {
        this.polja[i][j] = (char)(this.skritaPolja[i][j]+'0');
        odpriObmocjeRekurzivno(i, j);
    }
    public void odpriObmocjeRekurzivno(int i, int j) {
        if(i<0 || j<0 || i >= this.STEVILO_VRSTIC || j>= this.STEVILO_STOLPCEV || this.skritaPolja[i][j] == 9) {
            return;
        }
        this.polja[i][j] = (char)(this.skritaPolja[i][j]+'0');
        
    }
    // moram prevert da ni na robu
    // moram prevert ce je levo in desno kj gor in dol
    // pokazem gor dol levo desno
    // trenutni ni šit


}
