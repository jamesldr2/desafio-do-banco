package historico;

import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtCreation;

import conta.interfaces.IConta;
import historico.enums.TipoTransacao;
import historico.interfaces.ITransacao;

public class Transacao implements ITransacao {

    double saldoAnterior;
    double saldoPosterior;
    DateTimeAtCreation data;
    Optional<IConta> contaInteracao;

    public Transacao(double saldoAnterior, double valorMovimentado, Optional<IConta> conta) {
        this.saldoAnterior = saldoAnterior;
        this.saldoPosterior = saldoAnterior + valorMovimentado;
        this.contaInteracao = conta;
    }

    @Override
    public TipoTransacao tipoTransacao() {
        if (this.contaInteracao.isPresent()) {
            return this.saldoPosterior > this.saldoAnterior ? TipoTransacao.TRANSFERENCIA_RECEBIDA
                    : TipoTransacao.TRANSFERENCIA_ENVIADA;
        }
        return this.saldoPosterior > this.saldoAnterior ? TipoTransacao.CREDITO : TipoTransacao.DEBITO;
    }

}
