package ifgoiano.urt.cerveja;

import java.util.ArrayList;
import java.util.List;

public class oExpertCerveja {

    public List<String> getMarcas(String tipoCerveja){
        List<String> marcas = new ArrayList<>();

        if (tipoCerveja.equals("Pilsen")) {
            marcas.add("Brahma");
            marcas.add("Skol");
            marcas.add("Heineken");
        } else if(tipoCerveja.equals("Puro Malte")) {
            marcas.add("Eisenbahn");
            marcas.add("Bohemia");
            marcas.add("Amstel");
        } else if(tipoCerveja.equals("Weissbier")) {
            marcas.add("Eisenbahn Weizenbier");
            marcas.add("Paulaner");
            marcas.add("Franziskaner");
        } else if (tipoCerveja.equals("IPA")) {
            marcas.add("Dogma Rizoma");
        } else if (tipoCerveja.equals("APA")) {
            marcas.add("Colorado Indica");
            marcas.add("Wals Session Citra");
        }

        return marcas;
    }
}
