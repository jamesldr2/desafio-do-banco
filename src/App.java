import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import cliente.Cliente;

public class App {

    static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Banco banco = new Banco("Banco no Banquil");
        Cliente cliente1 = new Cliente("José");
        Cliente cliente2 = new Cliente("Maria");
        String divider = "====================";

        clientes.addAll(List.of(cliente1, cliente2));

        banco.adicionarClientes(clientes);
        banco.imprimirClientes();
        System.out.println(divider);
        banco.depositar(200, clientes.get(0).contaCorrente);
        System.out.println("Depósito de 200 reais na conta de " + clientes.get(0).nome);
        System.out.println(divider);
        banco.depositar(200, clientes.get(1).contaCorrente);
        System.out.println("Depósito de 200 reais na conta de " + clientes.get(1).nome);
        System.out.println(divider);
        banco.sacar(100, clientes.get(0).contaCorrente);
        System.out.println("Saque de 100 reais na conta de " + clientes.get(0).nome);
        System.out.println(divider);
        banco.sacar(100, clientes.get(1).contaCorrente);
        System.out.println("Saque de 100 reais na conta de " + clientes.get(1).nome);
        System.out.println(divider);
        banco.transferir(50, clientes.get(0).contaCorrente, clientes.get(1).contaCorrente);
        System.out.println("Transferência de 50 reais da conta de " + clientes.get(0).nome + " para a conta de "
                + clientes.get(1).nome);
        System.out.println(divider);
        banco.imprimirClientes();
    }
}
