import java.text.DecimalFormat;
import java.util.Scanner;

public class NewtonRaphson {
    Double[] angka = new Double[5];
    Double[] turunan1 = new Double[5];
    Double[] turunan2 = new Double[3];
    Double toleransiError, X, syarat;
    Double hasilFx, hasilF1X, hasilF2X;
    Integer batasIterasi, jumlahIterasi=0;
    Scanner input = new Scanner(System.in);
    public NewtonRaphson()
    {
        System.out.println("Pola fungsi : f(X) = X^A + BX^C - DX - E");
        System.out.print("Masukkan Nilai A : ");
        angka[0] = Double.parseDouble(input.nextLine());
        System.out.print("Masukkan Nilai B : ");
        angka[1] = Double.parseDouble(input.nextLine());
        System.out.print("Masukkan Nilai C : ");
        angka[2] = Double.parseDouble(input.nextLine());
        System.out.print("Masukkan Nilai D : ");
        angka[3] = Double.parseDouble(input.nextLine());
        System.out.print("Masukkan Nilai E : ");
        angka[4] = Double.parseDouble(input.nextLine());

        turunan1[0] = angka[0];
        turunan1[1] = angka[0] - 1;
        turunan1[2] = angka[1] * angka[2];
        turunan1[3] = angka[2] - 1;
        turunan1[4] = angka[3];

        turunan2[0] = turunan1[0] * turunan1[1];
        turunan2[1] = turunan1[1] - 1;
        turunan2[2] = turunan1[2] * turunan1[3];

        System.out.println("f(x) = X^"+angka[0]+" + "+angka[1]+"X^"+angka[2]+" + "+angka[3]+"X + "+angka[4]);
        System.out.println("f'(x) = "+turunan1[0]+"X^"+turunan1[1]+" + "+turunan1[2]+"X^"+turunan1[3]+" + "+turunan1[4]);
        System.out.println("f''(x) = "+turunan2[0]+"X^"+turunan2[1]+" + "+turunan2[2]);

        System.out.println("Cek Syarat ....");
        do {
            System.out.print("Masukkan nilai X : ");
            X = Double.parseDouble(input.nextLine());

            hasilFx = Math.pow(X, angka[0]) + Math.pow((angka[1]* X), angka[2]) + (angka[3]* X) + angka[4];
            hasilF1X = Math.pow((turunan1[0]* X), turunan1[1]) + Math.pow((turunan1[2]* X), turunan1[3]) + turunan1[4];
            hasilF2X = Math.pow((turunan2[0]* X), turunan2[1]) + turunan2[2];
            System.out.println("Hasil f(x) : "+hasilFx);
            System.out.println("Hasil f'(x) : "+hasilF1X);
            System.out.println("Hasil f''(x) : "+hasilF2X);
            syarat = (hasilFx * hasilF2X) / (hasilF1X*hasilF1X);
            syarat = decimalFormat(syarat);
            if ( syarat < 1 )
                System.out.println("Syarat Terpenuhi.");
            else
                System.out.println("Syarat tidak Terpenuhi");
        } while (!(syarat < 1));

        System.out.println("Hasil : " + syarat);
        System.out.print("Masukkan nilai toleransi Error : ");
        toleransiError = Double.parseDouble(input.nextLine());
        System.out.print("Masukkan batas Iterasi : ");
        batasIterasi = Integer.parseInt(input.nextLine());

        Double X2 = X - (hasilFx/hasilF1X);
        System.out.println("Nilai Akar : "+X2);
        iterasi(X2);
    }
    private void iterasi(Double Xiterasi)
    {
        System.out.println("---ITERASI ke-"+(jumlahIterasi+1)+ "---");
        hasilFx = Math.pow(Xiterasi, angka[0]) + Math.pow((angka[1]* Xiterasi), angka[2]) + (angka[3]* Xiterasi) + angka[4];
        hasilF1X = Math.pow((turunan1[0]* Xiterasi), turunan1[1]) + Math.pow((turunan1[2]* Xiterasi), turunan1[3]) + turunan1[4];

        Double nextX = Xiterasi - (hasilFx/hasilF1X);
        Double hasilError = Math.abs(hasilFx);
        System.out.println("Nilai Akar : "+nextX);
        System.out.println("Nilai Error : "+hasilError);
        if (hasilError > toleransiError)
        {
            System.out.println("Nilai Error belum terpenuhi..");
        }
        else {
            System.out.println("Nilai Error terpenuhi..");
        }
        jumlahIterasi ++;
        if (jumlahIterasi<batasIterasi && hasilError > toleransiError)
            iterasi(nextX);
        else if (jumlahIterasi == batasIterasi) {
            System.out.println("===============================================");
            System.out.println("Batas Iterasi terpenuhi, Iterasi dihentikan... ");
        }
    }
    private Double decimalFormat(Double input)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.###");
        return Double.valueOf((decimalFormat.format(input)));
    }
}