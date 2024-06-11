package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cliente.Cliente;
import conta.interfaces.IConta;

public class Banco {
    public String nome;
    public List<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void adicionarClientes(List<Cliente> clientes) {
        this.clientes.addAll(clientes);
    }

    public void removerCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void imprimirClientes() {
        List<Cliente> tempClients = new ArrayList<>(this.clientes);

        for (Cliente cliente : tempClients) {
            System.out.println(cliente);
        }
    }

    public void depositar(double valor, IConta conta) {

        conta.depositar(valor);

    }

    public void sacar(double valor, IConta conta) {
        conta.sacar(valor);
    }

    public void transferir(double valor, IConta origem, IConta destino) {
        Cliente clienteOrigem = getClienteByConta(origem)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        Cliente clienteDestino = getClienteByConta(destino)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        clienteOrigem.contaCorrente.transferir(valor, clienteDestino.contaCorrente);
        updateClienteById(clienteDestino.id);
        updateClienteById(clienteOrigem.id);

    }

    private Optional<Cliente> getClienteByConta(IConta conta) {
        return this.clientes.stream()
                .filter(c -> c.contaCorrente.equals(conta) || c.contaPoupanca.equals(conta))
                .findFirst();
    }

    private Optional<Cliente> getClientById(int id) {
        return this.clientes.stream()
                .filter(c -> c.id == id)
                .findFirst();
    }

    private void updateClienteById(int id) {
        Optional<Cliente> clienteOptional = getClientById(id);
        if (clienteOptional.isPresent()) {
            clientes.removeIf(c -> c.id == id);
            this.clientes.add(clienteOptional.get());
        }
    }

    public List<IConta> getContas() {
        List<IConta> contas = new ArrayList<>();
        for (Cliente cliente : this.clientes) {
            contas.add(cliente.contaCorrente);
            contas.add(cliente.contaPoupanca);
        }
        return contas;
    }

    public static void main(String[] args) {
        Banco banco = new Banco("Banco do Brasil");

        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");

        banco.adicionarClientes(List.of(cliente1, cliente2));

        banco.depositar(200, cliente1.contaCorrente);
        banco.imprimirClientes();
        banco.transferir(100, cliente1.contaCorrente, cliente2.contaCorrente);
        System.out.println("\n==============================================================================\n");
        banco.imprimirClientes();

    }

}
