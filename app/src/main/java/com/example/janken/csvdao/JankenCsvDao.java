package com.example.janken.csvdao;

import com.example.janken.model.Janken;
import lombok.val;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class JankenCsvDao {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String JANKENS_CSV = CsvDaoUtils.DATA_DIR + "jankens.csv";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Janken insert(Janken janken) {
        val jankensCsv = new File(JANKENS_CSV);

        try (val fw = new FileWriter(jankensCsv, true);
             val bw = new BufferedWriter(fw);
             val pw = new PrintWriter(bw)) {

            jankensCsv.createNewFile();

            val jankenId = CsvDaoUtils.countFileLines(JANKENS_CSV) + 1;
            val jankenWithId = new Janken(jankenId, janken.getPlayedAt());

            val line = janken2Line(jankenWithId);
            pw.println(line);

            return jankenWithId;
        }  catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String janken2Line(Janken janken) {
        val playedAtStr = DATE_TIME_FORMATTER.format(janken.getPlayedAt());

        return janken.getId() + CsvDaoUtils.CSV_DELIMITER + playedAtStr;
    }
}
