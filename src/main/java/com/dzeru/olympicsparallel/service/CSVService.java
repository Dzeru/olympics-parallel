package com.dzeru.olympicsparallel.service;

import com.dzeru.olympicsparallel.model.OlympicDatasetRow;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CSVService {

    private List<OlympicDatasetRow> olympicDataset;

    private static final Path csvPath;

    static {
        try {
            csvPath = Paths.get(Objects.requireNonNull(
                            Thread.currentThread().getContextClassLoader().getResource("dataset/Summer-Olympic-medals-1976-to-2008.csv"))
                    .toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OlympicDatasetRow> readAllLines() throws Exception {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withQuoteChar('"')
                .withIgnoreQuotations(false)
                .build();

        try (Reader reader = Files.newBufferedReader(csvPath, StandardCharsets.ISO_8859_1)) {
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser);

            try (CSVReader csvReader = csvReaderBuilder.build()) {
                var aa = csvReader.peek();
                var aaa = csvReader.readNext();
                //var a = csvReader.readAll();
                this.olympicDataset = convertFromStringArray(csvReader.readAll());
                return this.olympicDataset;
            }
        }
    }

    public List<OlympicDatasetRow> getOlympicDataset() {
        return this.olympicDataset;
    }

    private List<OlympicDatasetRow> convertFromStringArray(List<String[]> lines) {
        return lines.stream().map(OlympicDatasetRow::new).collect(Collectors.toList());
    }
}
