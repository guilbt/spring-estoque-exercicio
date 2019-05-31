package br.com.ithappens;

import br.com.ithappens.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
//    teste();
  }

//  public static void teste(){
//
//    Produto p1 = new Produto(1, "Notebook");
//    Produto p2 = new Produto(2, "Smartphone");
//    Produto p3 = new Produto(3, "Monitor");
//
//    List<Produto> lista = new ArrayList<>();
//
//    lista.add(p1);
//    lista.add(p2);
//    lista.add(p3);
//    lista.forEach(System.out::println);
//    lista.stream().filter(p -> p.getCodigo() <=2).collect(Collectors.toList()).forEach(System.out::println);
//  }
}