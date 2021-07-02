package com.example.undangan;


import com.example.undangan.model.konfrimasi.IsiItem_konfirmasi;
import com.example.undangan.model.tamu.IsiItem_tamu;

import java.util.List;



/**
 * This class represents the country view interface.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface view_konfirmasi {

   // void countriesReady(List<Result_laporan_saya> countries);




    void konfirmasi(List<IsiItem_konfirmasi> konfirmasi);
    void tamu(List<IsiItem_tamu> tamu);
    void status(String status);


}
