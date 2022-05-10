package com.kdb2018.sasa.percent.model.hisseModel;

import com.kdb2018.sasa.percent.model.CuzdanModel;

public class Liste {
    Hisse ilk = null, son=null;



    public void HisseEkle(String sembol,String deger){
        Hisse x =new Hisse(sembol,deger);
        if(ilk==null){
            ilk = x;
            son = x;
        }
        else {
            son.next = x;
            son = x;
        }
    }
    public String Esle(CuzdanModel.CuzdanModelHisse cuzdanList){
        Hisse hisse = ilk;
        float a = 0;


        int i=0;
        while (hisse.next!=null){

            if(hisse==null)
                break;

            else{
                if(cuzdanList.getHisse_adi().equals(hisse.sembol)){
                    return hisse.deger;

                }else{
                  i++;
                }
                hisse=hisse.next;
                i=0;
            }
        }
        return hisse.deger;

    }

    public int Listele() {
        int size =0;
        Hisse kl = ilk;
        if (kl == null) {
            System.out.println("liste boş");
        } else {
            while (kl != null) {
                size++;
                System.out.println("++++++"+kl.deger + " " + kl.sembol);
                kl = kl.next;
            }

        }
        return size;
    }




}

  class Hisse{
    String sembol;
    String deger;
    Hisse next;

    public String getSembol() {
        return sembol;
    }

    public void setSembol(String sembol) {
        this.sembol = sembol;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public Hisse() {
    }

    public Hisse(String sembol, String deger) {
        this.sembol = sembol;
        this.deger = deger;
    }
}