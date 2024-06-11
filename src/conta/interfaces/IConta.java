package conta.interfaces;

public interface IConta {
    void depositar(double valor);

    void sacar(double valor);

    void transferir(double valor, IConta contaDestino);

    void imprimirSaldo();
}
