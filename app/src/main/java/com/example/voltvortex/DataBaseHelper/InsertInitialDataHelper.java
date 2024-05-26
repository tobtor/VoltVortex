package com.example.voltvortex.DataBaseHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.*;

import java.util.Date;

public class InsertInitialDataHelper {

    public static void insertInitialData(SQLiteDatabase db) {
        insertContactPerson(db, "Paweł Mikołajek", "Enermitel", "Pomiarowiec",
                "pawel.mikolajek@enermitel.pl", "515170172");
        insertContactPerson(db, "Julia Lewandowska", "Enermitel", "Specjalista ds. Marketingu",
                "julia.lewandowska@enermitel.pl", "505802710");
        insertContactPerson(db, "Karol Kotkowski", "Enermitel", "Kierownik zespołu elektryków",
                "karol.kotkowski@enermitel.pl", "602782432");
        insertContactPerson(db, "Miłosz Dąbrowski", "Enermitel", "Pomiarowiec",
                "", "691882980");
        insertContactPerson(db, "Błaszczyk Beata", "IBS Budownictwo", "Opiekun Klienta",
                "b.blaszczyk@ibsbudownictwo.pl", "797382708");
        insertContactPerson(db, "Kicler Marta", "IBS Budownictwo", "Opiekun Klienta",
                "m.kicler@ibsbudownictwo.pl", "690508477");
        insertContactPerson(db, "Maksimczuk Martyna", "IBS Budownictwo", "Opiekun Klienta",
                "m.maksimczuk@ibsbudownictwo.pl", "502024050");
        insertContactPerson(db, "Blus Olga", "IBS Budownictwo", "Opiekun Klienta",
                "o.blus@ibsinwestycje.pl", "797514312");
        insertContactPerson(db, "Buziński Sebastian", "IBS Budownictwo", "Opiekun Klienta",
                "s.buzinski@ibsbudownictwo.pl", "510052231");
        insertContactPerson(db, "Patryk Thomasi", "MCDONALD`S", "",
                "", "664148614");
        insertContactPerson(db, "Kinga Szumejda", "Urząd Gminy Damnica", "",
                "", "598484442");

        insertProject(db, "RESTAURACJA MCDONALD`S", "IBS Budownictwo",
                "1 BUDYNEK RESTAURACJA 350 m2\n" +
                        "\n" +
                        "ZAKRES: Przegląd roczny według normy PN-HD 60364-6:2008 zakresie:\n" +
                        "•    oględziny instalacji elektrycznej i urządzeń elektrycznych, które są narażone na ne-gatywne wpływy atmosferyczne i inne czynniki zewnętrzne, a które występują w węzłach cieplnych, pralniach, suszarniach, garażach (pot. części mokre) \n" +
                        "•    przeciwpożarowy wyłącznik prądu\n" +
                        "•    oświetlenia awaryjnego/ewakuacyjnego",
                10, true);

        insertProject(db, "Urząd Gminy Damnica", "IBS Budownictwo",
                "Urząd Gminy Damnica 76-231 Damnica\n" +
                        "\n" +
                        "1. Remiza strażacka ok. 400m2-\n" +
                        "2. Stadion w Damnicy (scena, trybuny, szatnia) 381m2\n" +
                        "\n" +
                        "ZAKRES:\n" +
                        "Przegląd pięcioletni w rozumieniu art. 62 ust.1 pkt. 2 Ustawy Prawo Budowlane w zakresie:\n" +
                        "    badania instalacji elektrycznej i piorunochronnej w zakresie stanu sprawności połączeń, osprzętu, zabezpieczeń i środków ochrony od porażeń, oporności izolacji przewodów oraz uziemień instalacji i aparatów.\n" +
                        "\n" +
                        "Oferta obejmuje wykonanie następujących pomiarów i przeglądu:\n" +
                        "- pomiary ochrony przeciwporażeniowej\n" +
                        "- pomiary rezystancji izolacji\n" +
                        "- pomiary instalacji odgromowe\n" +
                        "- przegląd rozdzielni głównej RG\n" +
                        "\n" +
                        "termin: kwiecień/maj",
                11, true);

        insertPPAR(db, "W przypadku oznaczenia BPE należy podłączyć przewód ochronny PE do bolca w " +
                "gniazdku, aby zapewnić ochronę poprzez samoczynne wyłączenie zasilania.");
        insertPPAR(db, "W przypadku oznaczenia BN należy zapewnić ciągłość przewodu neutralnego na " +
                "drodze od rozdzielni elektrycznej, aż do odpowiedniego styku w gnieździe.");
        insertPPAR(db, "W przypadku oznaczenia BK należy wymienić gniazdo nieposiadające styku " +
                "ochronnego, na taki styk posiadające oraz podłączyć przewód PE. W przypadku gniazd BK " +
                "należy podłączać do nich tylko urządzenia w II klasie ochronności.");
        insertPPAR(db, "W związku z nietrwałym mocowaniem poprawić i co pewien okres sprawdzać " +
                "mocowanie gniazd wtyczkowych, w protokole zaznaczone komentarzem „wyrwane”.");
        insertPPAR(db, "Zaleca się testowanie wyłączników RCD za pomocą przycisku „TEST” " +
                "zgodnie z zaleceniami producenta.");
        insertPPAR(db, "„Połączenie realizowane 2-przewodowo” - opis zawarty przy wynikach pomiarów " +
                "impedancji pętli zwarcia odnosi się do wypustów oświetleniowych: żyrandoli, plastikowych i " +
                "szklanych lamp, oraz wszystkich innych punktów świetlnych, które realizują jedynie połączenie " +
                "przewodem fazowym i neutralnym.");
        insertPPAR(db, "Przeprowadzić konserwację rozdzielni elektrycznych, szczególnie oczyścić je " +
                "z kurzu i pajęczyn.");
        insertPPAR(db, "Brak kompletnej dokumentacji powykonawczej instalacji uniemożliwiający " +
                "dokonanie pełnej weryfikacji zgodności instalacji z projektem (w zakresie doboru i nastaw " +
                "urządzeń, przewodów i aparatów) – zaleca się uzupełnienie i weryfikację ww. elementów " +
                "instalacji z dokumentacją.");
        insertPPAR(db, "Brak aktualnych oznaczeń obwodów i schematów rozdzielnic i tablic – " +
                "zaleca się uzupełnienie i zaktualizowanie.");
        insertPPAR(db, "Zalecane dokonywanie co najmniej raz do roku okresowej konserwacji tablic i " +
                "rozdzielnic polegającej na oczyszczeniu zacisków, aparatów i przewodów i dokręceniu połączeń " +
                "śrubowych potwierdzonej adnotacją w niniejszym protokole.");
        insertPPAR(db, "Wynik oględzin instalacji odgromowej dotyczącej budynku pozytywny. " +
                "Instalacja ciągła i w dobrym stanie technicznym.");

        insertZSComponent(db, "Gn 1f p", "Gniazda");
        insertZSComponent(db, "Gn 1f 2p.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 2p.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 3p.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 3p.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 3p.3", "Gniazda");
        insertZSComponent(db, "Gn 1f 4p.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 4p.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 4p.3", "Gniazda");
        insertZSComponent(db, "Gn 1f 4p.4", "Gniazda");
        insertZSComponent(db, "Gn 3f p", "Gniazda");
        insertZSComponent(db, "Gn 1f g", "Gniazda");
        insertZSComponent(db, "Gn 1f d", "Gniazda");
        insertZSComponent(db, "Gn 1f pk", "Gniazda");
        insertZSComponent(db, "Gn 1f 2pk.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 2pk.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 3pk.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 3pk.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 3pk.3", "Gniazda");
        insertZSComponent(db, "Gn 1f 4pk.1", "Gniazda");
        insertZSComponent(db, "Gn 1f 4pk.2", "Gniazda");
        insertZSComponent(db, "Gn 1f 4pk.3", "Gniazda");
        insertZSComponent(db, "Gn 1f 4pk.4", "Gniazda");
        insertZSComponent(db, "O. podłużna", "Oświetlenie");
        insertZSComponent(db, "O. okrągła", "Oświetlenie");
        insertZSComponent(db, "O. podtynkowa", "Oświetlenie");
        insertZSComponent(db, "O. rastrowa", "Oświetlenie");
        insertZSComponent(db, "Wypust oświetleniowy", "Oświetlenie");
        insertZSComponent(db, "Żarówka", "Oświetlenie");
        insertZSComponent(db, "O. liniowa", "Oświetlenie");
        insertZSComponent(db, "O. kwadratowa", "Oświetlenie");
        insertZSComponent(db, "O. świetlówkowa", "Oświetlenie");
        insertZSComponent(db, "O. oświetleniowa", "Oświetlenie");
        insertZSComponent(db, "Klimatyzator", "Inne");
        insertZSComponent(db, "Centrala Wentylacyjna", "Inne");
        insertZSComponent(db, "Pompa", "Inne");
        insertZSComponent(db, "Piec", "Inne");
        insertZSComponent(db, "Silnik", "Inne");
        insertZSComponent(db, "Grill", "Inne");
        insertZSComponent(db, "Frytkownica", "Inne");

        insertZSElectricalProtection(db,"DO1 gG-2",
                14.375F,26.4367816091954F,
                12.1052631578947F, 18.91818F);
        insertZSElectricalProtection(db,"A",
                3,3,
                3,3,5);
        insertZSElectricalProtection(db,"B",
                5, 5,
                5, 5,7);
        insertZSElectricalProtection(db,"C",
                10,6,
                10,10,18);
        insertZSElectricalProtection(db,"D",
                20,6,
                20,20,23);
        insertZSElectricalProtection(db,"gG-16",
                1.54569892473118F, 2.72189349112426F,
                1.19791666666667F, 2.7383276506F);
    }

