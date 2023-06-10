import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
    char[][] igralnaPlosca;
    char igralecNaVrsti;

    public TicTacToe() {
        this.igralnaPlosca = new char[3][3];
        this.igralecNaVrsti = 'X';
    }

    public void igraj() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                izpisiIgralnoPlosco();
                System.out.println("Na vrsti je igralec: " + this.igralecNaVrsti);
                System.out.println("Kam igras? (1 - 9)");
                int kam = Integer.parseInt(br.readLine());
                vstaviZeton(kam);
                if(preveriZmago()) {
                    izpisiIgralnoPlosco();
                    System.out.println();
                    System.out.println("ZMAGA! - zmagal je igralec " + this.igralecNaVrsti);
                    return;
                }
                this.igralecNaVrsti = this.igralecNaVrsti == 'X' ? 'O' : 'X';
            }
            catch(Exception e) {

            } 
        }
    }

    private void izpisiIgralnoPlosco() {
        System.out.println();
        for(int i=0; i<3; i++) {
            // System.out.print("|");
            for(int j=0; j<3; j++) {
                if(this.igralnaPlosca[i][j] == '\u0000') this.igralnaPlosca[i][j] = '_';
                if(i == 2 && this.igralnaPlosca[i][j] == '_') {
                    System.out.print(" ");
                }
                else {
                    System.out.print(this.igralnaPlosca[i][j]);
                }
                if(j != 2) System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void vstaviZeton(int kam) {
        switch(kam) {
            case 1:
                if(this.igralnaPlosca[2][0] != '_') return;
                this.igralnaPlosca[2][0] = this.igralecNaVrsti;
                break;
            case 2:
                if(this.igralnaPlosca[2][1] != '_') return;
                this.igralnaPlosca[2][1] = this.igralecNaVrsti;
                break;
            case 3:
                if(this.igralnaPlosca[2][2] != '_') return;
                this.igralnaPlosca[2][2] = this.igralecNaVrsti;
                break;
            case 4:
                if(this.igralnaPlosca[1][0] != '_') return;
                this.igralnaPlosca[1][0] = this.igralecNaVrsti;
                break;
            case 5:
                if(this.igralnaPlosca[1][1] != '_') return;
                this.igralnaPlosca[1][1] = this.igralecNaVrsti;
                break;
            case 6:
                if(this.igralnaPlosca[1][2] != '_') return;
                this.igralnaPlosca[1][2] = this.igralecNaVrsti;
                break;
            case 7:
                if(this.igralnaPlosca[0][0] != '_') return;
                this.igralnaPlosca[0][0] = this.igralecNaVrsti;
                break;
            case 8:
                if(this.igralnaPlosca[0][1] != '_') return;
                this.igralnaPlosca[0][1] = this.igralecNaVrsti;
                break;
            case 9:
                if(this.igralnaPlosca[0][2] != '_') return;
                this.igralnaPlosca[0][2] = this.igralecNaVrsti;
                break;
            default:
                return;
        }
    }

    private boolean preveriZmago() {
        int a = 0;
        int b = 0;
        int c = 0;
        for(int i=0; i<3; i++) {
            a=0;
            b=0;
            c=0;
            for(int j=0; j<3; j++) {
                if(this.igralnaPlosca[i][j] == this.igralecNaVrsti) a++;
                if(this.igralnaPlosca[j][i] == this.igralecNaVrsti) b++;
                if(this.igralnaPlosca[j][j] == this.igralecNaVrsti) c++;
            }
            if(a==3 || b==3 || c==3) return true;
        }
        if(this.igralnaPlosca[0][2] == this.igralecNaVrsti && this.igralnaPlosca[1][1] == this.igralecNaVrsti && this.igralnaPlosca[2][0] == this.igralecNaVrsti) return true;

        return false;
    }
}
