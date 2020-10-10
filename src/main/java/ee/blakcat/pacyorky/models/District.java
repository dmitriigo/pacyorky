package ee.blakcat.pacyorky.models;

import java.util.Arrays;
import java.util.List;

public enum District {
    NONE("without location", "без локации", "Без локации"),
    TARTU("Tartu", "Тарту", "Тарту"),
    TALLINN("Tallinn", "Таллинн", "Таллинн"),
    PARNU("Pärnu", "Пярну", "Пярну"),
    JOHVI("Jõhvi", "Йыхви", "Йыхви"),
    KOHTLA("Kohtla-Järve", "Кохла-Ярве", "Кохла-Ярве"),
    HAAPSALU("Haapsalu", "Хаапсалу", "Хаапсалу"),
    NARVA("Narva", "Нарва", "Нарва"),
    SILLAMAE("Sillamäe", "Силламяе", "Силламяе"),
    HARJUMAA("Harjumaa", "Харьюмаа", "Харьюмаа"),
    IDAMAA("Ida-Virumaa", "Ида-Вирумаа", "Ида-Вирумаа"),
    HIIUMAA("Hiiumaa", "Хииюма", "Хииюма"),
    JARVAMAA("Järvamaa", "Ярвамаа", "Ярвамаа"),
    JOGEVAMAA("Jõgevamaa", "Йыгевамаа", "Йыгевамаа"),
    LAANEMAA("Laanemaa", "Ланемаа", "Ланемаа"),
    POLVAMAA("Polvamaa", "Полвамаа", "Полвамаа"),
    PARNUMAA("Pärnumaa", "Пярнумаа", "Пярнумаа"),
    RAPLAMAA("Raplamaa", "Рапламаа", "Рапламаа"),
    RARVERE("Rakvere", "Раквере", "Раквере"),
    SAAREMAA("Saaremaa", "Саремаа", "Саремаа"),
    TARTUMAA("Tartumaa", "Тартумаа", "Тартумаа"),
    VALGAMAA("Valgamaa", "Валгамаа", "Валгамаа"),
    VILJANDIMAA("Viljandimaa", "Вильяндимаа", "Вильяндимаа"),
    VORUMAA("Võrumaa", "Вырумаа", "Вырумаа");

    public final String estName;
    public final String ukrName;
    public final String rusName;


    District(String estName, String ukrName, String rusName) {
        this.estName = estName;
        this.ukrName = ukrName;
        this.rusName = rusName;
    }

    public static District getDistrict(String location) {
        if (location == null || location.equals("")) return District.NONE;
        location = location.toLowerCase();
        location = location.replaceAll(" ", "");
        List<String> locationSplitarr = Arrays.asList(location.split(","));
        District[] districts = District.values();
        for (District district : districts) {
            for (String locationSplit : locationSplitarr) {
                if (locationSplit.contains(district.estName.toLowerCase()) ||
                        locationSplit.contains(district.ukrName.toLowerCase()) ||
                        locationSplit.contains(district.rusName.toLowerCase())) {
                    return district;
                }
            }
        }
        return District.NONE;
    }

    public static void main(String[] args) {
        System.out.println(getDistrict("Jõhvi Kontserdimaja"));
    }
}
