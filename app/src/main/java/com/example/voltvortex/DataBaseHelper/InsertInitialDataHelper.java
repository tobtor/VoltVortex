package com.example.voltvortex.DataBaseHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.*;

import java.util.Date;

public class InsertInitialDataHelper {

    public static void insertInitialData(SQLiteDatabase db) {
        insertContactPerson(db, "Paweł Mikołajek", "Przykładowa firma", "Pomiarowiec",
                "przykładowy.adres@email.com", "123456789");
        insertContactPerson(db, "Julia Lewandowska", "Przykładowa firma", "Pomiarowiec",
                "przykładowy.adres@email.com", "123456789");
        insertContactPerson(db, "Karol Dąbrowski", "Przykładowa firma", "Pomiarowiec",
                "przykładowy.adres@email.com", "123456789");
        insertContactPerson(db, "Maciej Kowalski", "Przykładowa firma", "Pomiarowiec",
                "przykładowy.adres@email.com", "123456789");

        insertProject(db, "Przykładowy Projekt", "Przykładowa Firma",
                "1 BUDYNEK RESTAURACJA 350 m2\n" +
                        "\n" +
                        "ZAKRES: Przegląd roczny według normy PN-HD 60364-6:2008 zakresie:\n" +
                        "•    oględziny instalacji elektrycznej i urządzeń elektrycznych, które są " +
                        "narażone na negatywne wpływy atmosferyczne i inne czynniki zewnętrzne, " +
                        "a które występują w węzłach cieplnych, pralniach, suszarniach, garażach (pot. części mokre) \n" +
                        "•    przeciwpożarowy wyłącznik prądu\n" +
                        "•    oświetlenia awaryjnego/ewakuacyjnego",
                10, true);

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
        insertZSComponent(db, "Szyna Oświetleniowa", "Oświetlenie");
        insertZSComponent(db, "O. Led", "Oświetlenie");

        insertZSElectricalProtection(db, "DO1 gG-2",
                14.38F, 26.44F,
                12.11F, 18.92F);
        insertZSElectricalProtection(db, "DO1 gG-4",
                7.42F, 12.30F,
                6.67F, 8.82F);
        insertZSElectricalProtection(db, "DO1 gG-6",
                4.67F, 9.09F,
                3.85F, 6.19F);
        insertZSElectricalProtection(db, "DO1 gG-10",
                3.10F, 5.29F,
                2.64F, 3.63F);
        insertZSElectricalProtection(db, "DO1 gG-16",
                1.98F, 3.41F,
                1.72F, 2.74F);
        insertZSElectricalProtection(db, "DO1 gG-20",
                1.58F, 2.80F,
                1.32F, 1.92F);
        insertZSElectricalProtection(db, "DO1 gG-25",
                1.14F, 2.08F,
                1.00F, 1.58F);
        insertZSElectricalProtection(db, "DO1 gG-32",
                1.01F, 1.74F,
                0.86F, 1.06F);
        insertZSElectricalProtection(db, "DO1 gG-35",
                0.83F, 1.48F,
                0.69F, 1.12F);
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
        insertZSElectricalProtection(db, "gG-2",
                14.38F, 25.56F,
                12.11F, 18.92F);
        insertZSElectricalProtection(db, "gG-4",
                7.19F, 13.53F,
                5.90F, 8.82F);
        insertZSElectricalProtection(db, "gG-6",
                4.34F, 8.21F,
                3.71F, 6.19F);
        insertZSElectricalProtection(db, "gG-10",
                2.77F, 5.00F,
                2.34F, 3.63F);
        insertZSElectricalProtection(db, "gG-16",
                1.55F, 2.72F,
                1.20F, 2.74F);
        insertZSElectricalProtection(db, "gG-20",
                1.18F, 2.19F,
                1.02F, 1.92F);
        insertZSElectricalProtection(db, "gG-25",
                0.90F, 1.76F,
                0.73F, 1.58F);
        insertZSElectricalProtection(db, "gG-32",
                0.71F, 1.31F,
                0.58F, 1.06F);
        insertZSElectricalProtection(db, "gG-35",
                0.64F, 1.16F,
                0.52F, 1.12F);
        insertZSElectricalProtection(db, "gG-40",
                0.57F, 1.12F,
                0.47F, 0.87F);
        insertZSElectricalProtection(db, "gG-50",
                0.41F, 0.77F,
                0.33F, 0.61F);
        insertZSElectricalProtection(db, "gG-63",
                0.35F, 0.65F,
                0.28F, 0.51F);

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

    private static void insertZSComponent(SQLiteDatabase db,
                                          String component,
                                          String classOfComponent){
        ContentValues cv = new ContentValues();
        cv.put(ZSComponentsTableHelper.getColumnZsComponent(), component);
        cv.put(ZSComponentsTableHelper.getColumnClassOfComponent(), classOfComponent);
        db.insert(ZSComponentsTableHelper.getTableName_ZS_COMPONENT(), null, cv);
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
        db.insert(ZsElectricalProtectionTableHelper.getTableName_ZS_ELECTRICAL_PROTECTION(), null, cv);
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
        db.insert(ZsElectricalProtectionTableHelper.getTableName_ZS_ELECTRICAL_PROTECTION(), null, cv);
    }

}