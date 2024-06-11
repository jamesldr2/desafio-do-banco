package cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import conta.ContaCorrente;
import conta.ContaPoupanca;
import conta.interfaces.IConta;
import historico.Transacao;

public class Cliente implements Comparable<Cliente> {
    private static int SEQUENCIAL = 0;
    public int id;
    public String nome;
    public ContaCorrente contaCorrente;
    public ContaPoupanca contaPoupanca;
    public List<Transacao> transacoes;

    public Optional<IConta> getContaByNumero(int numero) {
        if (this.contaCorrente.getNumero() == numero) {
            return Optional.of(this.contaCorrente);
        }
        if (this.contaPoupanca.getNumero() == numero) {
            return Optional.of(this.contaPoupanca);
        }
        return Optional.empty();
    }

    public Cliente(String nome) {
        this.id = SEQUENCIAL++;
        this.nome = nome;
        this.contaCorrente = new ContaCorrente();
        this.contaPoupanca = new ContaPoupanca();
        this.transacoes = new ArrayList<>();
    }

    public List<Transacao> getTransacoes() {
        transacoes.addAll(this.contaCorrente.getTransacoes());
        transacoes.addAll(this.contaPoupanca.getTransacoes());
        return transacoes;
    }

    @Override
    public String toString() {
        return String.format(
                "Cliente: %s%nAgencia: %s%nConta Corrente: %d%nConta Poupança: %d%nSaldo CC: %.2f%nSaldo CP: %.2f%n",
                this.nome,
                this.contaCorrente.getAgencia(),
                this.contaCorrente.getNumero(),
                this.contaPoupanca.getNumero(),
                this.contaCorrente.getSaldo(),
                this.contaPoupanca.getSaldo());
    }

    @Override
    public int compareTo(Cliente other) {
        return this.nome.compareTo(other.nome);
    }

}
