package br.com.demtech.main;

import br.com.demtech.domain.model.Model;
import br.com.demtech.domain.model.ModelAndYearDTO;
import br.com.demtech.domain.model.ValueDTO;
import br.com.demtech.service.ConsumerAPI;
import br.com.demtech.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mateus Dantas
 */
public class Main {
    private static final String URL = "https://parallelum.com.br/fipe/api/v1/";
    private static final String COMPLEMENT_MODEL = "/modelos";
    private static final String COMPLEMENT_YEAR = "/anos";

    private Scanner scanner = new Scanner(System.in);
    private ConsumerAPI consumerAPI = new ConsumerAPI();
    private ConvertData convertData = new ConvertData();

    public void showMenu() {
        String menu = """
                *** OPÇÕES ***
                Moto
                Carro
                Caminhão
                
                Digite uma das opções para consultar:
                """;
        System.out.print(menu);

        String option = scanner.nextLine();
        String address;

        if (option.toLowerCase().contains("carr")) {
            address = URL + "carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            address = URL + "motos/marcas";
        } else {
            address = URL + "caminhoes/marcas";
        }

        String json = consumerAPI.getData(address);
        System.out.println(json);
        var mark = convertData.getList(json, ModelAndYearDTO.class);
        mark.stream()
                .sorted(Comparator.comparing(ModelAndYearDTO::code))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        String markCode = scanner.nextLine();

        address = address + "/" + markCode + COMPLEMENT_MODEL;
        json = consumerAPI.getData(address);
        var modelList = convertData.getData(json, Model.class);

        System.out.println("\nModelos dessa marca: ");
        modelList.models().stream()
                .sorted(Comparator.comparing(ModelAndYearDTO::code))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        String vehicleName = scanner.nextLine();

        List<ModelAndYearDTO> modelFiltered = modelList.models().stream()
                .filter(m -> m.name().toLowerCase().contains(vehicleName.toLowerCase()))
                .toList();

        System.out.println("\nModelos filtrados");
        modelFiltered.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores.");
        String modelCode = scanner.nextLine();

        address = address + "/" + modelCode + COMPLEMENT_YEAR;
        json = consumerAPI.getData(address);
        List<ModelAndYearDTO> years = convertData.getList(json, ModelAndYearDTO.class);
        List<ValueDTO> values = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            String addressYears = address + "/" + years.get(i).code();
            json = consumerAPI.getData(addressYears);
            ValueDTO valueDTO = convertData.getData(json, ValueDTO.class);
            values.add(valueDTO);
        }

        System.out.println("\nVeículos filtrados por ano: ");
        values.forEach(System.out::println);
    }
}
