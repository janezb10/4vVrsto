import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Minolovec {
    int STEVILO_MIN;
    int STEVILO_STOLPCEV;
    int STEVILO_VRSTIC;
    char[][] polja;
    char[][] skritaPolja;

    public Minolovec() {
        this.STEVILO_MIN = 99;
        this.STEVILO_STOLPCEV = 30;
        this.STEVILO_VRSTIC = 16;
        this.polja = new char[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
        this.skritaPolja = new char[this.STEVILO_VRSTIC][this.STEVILO_STOLPCEV];
    }

    public void igraj() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        this.postaviMine();

        while(true) {
            try {
                this.izpisiPolja();
                this.izpisiSkritaPolja();

                System.out.println("Kam igras?");
                String kam = br.readLine();

                

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
        int i=0;
        while(i<this.STEVILO_MIN) {
            int randInt = rand.nextInt(steviloVsehPolj);
            int vrstica = randInt / this.STEVILO_STOLPCEV;
            int stolpec = randInt % this.STEVILO_STOLPCEV;

            if(this.skritaPolja[vrstica][stolpec] == '@') continue;
            this.skritaPolja[vrstica][stolpec] = '@';
            i++;
        }
    }

    public void izpisiSkritaPolja() {
        System.out.println();
        for(int i=0; i<this.STEVILO_VRSTIC; i++) {
            for(int j=0; j<this.STEVILO_STOLPCEV; j++) {
                if(this.skritaPolja[i][j] == '\u0000') this.skritaPolja[i][j] = '.';
                System.out.print(this.skritaPolja[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();

    }

}
