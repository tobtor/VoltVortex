package com.example.voltvortex.DataBaseHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PARTabelHelper;

public class InsertBasicBuildingData {

    public static void insertBasicBuildingData(SQLiteDatabase db, int buidlingId) {
        insertPAR(db, "W przypadku oznaczenia BPE należy podłączyć przewód ochronny PE do bolca w " +
                "gniazdku, aby zapewnić ochronę poprzez samoczynne wyłączenie zasilania.", buidlingId);
        insertPAR(db, "W przypadku oznaczenia BN należy zapewnić ciągłość przewodu neutralnego na " +
                "drodze od rozdzielni elektrycznej, aż do odpowiedniego styku w gnieździe.", buidlingId);
        insertPAR(db, "W przypadku oznaczenia BK należy wymienić gniazdo nieposiadające styku " +
                "ochronnego, na taki styk posiadające oraz podłączyć przewód PE. W przypadku gniazd BK " +
                "należy podłączać do nich tylko urządzenia w II klasie ochronności.", buidlingId);
        insertPAR(db, "W związku z nietrwałym mocowaniem poprawić i co pewien okres sprawdzać " +
                "mocowanie gniazd wtyczkowych, w protokole zaznaczone komentarzem „wyrwane”.", buidlingId);
        insertPAR(db, "Zaleca się testowanie wyłączników RCD za pomocą przycisku „TEST” " +
                "zgodnie z zaleceniami producenta.", buidlingId);
        insertPAR(db, "„Połączenie realizowane 2-przewodowo” - opis zawarty przy wynikach pomiarów " +
                "impedancji pętli zwarcia odnosi się do wypustów oświetleniowych: żyrandoli, plastikowych i " +
                "szklanych lamp, oraz wszystkich innych punktów świetlnych, które realizują jedynie połączenie " +
                "przewodem fazowym i neutralnym.", buidlingId);
        insertPAR(db, "Przeprowadzić konserwację rozdzielni elektrycznych, szczególnie oczyścić je " +
                "z kurzu i pajęczyn.", buidlingId);
        insertPAR(db, "Brak kompletnej dokumentacji powykonawczej instalacji uniemożliwiający " +
                "dokonanie pełnej weryfikacji zgodności instalacji z projektem (w zakresie doboru i nastaw " +
                "urządzeń, przewodów i aparatów) – zaleca się uzupełnienie i weryfikację ww. elementów " +
                "instalacji z dokumentacją.", buidlingId);
        insertPAR(db, "Brak aktualnych oznaczeń obwodów i schematów rozdzielnic i tablic – " +
                "zaleca się uzupełnienie i zaktualizowanie.", buidlingId);
        insertPAR(db, "Zalecane dokonywanie co najmniej raz do roku okresowej konserwacji tablic i " +
                "rozdzielnic polegającej na oczyszczeniu zacisków, aparatów i przewodów i dokręceniu połączeń " +
                "śrubowych potwierdzonej adnotacją w niniejszym protokole.", buidlingId);
        insertPAR(db, "Wynik oględzin instalacji odgromowej dotyczącej budynku pozytywny. " +
                "Instalacja ciągła i w dobrym stanie technicznym.", buidlingId);
    }

    private static void insertPAR(SQLiteDatabase db,
                                  String content,
                                  int isUsed,
                                  int buildingId){
        ContentValues cv = new ContentValues();
        cv.put(PARTabelHelper.getColumnPARContent(), content);
        cv.put(PARTabelHelper.getColumnPARIsUsed(), isUsed);
        db.insert(PARTabelHelper.getTableName_PAR(buildingId), null, cv);
    }

    private static void insertPAR(SQLiteDatabase db,
                                  String content,
                                  int buildingId){
        ContentValues cv = new ContentValues();
        cv.put(PARTabelHelper.getColumnPARContent(), content);
        cv.put(PARTabelHelper.getColumnPARIsUsed(), 0);
        db.insert(PARTabelHelper.getTableName_PAR(buildingId), null, cv);
    }
}