import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StiriVVrsto {
    char[][] igralnaPlosca = new char[6][7];
    char igralecNaVrsti;
    int visina;
    int sirina;

    public StiriVVrsto() {
        this.visina = 6; // normalno 6
        this.sirina = 7; // normalno 7
        this.igralnaPlosca = new char[this.visina][this.sirina];
        this.igralecNaVrsti = 'X';
    }

    public void igraj() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                izpisiIgralnoPlosco();
                System.out.println("Na vrsti je igralec " + this.igralecNaVrsti);
                System.out.println("v kateri stolpec zelite igrati?   (izhod = 0)");
                int kam = Integer.parseInt(br.readLine());
                if(kam == 0) {
                    break;
                }

                if(!this.vstaviZeton(kam)) {
                    System.out.println("Napacen vnos");
                    continue;
                }
                if(preveriZmago()) {
                    izpisiIgralnoPlosco();
                    System.out.println("ZMAGAL JE IGRALEC: " + this.igralecNaVrsti + "!!!");
                    break;
                }
                if(preveriIzenacenje()) {
                    izpisiIgralnoPlosco();
                    System.out.println("IGRA JE IZENACENA!");
                    break;
                }
    
                this.igralecNaVrsti = this.igralecNaVrsti == 'X' ? 'O' : 'X';
            }
            catch (Exception e) {
                System.out.println("Napacen vnos");
            }
        }
    }

    public boolean preveriIzenacenje() {
        for(int i=0; i<this.visina; i++) {
            for(int j=0; j<this.sirina; j++) {
                if(this.igralnaPlosca[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean preveriZmago() {
        // horizontalno
        for(int i=0; i<this.visina; i++) {
            for(int j=0; j<this.sirina-3; j++) {
                if(this.igralnaPlosca[i][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i][j+1] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i][j+2] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i][j+3] == this.igralecNaVrsti) 
                {
                    return true;
                }
            }
        }
        // vertikalno
        for(int i=0; i<this.visina-3; i++) {
            for(int j=0; j<this.sirina; j++) {
                if(this.igralnaPlosca[i][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+1][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+2][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+3][j] == this.igralecNaVrsti) 
                {
                    return true;
                }
            }
        }

        // diagonala (dvig navzgor)
        for(int i=3; i<this.visina; i++) {
            for(int j=0; j<this.sirina-3; j++) {
                if(this.igralnaPlosca[i][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i-1][j+1] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i-2][j+2] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i-3][j+3] == this.igralecNaVrsti) 
                {
                    return true;
                }
            }
        }
        
        // diagonala (spust navzdol)
        for(int i=0; i<this.visina-3; i++) {
            for(int j=0; j<this.sirina-3; j++) {
                if(this.igralnaPlosca[i][j] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+1][j+1] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+2][j+2] == this.igralecNaVrsti && 
                    this.igralnaPlosca[i+3][j+3] == this.igralecNaVrsti) 
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean vstaviZeton(int kam) {
        if(kam < 1 || kam > this.sirina) {
            return false;
        }
        for(int i=this.visina-1; i>=0; i--) {
            if(this.igralnaPlosca[i][kam-1] == '.') {
                this.igralnaPlosca[i][kam-1] = this.igralecNaVrsti;
                return true;
            }
        }
        return false;
    }

    public void izpisiIgralnoPlosco() {
        System.out.println();
        for(int i=0; i<this.visina; i++) {
            System.out.print("|");
            for(int j=0; j<this.sirina; j++) {
                if(this.igralnaPlosca[i][j] == '\u0000') this.igralnaPlosca[i][j] = '.';
                System.out.print(this.igralnaPlosca[i][j]+ "|");
            }
            System.out.println();
        }
        if(this.sirina <= 10) {
            System.out.print("|");
            for(int i=1; i<=this.sirina; i++) {
                System.out.print(i + "|");
            }
            System.out.println();
        }
        System.out.println();
    }
}
