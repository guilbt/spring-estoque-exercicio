package br.com.ithappens.model;

import br.com.ithappens.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Estoque {
    Long id;
    Produto produto;
    Filial filial;
    int quantidade;

    public Estoque (Long id, int quantidade){
        this.id = id;
        this.quantidade = quantidade;
    }

    public void addQuantidade(int quantidadeAdd) {
        this.quantidade += quantidadeAdd;
    }

    public void retirarQuantidade(int quantidadeRetirada) throws BadRequestException {
        if(this.getQuantidade() < quantidadeRetirada){
            throw new BadRequestException("Não existe estoque o suficiente de um dos items do pedido");
        }
        this.quantidade -= quantidadeRetirada;
    }
}
