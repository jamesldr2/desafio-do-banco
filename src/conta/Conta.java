package conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import conta.interfaces.IConta;
import historico.Transacao;

public abstract class Conta implements IConta {
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected List<Transacao> transacoes;

    private static int SEQUENCIAL = 0;

    protected Conta() {
        this.agencia = 1;
        this.numero = SEQUENCIAL++;
        this.saldo = 0;
        this.transacoes = new ArrayList<>();
    }

    @Override
    public void depositar(double valor) {
        this.transacoes.add(new Transacao(this.saldo, valor, null));
        this.saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        this.transacoes.add(new Transacao(this.saldo, -valor, null));
        this.saldo -= valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.transacoes.add(new Transacao(this.saldo, -valor, Optional.of(contaDestino)));
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public int getNumero() {
        return this.numero;
    }

    public List<Transacao> getTransacoes() {
        return this.transacoes;
    }

    @Override
    public void imprimirSaldo() {
        System.out.println("Saldo: " + this.saldo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Conta) {
            Conta conta = (Conta) obj;
            return this.agencia == conta.agencia && this.numero == conta.numero;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.agencia ^ this.numero;
    }

}
