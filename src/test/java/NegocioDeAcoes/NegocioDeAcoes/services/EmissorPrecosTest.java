package NegocioDeAcoes.NegocioDeAcoes.services;

import NegocioDeAcoes.NegocioDeAcoes.model.Conta;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.model.MonitoramentoLista;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
@RunWith(SpringRunner.class)
@RestClientTest(EmissorPrecos.class)
public class EmissorPrecosTest {


    private long tempoInicial;
    private long tempoFinal;
    private long total;

    @Autowired
    private EmissorPrecos emissorPrecos;

    @Autowired
    private MockRestServiceServer server;

    @Before
    public void setUp(){
        Monitoramento monitoramento = new Monitoramento();
        monitoramento.setEmpresa("Facebook");
        monitoramento.setPrecoCompra(10d);
        monitoramento.setPrecoVenda(12d);
        monitoramento.setId(1151l);
        Conta conta = new Conta();
        conta.setId(1l);
        conta.setSaldo(100000d);
        conta.setDescricao("Caixa");
        monitoramento.setConta(conta);
        MonitoramentoLista.getMonitoramentos().put(monitoramento.getId(), monitoramento);
        this.server.expect(ExpectedCount.times(10),requestTo("http://localhost:8080/contas/"+monitoramento.getConta().getId()+
        "/monitoramentos/"+monitoramento.getId()+
                "/preco"))
                .andRespond(withSuccess());
    }

    @Test
    public void iniciaEmissao() {
//
//        EmissorPrecos emissorPrecos = new EmissorPrecos();
        for (int i= 0; i < 10; i++) {
            tempoInicial = System.currentTimeMillis();
            double preco = emissorPrecos.geraPreco(1151);
            tempoFinal = System.currentTimeMillis();
            assertTrue("Tempo deve ser menor que 5s (tolerancia de 200 ms): "+(tempoFinal - tempoInicial), 200l > (5000l - (tempoFinal - tempoInicial)));
            assertTrue("Preco deve estar entre 9 e 13,20: " + preco, (preco>9d && preco<13.2));
        }
    }
}