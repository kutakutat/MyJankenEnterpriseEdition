package com.example.janken.csvdao;

import com.example.janken.model.JankenDetail;
import lombok.val;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JankenDetailCsvDao {
    private static final String JANKEN_DETAILS_CSV = CsvDaoUtils.DATA_DIR + "janken_details.csv";

    public List<JankenDetail> insertAll(List<JankenDetail> jankenDetails) {
        val jankenDetailsCsv = new File(JANKEN_DETAILS_CSV);

        try (val fw = new FileWriter(jankenDetailsCsv, true);
             val bw = new BufferedWriter(fw);
             val pw = new PrintWriter(bw)) {

            jankenDetailsCsv.createNewFile();

            val jankenDetailWithIds = new ArrayList<JankenDetail>();
            for (int i = 0; i < jankenDetails.size(); i++) {
                val jankenDetail = jankenDetails.get(i);

                val jankenDetailId = CsvDaoUtils.countFileLines(JANKEN_DETAILS_CSV) + i + 1;
                val jankenDetailWithId = new JankenDetail(
                        jankenDetailId,
                        jankenDetail.getJankenId(),
                        jankenDetail.getPlayerId(),
                        jankenDetail.getHand(),
                        jankenDetail.getResult());

                val line = jankenDetail2Line(jankenDetailWithId);
                pw.println(line);

                jankenDetailWithIds.add(jankenDetailWithId);
            }

            return jankenDetailWithIds;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String jankenDetail2Line(JankenDetail jankenDetail) {
        return String.join(CsvDaoUtils.CSV_DELIMITER,
                String.valueOf(jankenDetail.getId()),
                String.valueOf(jankenDetail.getJankenId()),
                String.valueOf(jankenDetail.getPlayerId()),
                String.valueOf(jankenDetail.getHand().getValue()),
                String.valueOf(jankenDetail.getResult().getValue()));
    }
}
