import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import Razredi.Artikel;
import Razredi.Artikli;
import Razredi.Podjetje;
import Razredi.Invoices;
import Razredi.Companies;
import Razredi.*;
import  Razredi.Racun;
import com.google.gson.Gson;

import java.util.UUID;
import java.util.Random;



public class Main {

    public static void main(String[] args)
    {
        Gson gson = new Gson();
        int[] st = {3,8,3,0,0,3,3,5,1,2,2,8,0};
        Random rand = new Random();
        int pickedNumber = rand.nextInt(999999999) + 1;
        System.out.println(pickedNumber);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        double vsota =0.;
        double vsotaDDV =0.;
        double DDV = 0.22;
        Artikel objekt1 = new Artikel(randomUUIDString,"miska",10.2,1,2,"99211678907002","0");
        Artikel objekt2 = new Artikel(randomUUIDString,"tipkovnica",5.6,1,2,"95111679904002","0");
        Artikel objekt3 = new Artikel(randomUUIDString,"slusalke",32.7,3,2,"00321699905002","0");
        Artikel objekt4 = new Artikel(randomUUIDString,"zaslon",4.99,2,2,"00211678902002","0");
        Artikel objekt5 = new Artikel(randomUUIDString,"racunalnik",21.44,4,2,"92211678902002","0");
        Podjetje LEK = new Podjetje("LEK","SI1732811000",87916452,true);
        Podjetje HENKEL = new Podjetje("HENKEL","SI58665765",626175200,true);
        Podjetje KOS = new Podjetje("KOS","714525634",883145582,false);

        objekt1.FilterCrtneKode(objekt1.getEan());
        objekt2.FilterCrtneKode(objekt2.getEan());
        objekt3.FilterCrtneKode(objekt3.getEan());
        objekt4.FilterCrtneKode(objekt4.getEan());
        objekt5.FilterCrtneKode(objekt5.getEan());

        List <Artikel> seznam = new ArrayList<>();
        List <Racun> seznamRacunov = new ArrayList<>();
        List <Podjetje> seznamPodjetij = new ArrayList<>();
        seznam.add(objekt1);
        seznam.add(objekt2);
        seznam.add(objekt3);
        seznam.add(objekt4);
        seznam.add(objekt5);
        seznamPodjetij.add(LEK);
        seznamPodjetij.add(HENKEL);
        seznamPodjetij.add(KOS);
        System.out.println("črtna koda je veljavna: "+objekt1.checkDigit(st));
       /* System.out.println("črtna koda je veljavna: "+objekt2.checkDigit(st));
        System.out.println("črtna koda je veljavna: "+objekt3.checkDigit(st));
        System.out.println("črtna koda je veljavna: "+objekt4.checkDigit(st));
        System.out.println("črtna koda je veljavna: "+objekt5.checkDigit(st));
        */


        for (Artikel pr : seznam){
            vsota+= pr.getCena() * pr.getKolicina();
            vsotaDDV+= pr.getCena() * pr.getKolicina()+DDV;
        };
        Date datum = new Date();
        Artikli test1 = new Artikli(seznam);

        Racun racun1 = new Racun(randomUUIDString,datum,test1,LEK.getIme(),LEK.getDavcnaST());
        Racun racun2 = new Racun(randomUUIDString,datum,test1,HENKEL.getIme(),HENKEL.getDavcnaST());
        Racun racun3 = new Racun(randomUUIDString,datum,test1,KOS.getIme(),KOS.getDavcnaST());
        seznamRacunov.add(racun1);
        seznamRacunov.add(racun2);
        seznamRacunov.add(racun3);
        Invoices test2 = new Invoices(seznamRacunov);
        Companies Comp = new Companies(seznamPodjetij);
        System.out.println(test2+ "\n"+"Skupni znesek računa brez DDV-ja: " +vsota +
                        "\n"+"Skupni znesek računa z DDV-jem: " + vsotaDDV +"\n"+" "+"Seznam podjetij "+Comp);
    }
}
/*Ali je smiselno uvesti časovno značko na nivoju artikla na računu?*/
/*Da, saj tako zvemo kdaj je bil račun izdan in se lahko po datumo sklicujemo.
* Nekatera podjeta imajo garancijo na račun*/