    private static void insertContactPerson(SQLiteDatabase db,
                                            String name,
                                            String firm,
                                            String position,
                                            String email,
                                            String phone) {
        ContentValues cv = new ContentValues();
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_NAME(), name);
        cv.put(ContactPersonTableHelper.getColumn_FIRM(), firm);
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_POSITION(), position);
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_EMAIL(), email);
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_PHONE(), phone);
        db.insert(ContactPersonTableHelper.getTableName_CONTACT_PERSON(), null, cv);
    }

    private static void insertProject(SQLiteDatabase db,
                                      String projectName,
                                      String firm,
                                      String description,
                                      int contactPersonId,
                                      boolean isSingleContactPerson) {
        ContentValues cv = new ContentValues();
        cv.put(ProjectTableHelper.getColumnProjectName(), projectName);
        cv.put(ProjectTableHelper.getColumnFirm(), firm);
        cv.put(ProjectTableHelper.getColumnDescription(), description);
        cv.put(ProjectTableHelper.getColumnContactPersonId(), contactPersonId);
        cv.put(ProjectTableHelper.getColumnIsSingleContactPerson(), isSingleContactPerson ? 1 : 0);
        db.insert(ProjectTableHelper.getTableName_PROJECT(), null, cv);
    }

    private static void insertPPAR(SQLiteDatabase db,
                                   String content){
        ContentValues cv = new ContentValues();
        cv.put(PPARTabelHelper.getColumnPPARContent(), content);
    }

    private static void insertZSComponent(SQLiteDatabase db,
                                          String component,
                                          String classOfComponent){
        ContentValues cv = new ContentValues();
        cv.put(ZSComponentsTableHelper.getColumnZsComponent(), component);
        cv.put(ZSComponentsTableHelper.getColumnClassOfComponent(), classOfComponent);
    }

    private static void insertZSElectricalProtection(SQLiteDatabase db,
                                                     String protectionType,
                                                     float multiplierTNReceiver,
                                                     float multiplierTNSwitchgear,
                                                     float multiplierTTReceiver,
                                                     float multiplierTTSwitchgear,
                                                     float multiplierDC){
        ContentValues cv = new ContentValues();
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtection(), protectionType);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTNReciver(), multiplierTNReceiver);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTNSwitchGear(), multiplierTNSwitchgear);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTTReciver(), multiplierTTReceiver);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTTSwitchGear(), multiplierTTSwitchgear);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierDC(), multiplierDC);
    }

    private static void insertZSElectricalProtection(SQLiteDatabase db,
                                                     String protectionType,
                                                     float multiplierTNReceiver,
                                                     float multiplierTNSwitchgear,
                                                     float multiplierTTReceiver,
                                                     float multiplierTTSwitchgear){
        ContentValues cv = new ContentValues();
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtection(), protectionType);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTNReciver(), multiplierTNReceiver);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTNSwitchGear(), multiplierTNSwitchgear);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTTReciver(), multiplierTTReceiver);
        cv.put(ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionMultiplierTTSwitchGear(), multiplierTTSwitchgear);
    }

}