package com.kdb2018.sasa.percent.model;

import com.kdb2018.sasa.percent.Keys;

import java.io.Serializable;

public class CuzdanModel implements Serializable {

    public static class CuzdanModelKripto {
        private int id;
        private String coin_adi;
        private Float coin_alis_fiyati;
        private Float coin_alis_adeti;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoin_adi() {
            return coin_adi;
        }

        public void setCoin_adi(String coin_adi) {
            this.coin_adi = coin_adi;
        }

        public Float getCoin_alis_fiyati() {
            return coin_alis_fiyati;
        }

        public void setCoin_alis_fiyati(Float coin_alis_fiyati) {
            this.coin_alis_fiyati = coin_alis_fiyati;
        }

        public Float getCoin_alis_adeti() {
            return coin_alis_adeti;
        }

        public void setCoin_alis_adeti(Float coin_alis_adeti) {
            this.coin_alis_adeti = coin_alis_adeti;
        }

        public CuzdanModelKripto(int id, String coin_adi, Float coin_alis_fiyati, Float coin_alis_adeti) {
            this.id = id;
            this.coin_adi = coin_adi;
            this.coin_alis_fiyati = coin_alis_fiyati;
            this.coin_alis_adeti = coin_alis_adeti;
        }

        public CuzdanModelKripto() {
        }
    }


    public static class CuzdanModelHisse{
        private int id;
        private String hisse_adi;
        private Float hisse_alis_fiyati;
        private Float hisse_alis_adeti;

        public CuzdanModelHisse(int id, String hisse_adi, Float hisse_alis_fiyati, Float hisse_alis_adeti) {
            this.id = id;
            this.hisse_adi = hisse_adi;
            this.hisse_alis_fiyati = hisse_alis_fiyati;
            this.hisse_alis_adeti = hisse_alis_adeti;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHisse_adi() {
            return hisse_adi;
        }

        public void setHisse_adi(String hisse_adi) {
            this.hisse_adi = hisse_adi;
        }

        public Float getHisse_alis_fiyati() {
            return hisse_alis_fiyati;
        }

        public void setHisse_alis_fiyati(Float hisse_alis_fiyati) {
            this.hisse_alis_fiyati = hisse_alis_fiyati;
        }

        public Float getHisse_alis_adeti() {
            return hisse_alis_adeti;
        }

        public void setHisse_alis_adeti(Float hisse_alis_adeti) {
            this.hisse_alis_adeti = hisse_alis_adeti;
        }
    }


}
