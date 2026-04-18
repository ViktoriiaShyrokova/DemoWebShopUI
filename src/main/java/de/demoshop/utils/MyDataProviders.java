package de.demoshop.utils;

import de.demoshop.model.User;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class MyDataProviders {

    @DataProvider
    public Iterator<Object[]> fillRegisterFormWithInvalidDataFromCsv() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/RegistrationInvalidData.csv"), StandardCharsets.UTF_8)) {

            return lines
                    .filter(l -> !l.isBlank())
                    .skip(1)
                    .map(l -> l.split(",",-1))
                    .map(split -> new Object[]{new User()
                            .setName(split[0])
                            .setLastName(split[1])
                            .setEmail(split[2])
                            .setPassword(split[3])})
                    .toList()
                    .iterator();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
